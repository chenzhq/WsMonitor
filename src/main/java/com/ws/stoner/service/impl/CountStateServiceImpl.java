package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApiParameter;
import com.ws.bix4j.access.host.HostGetRequest;
import com.ws.bix4j.access.trigger.TriggerGetRequest;
import com.ws.bix4j.bean.TriggerDO;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.manager.HostManager;
import com.ws.stoner.manager.TriggerManager;
import com.ws.stoner.service.CountStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pc on 2017/6/12.
 */
@Service
public class CountStateServiceImpl implements CountStateService {

    @Autowired
    private HostManager hostManager;
    @Autowired
    private TriggerManager triggerManager;

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
        hostGetRequest.getParams().setFilter(statusFilter).setCountOutput(true);
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
        //step1:获取state:up to date 触发器list
        TriggerGetRequest triggerGetRequest1 = new TriggerGetRequest();
        Map<String, Object> triggerFilter = new HashMap<>();
        triggerFilter.put("state", ZApiParameter.TRIGGER_STATE.UP_TO_DATE.value);
        triggerFilter.put("value",ZApiParameter.TRIGGER_VALUE.PROBLEM.value);
        triggerFilter.put("only_true",true);
        triggerGetRequest1.getParams().setFilter(triggerFilter);
        List<TriggerDO> triggers1 ;
        try {
            triggers1 = triggerManager.listTrigger(triggerGetRequest1);
        } catch (ManagerException e) {
            e.printStackTrace();
            return 0;
        }
        //step2:获取state:unknown 触发器list
        TriggerGetRequest triggerGetRequest2 = new TriggerGetRequest();
        Map<String, Object> triggerFilter2 = new HashMap<>();
        triggerFilter.put("state", ZApiParameter.TRIGGER_STATE.UNKNOWN.value);
        triggerGetRequest2.getParams().setFilter(triggerFilter2);
        List<TriggerDO> triggers2 ;
        try {
            triggers2 = triggerManager.listTrigger(triggerGetRequest2);
        } catch (AuthExpireException e) {
            e.printStackTrace();
            return 0;
        }
        //step3:根据两个触发器的ids得到主机数量 hosts1
        List<String> triggerIds = new ArrayList<>();
        for(TriggerDO trigger : triggers1) {
            triggerIds.add(trigger.getTriggerId());
        }
        for(TriggerDO trigger : triggers2) {
            triggerIds.add(trigger.getTriggerId());
        }
        HostGetRequest hostGetRequest1 = new HostGetRequest();
        Map<String,Object> hostFilter1 = new HashMap<>();
        hostFilter1.put("status",ZApiParameter.HOST_MONITOR_STATUS.MONITORED_HOST.value);
        hostGetRequest1.getParams().setFilter(hostFilter1);
        hostGetRequest1.getParams().setTriggerIds(triggerIds).setCountOutput(true);
        int host1 = 0;
        try {
            host1 = hostManager.countHost(hostGetRequest1);
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
        hostGetRequest2.getParams().setFilter(hostFilter2).setSearchByAny(true);
        int host2 = 0;
        try {
            host2 = hostManager.countHost(hostGetRequest2);
        } catch (ManagerException e) {
            e.printStackTrace();
            return 0;
        }
        //step5:求和host1 + host2
        int hostNum = host1 + host2;
        return hostNum;
    }
}
