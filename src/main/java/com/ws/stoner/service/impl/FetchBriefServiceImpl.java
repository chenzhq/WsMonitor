package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApiParameter;
import com.ws.bix4j.access.trigger.TriggerGetRequest;
import com.ws.stoner.manager.TriggerManager;
import com.ws.stoner.model.dto.BriefHostDTO;
import com.ws.stoner.model.view.BriefProblemVO;
import com.ws.bix4j.access.application.ApplicationGetRequest;
import com.ws.bix4j.access.host.HostGetRequest;
import com.ws.bix4j.access.hostgroup.HostGroupGetRequest;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.manager.*;
import com.ws.stoner.model.brief.ApplicationBrief;
import com.ws.stoner.model.brief.HostBrief;
import com.ws.stoner.model.brief.HostGroupBrief;
import com.ws.stoner.service.FetchBriefService;
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
public class FetchBriefServiceImpl implements FetchBriefService {

    @Autowired
    private HostManager hostManager;

    @Autowired
    private TriggerManager triggerManager;

    @Autowired
    private ApplicationManager appManager;

    @Autowired
    private ItemManager itemManager;

    @Autowired
    private PlatformManager platformManager;

    /**
     * 获取简约所有主机list 剔除停用的
     * @return
     * @throws ServiceException
     */
    @Override
    public List<HostBrief> listHost() throws ServiceException {
        HostGetRequest hostGetRequest = new HostGetRequest();
        Map<String, Integer> statusFilter = new HashMap<>();
        statusFilter.put("status", ZApiParameter.HOST_MONITOR_STATUS.MONITORED_HOST.value);
        hostGetRequest.getParams().setFilter(statusFilter);
        List<HostBrief> hosts ;
        try {
            hosts = hostManager.listHost(hostGetRequest);
        } catch (AuthExpireException e) {
            e.printStackTrace();
            return null;
        }
        return hosts;
    }

    /**
     * 获取问题主机 list  problem
     * @return
     * @throws ServiceException
     */
    @Override
    public List<HostBrief> listProblemHost() throws ServiceException {
        //step1:获取问题触发器ids
        List<String> triggerIds ;
        try {
            triggerIds = triggerManager.getProblemTriggerIds();
        } catch (ManagerException e) {
            e.printStackTrace();
            return null;
        }
        //step2:根据两个触发器的ids得到主机数量 hosts1
        HostGetRequest hostGetRequest1 = new HostGetRequest();
        Map<String,Object> hostFilter1 = new HashMap<>();
        hostFilter1.put("status",ZApiParameter.HOST_MONITOR_STATUS.MONITORED_HOST.value);
        hostGetRequest1.getParams().setFilter(hostFilter1);
        hostGetRequest1.getParams().setTriggerIds(triggerIds);
        List<HostBrief> host1 ;
        try {
            host1 = hostManager.listHost(hostGetRequest1);
        } catch (ManagerException e) {
            e.printStackTrace();
            return null;
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
        List<HostBrief> host2 ;
        try {
            host2 = hostManager.listHost(hostGetRequest2);
        } catch (ManagerException e) {
            e.printStackTrace();
            return null;
        }
        //step5:拼接host1 + host2
        host1.addAll(host2);
        return host1;

    }

    /**
     * 获取OK主机 list OK
     * @return
     * @throws ServiceException
     */
    @Override
    public List<HostBrief> listOkHost() throws ServiceException {
        List<HostBrief> allHosts = listHost();
        List<HostBrief> problemHosts = listProblemHost();
        List<HostBrief> OkHosts = new ArrayList<>();
        Map<String,HostBrief> hostKey = new HashMap<>();
        for(HostBrief phost : problemHosts) {
            hostKey.put(phost.getHostId(),phost);
        }
        for(HostBrief host : allHosts) {
            if(hostKey.get(host.getHostId()) == null) {
                OkHosts.add(host);
            }
        }
        return OkHosts;
    }

    /**
     * 获取简约listPlatform list
     * @return
     * @throws ServiceException
     */
    @Override
    public List<HostGroupBrief> listPlatform() throws ServiceException {
        HostGroupGetRequest groupRequest = new HostGroupGetRequest();
        groupRequest.getParams().setMonitoredHosts(true).setRealHosts(true);
        groupRequest.getParams();
        List<HostGroupBrief> hostGroups ;
        try {
            hostGroups = platformManager.listPlatform(groupRequest);
        } catch (AuthExpireException e) {
            e.printStackTrace();
            return null;
        }
        return hostGroups;
    }

    /**
     * 获取简约监控点application list
     * @return
     * @throws ServiceException
     */
    @Override
    public List<ApplicationBrief> listApp() throws ServiceException {
        //step1:获取监控中的主机
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams().setMonitoredHosts(true);
        List<HostBrief> hosts;
        try {
            hosts = hostManager.listHost(hostGetRequest);
        } catch (AuthExpireException e) {
            e.printStackTrace();
            return null;
        }
        //step2:根据主机ids获取所有的监控点
        List<String> hostIds = new ArrayList<>();
        if(hosts == null) {
            return null;
        }
        for(HostBrief host : hosts) {
            hostIds.add(host.getHostId());
        }
        ApplicationGetRequest appRequest = new ApplicationGetRequest();
        appRequest.getParams().setHostIds(hostIds);
        List<ApplicationBrief> apps;
        try {
            apps = appManager.listApplication(appRequest);
        } catch (AuthExpireException e) {
            e.printStackTrace();
            return null;
        }
        return apps;
    }

    @Override
    public List<BriefProblemVO> listBriefProblems() {
        TriggerGetRequest request = new TriggerGetRequest();
        Map<String, Integer> triggerFilter = new HashMap<>();
        triggerFilter.put("state", ZApiParameter.TRIGGER_STATE.UP_TO_DATE.value);
        request.getParams()
                .setMonitored(true)
                .setOnlyTrue(true)
                .setListHosts(BriefHostDTO.PROPERTY_NAMES)
                .setOutput(BriefProblemVO.PROPERTY_NAMES)
                .setFilter(triggerFilter);
        return triggerManager.listTrigger(request, BriefProblemVO.class);
    }
}
