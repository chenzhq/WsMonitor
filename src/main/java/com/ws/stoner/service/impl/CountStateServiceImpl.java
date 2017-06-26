package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApiParameter;
import com.ws.bix4j.access.application.ApplicationGetRequest;
import com.ws.bix4j.access.host.HostGetRequest;
import com.ws.bix4j.access.hostgroup.HostGroupGetRequest;
import com.ws.bix4j.access.item.ItemGetRequest;
import com.ws.stoner.constant.StatusEnum;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.manager.*;
import com.ws.stoner.model.dto.BriefHostDTO;
import com.ws.stoner.model.dto.BriefItemDTO;
import com.ws.stoner.model.dto.BriefTriggerDTO;
import com.ws.stoner.model.dto.StateNumDTO;
import com.ws.stoner.service.CountStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * Created by pc on 2017/6/12.
 */
@Service
public class CountStateServiceImpl implements CountStateService {

    @Autowired
    private HostManager hostManager;

    @Autowired
    private TriggerManager triggerManager;

    @Autowired
    private PointManager pointManager;

    @Autowired
    private ItemManager itemManager;

    @Autowired
    private PlatformManager platformManager;

    /**
     * 主机业务数据组合
     * @return
     * @throws ServiceException
     */
    @Override
    public StateNumDTO countHostState() throws ServiceException {
        StateNumDTO hostState = new StateNumDTO();
        List<StateNumDTO.StateNum> stateNums = new ArrayList<>();
        StateNumDTO.StateNum problemStateNum = new StateNumDTO.StateNum(StatusEnum.PROBLEM,countProblemHost());
        StateNumDTO.StateNum okStateNum = new StateNumDTO.StateNum(StatusEnum.OK,countOkHost());
        stateNums.add(okStateNum);
        stateNums.add(problemStateNum);
        hostState.setTotalNum(countAllHost()).setStateNum(stateNums);

        return hostState;
    }

    /**
     * 获取业务平台业务组合数据
     * @return
     * @throws ServiceException
     */
    @Override
    public StateNumDTO countPlatformState() throws ServiceException {
        StateNumDTO platformState = new StateNumDTO();
        List<StateNumDTO.StateNum> stateNums = new ArrayList<>();
        StateNumDTO.StateNum problemStateNum = new StateNumDTO.StateNum(StatusEnum.PROBLEM,countProblemPlatform());
        StateNumDTO.StateNum okStateNum = new StateNumDTO.StateNum(StatusEnum.OK,countOkPlatform());
        stateNums.add(okStateNum);
        stateNums.add(problemStateNum);
        platformState.setTotalNum(countAllPlatform()).setStateNum(stateNums);
        return platformState;
    }

    /**
     * 获取监控点业务组合数据
     * @return
     * @throws ServiceException
     */
    @Override
    public StateNumDTO countPointState() throws ServiceException {
        StateNumDTO pointState = new StateNumDTO();
        List<StateNumDTO.StateNum> stateNums = new ArrayList<>();
        StateNumDTO.StateNum problemStateNum = new StateNumDTO.StateNum(StatusEnum.PROBLEM,countProblemPoint());
        StateNumDTO.StateNum okStateNum = new StateNumDTO.StateNum(StatusEnum.OK,countOkPoint());
        stateNums.add(okStateNum);
        stateNums.add(problemStateNum);
        pointState.setTotalNum(countAllPoint()).setStateNum(stateNums);
        return pointState;
    }

    /**
     * 获取主机总数量，排除停用主机，filter： status:0
     * @return
     * @throws ServiceException
     */
    @Override
    public int countAllHost() throws ServiceException {
        HostGetRequest hostGetRequest = new HostGetRequest();
        Map<String, Integer> statusFilter = new HashMap<>();
        statusFilter.put("status", ZApiParameter.HOST_MONITOR_STATUS.MONITORED_HOST.value);
        hostGetRequest.getParams()
                .setFilter(statusFilter)
                .setCountOutput(true);
        int allHostNum;
        try {
            allHostNum = hostManager.countHost(hostGetRequest);
        } catch (ManagerException e) {
            e.printStackTrace();
            return 0;
        }
        return allHostNum;
    }

    /**
     * 获取问题主机数量
     * 1、根据触发器状态获取问题主机
     * filter: a.  state:up to date  value:problem  only_true:true
     *         b.  state:unknown
     * 2、筛选四种监控接口中至少一个有问题的主机，这是另一部分问题状态的主机filter+searchByAny
     * @return
     * @throws ServiceException
     */
    @Override
    public int countProblemHost() throws ServiceException {
        //step1:获取问题触发器ids
        List<String> triggerIds ;
        try {
            triggerIds = triggerManager.getProblemTriggerIds();
        } catch (ManagerException e) {
            e.printStackTrace();
            return 0;
        }
        //step2:根据两个触发器的ids得到主机数量 hosts1
        HostGetRequest hostGetRequest1 = new HostGetRequest();
        Map<String,Object> hostFilter1 = new HashMap<>();
        hostFilter1.put("status",ZApiParameter.HOST_MONITOR_STATUS.MONITORED_HOST.value);
        hostGetRequest1.getParams()
                .setTriggerIds(triggerIds)
                .setOutput(BriefHostDTO.PROPERTY_NAMES)
                .setFilter(hostFilter1);
        List<BriefHostDTO> host1 ;
        try {
            host1 = hostManager.listHost(hostGetRequest1);
        } catch (ManagerException e) {
            e.printStackTrace();
            return 0;
        }
        //step4:筛选四种监控接口中至少一个有问题的主机数量 host2
        HostGetRequest hostGetRequest2 = new HostGetRequest();
        Map<String, Object> hostFilter2 = new HashMap<>();
        hostFilter2.put("monitored_hosts",true);
        hostFilter2.put("available",ZApiParameter.HOST_AVAILABLE.UNAVAILABLE_HOST.value);
        hostFilter2.put("ipmi_available",ZApiParameter.HOST_AVAILABLE.UNAVAILABLE_HOST.value);
        hostFilter2.put("jmx_available",ZApiParameter.HOST_AVAILABLE.UNAVAILABLE_HOST.value);
        hostFilter2.put("snmp_available",ZApiParameter.HOST_AVAILABLE.UNAVAILABLE_HOST.value);
        hostGetRequest2.getParams()
                .setOutput(BriefHostDTO.PROPERTY_NAMES)
                .setFilter(hostFilter2)
                .setSearchByAny(true);
        List<BriefHostDTO> host2 ;
        try {
            host2 = hostManager.listHost(hostGetRequest2);
        } catch (ManagerException e) {
            e.printStackTrace();
            return 0;
        }
        //step5:去掉重复的主机并求和
        List<BriefHostDTO> hosts = new ArrayList<>(host1);
        hosts.retainAll(host2);
        host1.removeAll(hosts);
        int hostNum = host1.size() + host2.size();
        return hostNum;
    }

    /**
     *  获取正常主机的数量
     *  okHostNum = allHostNum - problemHostNum
     * @return
     * @throws ServiceException
     */
    @Override
    public int countOkHost() throws ServiceException {
        int okHostNum = countAllHost() - countProblemHost();
        return okHostNum;
    }

    /**
     * 获取指定业务平台的所有主机数量 all host number by platformIds
     * @return
     * @throws ServiceException
     */
    @Override
    public int countAllHostByPlatformIds(List<String> platformIds) throws ServiceException {
        HostGetRequest hostGetRequest = new HostGetRequest();
        Map<String, Integer> statusFilter = new HashMap<>();
        statusFilter.put("status", ZApiParameter.HOST_MONITOR_STATUS.MONITORED_HOST.value);
        hostGetRequest.getParams()
                .setGroupIds(platformIds)
                .setFilter(statusFilter)
                .setCountOutput(true);
        int allHostNum;
        try {
            allHostNum = hostManager.countHost(hostGetRequest);
        } catch (ManagerException e) {
            e.printStackTrace();
            return 0;
        }
        return allHostNum;
    }

    /**
     * 获取指定业务平台的问题主机数量 problem host number by platformIds
     * @param platformIds
     * @return
     * @throws ServiceException
     */
    @Override
    public int countProblemHostByPlatformIds(List<String> platformIds) throws ServiceException {
        //step1:获取问题触发器ids
        List<String> triggerIds ;
        try {
            triggerIds = triggerManager.getProblemTriggerIds();
        } catch (ManagerException e) {
            e.printStackTrace();
            return 0;
        }
        //step2:根据两个触发器的ids得到主机数量 hosts1
        HostGetRequest hostGetRequest1 = new HostGetRequest();
        Map<String,Object> hostFilter1 = new HashMap<>();
        hostFilter1.put("status",ZApiParameter.HOST_MONITOR_STATUS.MONITORED_HOST.value);
        hostGetRequest1.getParams()
                .setTriggerIds(triggerIds)
                .setGroupIds(platformIds)
                .setFilter(hostFilter1)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> host1 ;
        try {
            host1 = hostManager.listHost(hostGetRequest1);
        } catch (ManagerException e) {
            e.printStackTrace();
            return 0;
        }
        //step4:筛选四种监控接口中至少一个有问题的主机数量 host2
        HostGetRequest hostGetRequest2 = new HostGetRequest();
        Map<String, Object> hostFilter2 = new HashMap<>();
        hostFilter2.put("monitored_hosts",true);
        hostFilter2.put("available",ZApiParameter.HOST_AVAILABLE.UNAVAILABLE_HOST.value);
        hostFilter2.put("ipmi_available",ZApiParameter.HOST_AVAILABLE.UNAVAILABLE_HOST.value);
        hostFilter2.put("jmx_available",ZApiParameter.HOST_AVAILABLE.UNAVAILABLE_HOST.value);
        hostFilter2.put("snmp_available",ZApiParameter.HOST_AVAILABLE.UNAVAILABLE_HOST.value);
        hostGetRequest2.getParams()
                .setGroupIds(platformIds)
                .setFilter(hostFilter2)
                .setSearchByAny(true)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> host2 ;
        try {
            host2 = hostManager.listHost(hostGetRequest2);
        } catch (ManagerException e) {
            e.printStackTrace();
            return 0;
        }
        //step5:去掉重复的主机并求和
        Set<BriefHostDTO> hosts = new HashSet<>();
        hosts.addAll(host1);
        hosts.addAll(host2);
        return hosts.size();
    }


    /**
     * 获取所有业务平台数量 hostgroup number
     * @return
     * @throws ServiceException
     */
    @Override
    public int countAllPlatform() throws ServiceException {
        HostGroupGetRequest groupRequest = new HostGroupGetRequest();
        groupRequest.getParams()
                .setMonitoredHosts(true)
                .setRealHosts(true)
                .setCountOutput(true);
        int hostGroupNum ;
        try {
            hostGroupNum = platformManager.countPlatform(groupRequest);
        } catch (ManagerException e) {
            e.printStackTrace();
            return 0;
        }
        return hostGroupNum;
    }

    /**
     * 获取问题业务平台数量 problem
     * @return
     * @throws ServiceException
     */
    @Override
    public int countProblemPlatform() throws ServiceException {
        //step1:获取问题触发器Ids
        List<String> triggerIds ;
        try {
            triggerIds = triggerManager.getProblemTriggerIds();
        } catch (ManagerException e) {
            e.printStackTrace();
            return 0;
        }
        //step2:根据触发器Ids获取业务平台数量
        HostGroupGetRequest groupRequest = new HostGroupGetRequest();
        groupRequest.getParams()
                .setTriggerIds(triggerIds)
                .setMonitoredHosts(true)
                .setRealHosts(true)
                .setCountOutput(true);
        int problemHostGroupNum ;
        try {
            problemHostGroupNum = platformManager.countPlatform(groupRequest);
        } catch (ManagerException e) {
            e.printStackTrace();
            return 0;
        }
        return problemHostGroupNum;
    }

    /**
     * 获取正常的业务平台数量 OK
     * @return
     * @throws ServiceException
     */
    @Override
    public int countOkPlatform() throws ServiceException {
        int OkPlatformNum = countAllPlatform() - countProblemPlatform();
        return OkPlatformNum;
    }

    /**
     * 获取所有的监控点
     * 根据筛选监控中的主机得到所有的监控点
     * @throws ServiceException
     */
    @Override
    public int countAllPoint() throws ServiceException {
        //step1:筛选所有监控中的主机monitored
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams()
                .setMonitoredHosts(true)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> hosts;
        try {
            hosts = hostManager.listHost(hostGetRequest);
        } catch (ManagerException e) {
            e.printStackTrace();
            return 0;
        }
        //step2:根据这些主机筛选其下的所有应用集hostids，即为所有的应用集（排出了停用状态，和模版中的应用集）
        List<String> hostIds = new ArrayList<>();
        if(hosts == null) {
            return 0;
        }
        for(BriefHostDTO host : hosts) {
            hostIds.add(host.getHostId());
        }
        ApplicationGetRequest appRequest = new ApplicationGetRequest();
        appRequest.getParams()
                .setHostIds(hostIds)
                .setCountOutput(true);
        int appALlNum ;
        try {
            appALlNum = pointManager.countPoint(appRequest);
        } catch (ManagerException e) {
            e.printStackTrace();
            return 0;
        }
        return appALlNum;
    }


    /**
     * 获取所有的问题监控点
     * 根据触发器获取监控点
     * @return int
     * @throws ServiceException
     */
    @Override
    public int countProblemPoint() throws ServiceException {
        //step1:获取问题触发器Ids
        List<String> triggerIds ;
        try {
            triggerIds = triggerManager.getProblemTriggerIds();
        } catch (ManagerException e) {
            e.printStackTrace();
            return 0;
        }
        //step2:根据触发器Ids获取items
        ItemGetRequest itemGetRequest = new ItemGetRequest();
        itemGetRequest.getParams()
                .setTriggerIds(triggerIds)
                .setMonitored(true)
                .setOutput(BriefItemDTO.PROPERTY_NAMES);
        List<BriefItemDTO> items ;
        try {
            items = itemManager.listItem(itemGetRequest);
        } catch (ManagerException e) {
            e.printStackTrace();
            return 0;
        }
        //step3:根据item筛选出应用集
        List<String> itemIds = new ArrayList<>();
        for(BriefItemDTO item : items) {
            itemIds.add(item.getItemId());
        }
        ApplicationGetRequest appRequest = new ApplicationGetRequest();
        appRequest.getParams()
                .setItemIds(itemIds)
                .setCountOutput(true);
        int appProblemNum ;
        try {
            appProblemNum = pointManager.countPoint(appRequest);
        } catch (ManagerException e) {
            e.printStackTrace();
            return 0;
        }
        return appProblemNum;

    }

    /**
     * 获取正常监控点 数量
     * okAppNum = allAppNum - problemAppNum
     * @return
     * @throws ServiceException
     */
    @Override
    public int countOkPoint() throws ServiceException {
        int okAppNum = countAllPoint() - countProblemPoint();
        return okAppNum;

    }

    /**
     * 获取指定主机的监控点数量
     * @param hostIds
     * @return
     * @throws ServiceException
     */
    @Override
    public int countAllPointByHostIds(List<String> hostIds) throws ServiceException {
        ApplicationGetRequest applicationGetRequest = new ApplicationGetRequest();
        applicationGetRequest.getParams()
                .setHostIds(hostIds)
                .setCountOutput(true);
        int pointsByHostId;
        try {
            pointsByHostId = pointManager.countPoint(applicationGetRequest);
        } catch (ManagerException e) {
            e.printStackTrace();
            return 0;
        }
        return pointsByHostId;
    }

    @Override
    public int countProblemPointByHostIds(List<String> hostIds) throws ServiceException {
        //step1:获取问题触发器Ids
        List<String> triggerIds ;
        try {
            triggerIds = triggerManager.getProblemTriggerIds();
        } catch (ManagerException e) {
            e.printStackTrace();
            return 0;
        }
        //step2:根据触发器Ids获取items
        ItemGetRequest itemGetRequest = new ItemGetRequest();
        itemGetRequest.getParams()
                .setTriggerIds(triggerIds)
                .setMonitored(true)
                .setOutput(BriefItemDTO.PROPERTY_NAMES);
        List<BriefItemDTO> items ;
        try {
            items = itemManager.listItem(itemGetRequest);
        } catch (ManagerException e) {
            e.printStackTrace();
            return 0;
        }
        //step3:根据item筛选出应用集
        List<String> itemIds = new ArrayList<>();
        for(BriefItemDTO item : items) {
            itemIds.add(item.getItemId());
        }
        //筛选指定主机的监控点
        ApplicationGetRequest appRequest = new ApplicationGetRequest();
        appRequest.getParams()
                .setItemIds(itemIds)
                .setHostIds(hostIds)
                .setCountOutput(true);
        int appProblemNum ;
        try {
            appProblemNum = pointManager.countPoint(appRequest);
        } catch (ManagerException e) {
            e.printStackTrace();
            return 0;
        }
        return appProblemNum;
    }


}
