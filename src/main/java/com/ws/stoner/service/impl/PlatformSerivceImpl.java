package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.ZApiParameter;
import com.ws.bix4j.access.host.HostGetRequest;
import com.ws.bix4j.access.hostgroup.HostGroupGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.service.HostSerivce;
import com.ws.stoner.service.PlatformSerivce;
import com.ws.stoner.service.TriggerSerivce;
import com.ws.stoner.model.dto.BriefHostDTO;
import com.ws.stoner.model.dto.BriefPlatformDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by pc on 2017/6/1.
 */
@Service
public class PlatformSerivceImpl implements PlatformSerivce {
    private static final Logger logger = LoggerFactory.getLogger(PlatformSerivceImpl.class);
    @Autowired
    private ZApi zApi;

    @Autowired
    private HostSerivce hostSerivce;

    @Autowired
    private TriggerSerivce triggerSerivce;

    @Override
    public List<BriefPlatformDTO> listPlatform(HostGroupGetRequest request) throws ManagerException {
        List<BriefPlatformDTO> groups;
        try {
            groups = zApi.Group().get(request, BriefPlatformDTO.class);
        } catch (ZApiException e) {
            if (e.getCode().equals(ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE)) {
                throw new ManagerException("");
            }
            e.printStackTrace();
            logger.error("查询业务平台错误！{}", e.getMessage());
            return null;
        }
        return groups;
    }

    /**
     * 根据request获取业务平台数量
     * @return
     * @throws ManagerException
     */
    @Override
    public int countPlatform(HostGroupGetRequest request) throws ManagerException {
        int hostGroupNum ;
        try {
            hostGroupNum = zApi.Group().count(request);
        } catch (ZApiException e) {
            e.printStackTrace();
            return 0;
        }
        return hostGroupNum;
    }

    /**
     * 获取指定业务平台的所有主机数量 all host number by platformIds
     * @return
     * @throws ManagerException
     */
    @Override
    public int countAllHostByPlatformIds(List<String> platformIds) throws ManagerException {
        HostGetRequest hostGetRequest = new HostGetRequest();
        Map<String, Integer> statusFilter = new HashMap<>();
        statusFilter.put("status", ZApiParameter.HOST_MONITOR_STATUS.MONITORED_HOST.value);
        hostGetRequest.getParams()
                .setGroupIds(platformIds)
                .setFilter(statusFilter)
                .setCountOutput(true);
        int allHostNum = hostSerivce.countHost(hostGetRequest);
        return allHostNum;
    }

    /**
     * 获取指定业务平台的问题主机数量 problem host number by platformIds
     * @param platformIds
     * @return
     * @throws ManagerException
     */
    @Override
    public int countProblemHostByPlatformIds(List<String> platformIds) throws ManagerException {
        //step1:获取问题触发器ids
        List<String> triggerIds = triggerSerivce.getProblemTriggerIds();
        //step2:根据两个触发器的ids得到主机数量 hosts1
        HostGetRequest hostGetRequest1 = new HostGetRequest();
        Map<String,Object> hostFilter1 = new HashMap<>();
        hostFilter1.put("status",ZApiParameter.HOST_MONITOR_STATUS.MONITORED_HOST.value);
        hostGetRequest1.getParams()
                .setTriggerIds(triggerIds)
                .setGroupIds(platformIds)
                .setFilter(hostFilter1)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> host1  = hostSerivce.listHost(hostGetRequest1);
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
        List<BriefHostDTO> host2 = hostSerivce.listHost(hostGetRequest2);
        //step5:去掉重复的主机并求和
        Set<BriefHostDTO> hosts = new HashSet<>();
        hosts.addAll(host1);
        hosts.addAll(host2);
        return hosts.size();
    }


    /**
     * 获取所有业务平台数量 hostgroup number
     * @return
     * @throws ManagerException
     */
    @Override
    public int countAllPlatform() throws ManagerException {
        HostGroupGetRequest groupRequest = new HostGroupGetRequest();
        groupRequest.getParams()
                .setMonitoredHosts(true)
                .setRealHosts(true)
                .setCountOutput(true);
        int hostGroupNum  = countPlatform(groupRequest);
        return hostGroupNum;
    }

    /**
     * 获取警告业务平台数量 warning
     * 根据custom_state字段判断
     * @return
     * @throws ManagerException
     */
    @Override
    public int countWarningPlatform() throws ManagerException {
        //step1:根据custom_state字段判断
        HostGroupGetRequest groupRequest = new HostGroupGetRequest();
        Map<String,Object> groupFilter = new HashMap<>();
        groupFilter.put("custom_state",ZApiParameter.OBJECT_STATE.CUSTOM_STATE_WARNING.value);
        groupRequest.getParams()
                .setMonitoredHosts(true)
                .setRealHosts(true)
                .setFilter(groupFilter)
                .setCountOutput(true);
        int warningHostGroupNum  = countPlatform(groupRequest);
        return warningHostGroupNum;
    }

    /**
     * 获取严重业务平台数量 hight
     * 根据custom_state字段判断
     * @return
     * @throws ManagerException
     */
    @Override
    public int countHightPlatform() throws ManagerException {
        //step1:根据custom_state字段判断
        HostGroupGetRequest groupRequest = new HostGroupGetRequest();
        Map<String,Object> groupFilter = new HashMap<>();
        groupFilter.put("custom_state",ZApiParameter.OBJECT_STATE.CUSTOM_STATE_HIGHT.value);
        groupRequest.getParams()
                .setMonitoredHosts(true)
                .setRealHosts(true)
                .setFilter(groupFilter)
                .setCountOutput(true);
        int hightHostGroupNum  = countPlatform(groupRequest);
        return hightHostGroupNum;
    }

    /**
     * 获取正常的业务平台数量 OK
     * @return
     * @throws ManagerException
     */
    @Override
    public int countOkPlatform() throws ManagerException {
        //step1:根据custom_state字段判断
        HostGroupGetRequest groupRequest = new HostGroupGetRequest();
        Map<String,Object> groupFilter = new HashMap<>();
        groupFilter.put("custom_state",ZApiParameter.OBJECT_STATE.CUSTOM_STATE_OK.value);
        groupRequest.getParams()
                .setMonitoredHosts(true)
                .setRealHosts(true)
                .setFilter(groupFilter)
                .setCountOutput(true);
        int okHostGroupNum  = countPlatform(groupRequest);
        return okHostGroupNum;
    }

/*
 *list platform
 */


    /**
     * 获取简约listPlatform list all
     * @return
     * @throws ManagerException
     */
    @Override
    public List<BriefPlatformDTO> listAllPlatform() throws ManagerException {
        HostGroupGetRequest groupRequest = new HostGroupGetRequest();
        groupRequest.getParams()
                .setMonitoredHosts(true)
                .setRealHosts(true)
                .setSelectHosts(BriefHostDTO.PROPERTY_NAMES)
                .setOutput(BriefPlatformDTO.PROPERTY_NAMES);
        List<BriefPlatformDTO> platforms  = listPlatform(groupRequest);
        return platforms;
    }

    /**
     * 获取警告的 platform list warning
     * @return
     * @throws ManagerException
     */
    @Override
    public List<BriefPlatformDTO> listWarningPlatform() throws ManagerException {
        HostGroupGetRequest groupRequest = new HostGroupGetRequest();
        Map<String, Object> platformFilter = new HashMap<>();
        platformFilter.put("custom_state",ZApiParameter.OBJECT_STATE.CUSTOM_STATE_WARNING);
        groupRequest.getParams()
                .setMonitoredHosts(true)
                .setRealHosts(true)
                .setFilter(platformFilter)
                .setOutput(BriefPlatformDTO.PROPERTY_NAMES);
        List<BriefPlatformDTO> problemPlatforms  = listPlatform(groupRequest);
        return problemPlatforms;
    }

    /**
     * 获取严重的 platform list hight
     * @return
     * @throws ManagerException
     */
    @Override
    public List<BriefPlatformDTO> listHightPlatform() throws ManagerException {
        HostGroupGetRequest groupRequest = new HostGroupGetRequest();
        Map<String, Object> platformFilter = new HashMap<>();
        platformFilter.put("custom_state",ZApiParameter.OBJECT_STATE.CUSTOM_STATE_HIGHT);
        groupRequest.getParams()
                .setMonitoredHosts(true)
                .setRealHosts(true)
                .setFilter(platformFilter)
                .setOutput(BriefPlatformDTO.PROPERTY_NAMES);
        List<BriefPlatformDTO> problemPlatforms  = listPlatform(groupRequest);
        return problemPlatforms;
    }
}
