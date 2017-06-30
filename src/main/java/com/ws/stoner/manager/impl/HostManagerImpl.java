package com.ws.stoner.manager.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.ZApiParameter;
import com.ws.bix4j.access.host.HostGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.manager.HostManager;
import com.ws.stoner.manager.TriggerManager;
import com.ws.stoner.model.dto.BriefHostDTO;
import com.ws.stoner.model.dto.BriefHostInterfaceDTO;
import com.ws.stoner.model.dto.BriefPointDTO;
import com.ws.stoner.model.dto.BriefTemplateDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenzheqi on 2017/5/2.
 */
@Service
public class HostManagerImpl implements HostManager {
    private static final Logger logger = LoggerFactory.getLogger(HostManagerImpl.class);
    @Autowired
    private ZApi zApi;

    @Autowired
    private TriggerManager triggerManager;

/*
 *count host
 */

    @Override
    public int countHost(HostGetRequest request) throws ManagerException {
        int allHost;
        try {
            allHost = zApi.Host().count(request);
        } catch (ZApiException e) {
            if (e.getCode().equals(ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE)) {
                throw new ManagerException("");
            }
            e.printStackTrace();
            logger.error("查询主机错误！{}", e.getMessage());
            return 0;
        }
        return allHost;
    }

    /**
     * 获取所有的主机列表
     * @return
     * @throws ManagerException
     */
    @Override
    public List<BriefHostDTO> listHost(HostGetRequest request) throws ManagerException {
        List<BriefHostDTO> hosts;
        try {
            hosts = zApi.Host().get(request,BriefHostDTO.class);
        } catch (ZApiException e) {
            if (e.getCode().equals(ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE)) {
                throw new ManagerException("");
            }
            e.printStackTrace();
            logger.error("查询主机错误！{}", e.getMessage());
            return null;
        }
        return hosts;
    }

    /**
     * 获取主机总数量，排除停用主机，filter： status:0
     * @return
     * @throws ServiceException
     */
    @Override
    public int countAllHost() throws ManagerException {
        HostGetRequest hostGetRequest = new HostGetRequest();
        Map<String, Integer> statusFilter = new HashMap<>();
        statusFilter.put("status", ZApiParameter.HOST_MONITOR_STATUS.MONITORED_HOST.value);
        hostGetRequest.getParams()
                .setFilter(statusFilter)
                .setCountOutput(true);
        int allHostNum = countHost(hostGetRequest);
        return allHostNum;
    }

    /**
     * 获取问题主机数量
     * 1、根据触发器状态获取问题主机
     * filter: a.  state:up to date  value:problem  only_true:true
     *         b.  state:unknown
     * 2、筛选四种监控接口中至少一个有问题的主机，这是另一部分问题状态的主机filter+searchByAny
     * @return
     * @throws ManagerException
     */
    @Override
    public int countProblemHost(List<String> triggerIds) throws ManagerException {
        //step1:获取问题触发器ids，参数传入
        //step2:根据两个触发器的ids得到主机数量 hosts1
        HostGetRequest hostGetRequest1 = new HostGetRequest();
        Map<String,Object> hostFilter1 = new HashMap<>();
        hostFilter1.put("status",ZApiParameter.HOST_MONITOR_STATUS.MONITORED_HOST.value);
        hostGetRequest1.getParams()
                .setTriggerIds(triggerIds)
                .setOutput(BriefHostDTO.PROPERTY_NAMES)
                .setFilter(hostFilter1);
        List<BriefHostDTO> host1 = listHost(hostGetRequest1);
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
        List<BriefHostDTO> host2 = listHost(hostGetRequest2) ;
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
     * @throws ManagerException
     */
    @Override
    public int countOkHost(List<String> triggerIds) throws ManagerException {
        int okHostNum = countAllHost() - countProblemHost(triggerIds);
        return okHostNum;
    }

/*
 *list host
 */

    /**
     * 获取简约所有主机list 剔除停用的
     * @return
     * @throws ManagerException
     */
    @Override
    public List<BriefHostDTO> listAllHost() throws ManagerException {
        HostGetRequest hostGetRequest = new HostGetRequest();
        Map<String, Integer> statusFilter = new HashMap<>();
        statusFilter.put("status", ZApiParameter.HOST_MONITOR_STATUS.MONITORED_HOST.value);
        hostGetRequest.getParams().setFilter(statusFilter);
        hostGetRequest.getParams()
                .setSelectInterfaces(BriefHostInterfaceDTO.PROPERTY_NAMES)
                .setSelectParentTemplates(BriefTemplateDTO.PROPERTY_NAMES)
                .setSelectApplications(BriefPointDTO.PROPERTY_NAMES)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> hosts  = listHost(hostGetRequest);
        return hosts;
    }

    /**
     * 获取问题主机 list  problem
     * @return
     * @throws ManagerException
     */
    @Override
    public List<BriefHostDTO> listProblemHost(List<String> triggerIds) throws ManagerException {
        //step1:获取问题触发器ids 参数传入
        //step2:根据两个触发器的ids得到主机list hosts1
        HostGetRequest hostGetRequest1 = new HostGetRequest();
        Map<String,Object> hostFilter1 = new HashMap<>();
        hostFilter1.put("status",ZApiParameter.HOST_MONITOR_STATUS.MONITORED_HOST.value);
        hostGetRequest1.getParams().setFilter(hostFilter1);
        hostGetRequest1.getParams()
                .setTriggerIds(triggerIds)
                .setSelectInterfaces(BriefHostInterfaceDTO.PROPERTY_NAMES)
                .setSelectParentTemplates(BriefTemplateDTO.PROPERTY_NAMES)
                .setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> host1 = listHost(hostGetRequest1);
        //step4:筛选四种监控接口中至少一个有问题的主机数量 host2
        HostGetRequest hostGetRequest2 = new HostGetRequest();
        Map<String, Object> hostFilter2 = new HashMap<>();
        hostFilter2.put("monitored_hosts",true);
        hostFilter2.put("available",ZApiParameter.HOST_AVAILABLE.UNAVAILABLE_HOST.value);
        hostFilter2.put("ipmi_available",ZApiParameter.HOST_AVAILABLE.UNAVAILABLE_HOST.value);
        hostFilter2.put("jmx_available",ZApiParameter.HOST_AVAILABLE.UNAVAILABLE_HOST.value);
        hostFilter2.put("snmp_available",ZApiParameter.HOST_AVAILABLE.UNAVAILABLE_HOST.value);
        hostGetRequest2.getParams()
                .setSelectInterfaces(BriefHostInterfaceDTO.PROPERTY_NAMES)
                .setSelectParentTemplates(BriefTemplateDTO.PROPERTY_NAMES)
                .setOutput(BriefHostDTO.PROPERTY_NAMES)
                .setFilter(hostFilter2)
                .setSearchByAny(true);
        List<BriefHostDTO> host2 = listHost(hostGetRequest2);
        //step5:拼接host1 + host2
        host1.removeAll(host2);
        host1.addAll(host2);
        return host1;

    }

    /**
     * 获取OK主机 list OK
     * @return
     * @throws ManagerException
     */
    @Override
    public List<BriefHostDTO> listOkHost(List<String> triggerIds) throws ManagerException {
        List<BriefHostDTO> allHosts = listAllHost();
        List<BriefHostDTO> problemHosts = listProblemHost(triggerIds);
        allHosts.removeAll(problemHosts);
        return allHosts;
    }


}
