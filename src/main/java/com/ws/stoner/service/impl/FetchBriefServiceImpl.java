package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApiParameter;
import com.ws.bix4j.access.application.ApplicationGetRequest;
import com.ws.bix4j.access.host.HostGetRequest;
import com.ws.bix4j.access.hostgroup.HostGroupGetRequest;
import com.ws.bix4j.access.item.ItemGetRequest;
import com.ws.bix4j.access.template.TemplateGetRequest;
import com.ws.bix4j.access.trigger.TriggerGetRequest;
import com.ws.stoner.constant.StatusEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.manager.*;
import com.ws.stoner.model.dto.*;
import com.ws.stoner.model.view.BriefProblemVO;
import com.ws.stoner.model.view.DashboardHostVO;
import com.ws.stoner.model.view.DashboardPlatformVO;
import com.ws.stoner.model.view.DashboardPointVO;
import com.ws.stoner.service.CountStateService;
import com.ws.stoner.service.FetchBriefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private PointManager pointManager;

    @Autowired
    private ItemManager itemManager;

    @Autowired
    private PlatformManager platformManager;

    @Autowired
    private TemplateManager templateManager;

    @Autowired
    private CountStateService countStateService;

    /**
     * 组装业务host <DashboardHostVO>list
     * @return
     * @throws ServiceException
     */
    @Override
    public List<DashboardHostVO> listDashBoardHosts() throws ServiceException {
        //step1:取BriefHostDTO 类型所有主机allhostDTO
        List<BriefHostDTO> allhostDTO = listHost();
        //step2:取BriefHostDTO 类型所有问题主机 并重组成hostids （String list）
        List<BriefHostDTO> problemHostDTO = listProblemHost();
        List<String> problemHostIds = new ArrayList<>();
        for(BriefHostDTO problemHost : problemHostDTO) {
            problemHostIds.add(problemHost.getHostId());
        }
        //step3:获取所有模板allTemplateDTO
        List<BriefTemplateDTO> allTemplateDTO = listAllTemplate();
        //step4:循环给DashBoardHostVO赋值
        List<DashboardHostVO> hostVOS = new ArrayList<>();
        for(BriefHostDTO hostDTO : allhostDTO) {
            DashboardHostVO hostVO = new DashboardHostVO();
            //hostid,name赋值
            hostVO.setHostId(hostDTO.getHostId());
            hostVO.setName(hostDTO.getName());
            //ip
            hostVO.setIp(hostDTO.getInterfaces().get(0).getIp());
            //state
            if(problemHostIds.contains(hostDTO.getHostId())) {
                hostVO.setState(StatusEnum.PROBLEM.getName());
            }else {
                hostVO.setState(StatusEnum.OK.getName());
            }
            //type
            if(hostDTO.getParentTemplates().size() != 0) {
                String DTOTemplateId = hostDTO.getParentTemplates().get(0).getTemplateId();
                for(BriefTemplateDTO template : allTemplateDTO) {
                    if(template.getTemplateId().equals(DTOTemplateId)) {
                        hostVO.setType(template.getTemplateGroups().get(0).getName());
                    }
                }
            }

            //allNum
            hostVO.setAllNum(countStateService.countAllPointByHostIds(Arrays.asList(hostDTO.getHostId())));
            //problemNum
            hostVO.setProblemNum(countStateService.countProblemPointByHostIds(Arrays.asList(hostDTO.getHostId())));

            hostVOS.add(hostVO);
        }

        return hostVOS;
    }

    /**
     * 获取简约所有主机list 剔除停用的
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefHostDTO> listHost() throws ServiceException {
        HostGetRequest hostGetRequest = new HostGetRequest();
        Map<String, Integer> statusFilter = new HashMap<>();
        statusFilter.put("status", ZApiParameter.HOST_MONITOR_STATUS.MONITORED_HOST.value);
        hostGetRequest.getParams().setFilter(statusFilter);
        hostGetRequest.getParams().setListInterfaces(BriefHostInterfaceDTO.PROPERTY_NAMES).setListParentTemplates(BriefTemplateDTO.PROPERTY_NAMES).setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> hosts ;
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
    public List<BriefHostDTO> listProblemHost() throws ServiceException {
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
        hostGetRequest1.getParams().setListInterfaces(BriefHostInterfaceDTO.PROPERTY_NAMES).setListParentTemplates(BriefTemplateDTO.PROPERTY_NAMES).setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> host1 ;
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
        hostGetRequest2.getParams().setListInterfaces(BriefHostInterfaceDTO.PROPERTY_NAMES).setListParentTemplates(BriefTemplateDTO.PROPERTY_NAMES).setOutput(BriefHostDTO.PROPERTY_NAMES);
        List<BriefHostDTO> host2 ;
        try {
            host2 = hostManager.listHost(hostGetRequest2);
        } catch (ManagerException e) {
            e.printStackTrace();
            return null;
        }
        //step5:拼接host1 + host2
        host1.removeAll(host2);
        host1.addAll(host2);
        return host1;

    }

    /**
     * 获取OK主机 list OK
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefHostDTO> listOkHost() throws ServiceException {
        List<BriefHostDTO> allHosts = listHost();
        List<BriefHostDTO> problemHosts = listProblemHost();
        List<BriefHostDTO> OkHosts = new ArrayList<>();
        Map<String,BriefHostDTO> hostKey = new HashMap<>();
        for(BriefHostDTO phost : problemHosts) {
            hostKey.put(phost.getHostId(),phost);
        }
        for(BriefHostDTO host : allHosts) {
            if(hostKey.get(host.getHostId()) == null) {
                OkHosts.add(host);
            }
        }
        return OkHosts;
    }

    @Override
    public List<BriefTemplateDTO> listAllTemplate() throws ServiceException {
        TemplateGetRequest templateGetRequest = new TemplateGetRequest();
        templateGetRequest.getParams().setListGroups(BriefTemplateGroupDTO.PROPERTY_NAMES).setOutput(BriefTemplateDTO.PROPERTY_NAMES);
        List<BriefTemplateDTO> templatesDTO;
        try {
            templatesDTO = templateManager.listTemplate(templateGetRequest);
        } catch (AuthExpireException e) {
            e.printStackTrace();
            return null;
        }

        return templatesDTO;
    }

    /**
     * 组装仪表板中的业务 platform <DashboardPlatformVO> list
     * @return
     * @throws ServiceException
     */
    @Override
    public List<DashboardPlatformVO> listDashboardPlatform() throws ServiceException {
        //step1:获取BriefPlatformDTO 类型的所有业务平台 allPlatformDTO
        List<BriefPlatformDTO> allPlatformDTO = listPlatform();
        //step2:获取BriefPlatformDTO 类型的问题业务平台 problemPaltformDTO
        List<BriefPlatformDTO> problemPlatformDTO = listProblemPlatform();
        List<String> platformIds = new ArrayList<>();
        for(BriefPlatformDTO problemPlatform : problemPlatformDTO) {
            platformIds.add(problemPlatform.getPlatformId());
        }
        //step3:新建List<DashboardPlatformVO>，循环allplatformDTO，新建DashboardPlatformVO，分别赋值
        List<DashboardPlatformVO> platformVOS = new ArrayList<>();
        for(BriefPlatformDTO platform : allPlatformDTO) {
            DashboardPlatformVO platformVO = new DashboardPlatformVO();
            //赋值 id,name,availability
            platformVO.setPlatformId(platform.getPlatformId());
            platformVO.setName(platform.getName());
            platformVO.setAvailability(100);
            //state
            if(platformIds.contains(platform.getPlatformId())) {
                platformVO.setState(StatusEnum.PROBLEM.getName());
            }else {
                platformVO.setState(StatusEnum.OK.getName());
            }
            //allNum
            platformVO.setAllNum(countStateService.countAllHostByPlatformIds(Arrays.asList(platform.getPlatformId())));
            //problemNum
            platformVO.setProblemNum(countStateService.countProblemHostByPlatformIds(Arrays.asList(platform.getPlatformId())));

            platformVOS.add(platformVO);
        }
        return platformVOS;
    }

    /**
     * 获取简约listPlatform list all
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefPlatformDTO> listPlatform() throws ServiceException {
        HostGroupGetRequest groupRequest = new HostGroupGetRequest();
        groupRequest.getParams().setMonitoredHosts(true).setRealHosts(true);
        groupRequest.getParams().setOutput(BriefPlatformDTO.PROPERTY_NAMES);
        List<BriefPlatformDTO> platforms ;
        try {
            platforms = platformManager.listPlatform(groupRequest);
        } catch (AuthExpireException e) {
            e.printStackTrace();
            return null;
        }
        return platforms;
    }

    /**
     * 获取问题的 platform list problem
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefPlatformDTO> listProblemPlatform() throws ServiceException {
        //step1:获取问题触发器Ids
        List<String> triggerIds ;
        try {
            triggerIds = triggerManager.getProblemTriggerIds();
        } catch (ManagerException e) {
            e.printStackTrace();
            return null;
        }
        //step2:根据触发器Ids获取业务平台数量
        HostGroupGetRequest groupRequest = new HostGroupGetRequest();
        groupRequest.getParams().setTriggerIds(triggerIds);
        groupRequest.getParams().setMonitoredHosts(true).setRealHosts(true);
        groupRequest.getParams().setOutput(BriefPlatformDTO.PROPERTY_NAMES);
        List<BriefPlatformDTO> problemPlatforms ;
        try {
            problemPlatforms = platformManager.listPlatform(groupRequest);
        } catch (ManagerException e) {
            e.printStackTrace();
            return null;
        }
        return problemPlatforms;
    }


    /**
     * 组装仪表板中的监控点 point <DashboardPointVO>list
     * @return
     * @throws ServiceException
     */
    @Override
    public List<DashboardPointVO> listDashboardPoint() throws ServiceException {
        //step1:获取BriefPointDTO 类型的所有启用的主机的监控点point allPointDTO
        List<BriefPointDTO> allPointDTO = listPoint();
        //step2:获取BriefPointDTO 类型的所有启用的主机的问题监控点point problemPointDTO，并形成ids
        List<BriefPointDTO> problemPointDTO = listProblemPoint();
        List<String> problemPointIds = new ArrayList<>();
        for(BriefPointDTO problemPoint : problemPointDTO) {
            problemPointIds.add(problemPoint.getPointId());
        }
        //step3:新建List<DashboardPointVO>，循环allPointDTO，新建DashboardPointVO，分别赋值
        List<DashboardPointVO> pointVOS = new ArrayList<>();
        for(BriefPointDTO point :allPointDTO) {
            DashboardPointVO pointVO = new DashboardPointVO();
            //赋值 id,name,hostid,hostname
            pointVO.setPointId(point.getPointId());
            pointVO.setName(point.getName());
            pointVO.setHostId(point.getHostId());
            pointVO.setHostName(point.getHost().getName());
            //state
            if(problemPointIds.contains(point.getPointId())) {
                pointVO.setState(StatusEnum.PROBLEM.getName());
            }else {
                pointVO.setState(StatusEnum.OK.getName());
            }
            pointVOS.add(pointVO);
        }
        return pointVOS;
    }

    /**
     * 获取简约监控点 point list
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefPointDTO> listPoint() throws ServiceException {
        //step1:获取监控中的主机
        HostGetRequest hostGetRequest = new HostGetRequest();
        hostGetRequest.getParams().setMonitoredHosts(true);
        List<BriefHostDTO> hosts;
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
        for(BriefHostDTO host : hosts) {
            hostIds.add(host.getHostId());
        }
        ApplicationGetRequest appRequest = new ApplicationGetRequest();
        appRequest.getParams().setHostIds(hostIds).setListHost(BriefHostDTO.PROPERTY_NAMES).setOutput(BriefPointDTO.PROPERTY_NAMES);
        List<BriefPointDTO> points;
        try {
            points = pointManager.listPoint(appRequest);
        } catch (AuthExpireException e) {
            e.printStackTrace();
            return null;
        }
        return points;
    }

    /**
     * 获取问题监控点 point list
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefPointDTO> listProblemPoint() throws ServiceException {
        //step1:获取问题触发器Ids
        List<String> triggerIds ;
        try {
            triggerIds = triggerManager.getProblemTriggerIds();
        } catch (ManagerException e) {
            e.printStackTrace();
            return null;
        }
        //step2:根据触发器Ids获取items
        ItemGetRequest itemGetRequest = new ItemGetRequest();
        itemGetRequest.getParams().setTriggerIds(triggerIds);
        itemGetRequest.getParams().setMonitored(true).setOutput(BriefItemDTO.PROPERTY_NAMES);
        List<BriefItemDTO> items ;
        try {
            items = itemManager.listItem(itemGetRequest);
        } catch (AuthExpireException e) {
            e.printStackTrace();
            return null;
        }
        //step3:根据item筛选出应用集
        List<String> itemIds = new ArrayList<>();
        for(BriefItemDTO item : items) {
            itemIds.add(item.getItemId());
        }
        ApplicationGetRequest appRequest = new ApplicationGetRequest();
        appRequest.getParams().setListHost(BriefHostDTO.PROPERTY_NAMES);
        appRequest.getParams().setItemIds(itemIds);
        appRequest.getParams().setOutput(BriefPointDTO.PROPERTY_NAMES);
        List<BriefPointDTO> problemPoints ;
        try {
            problemPoints = pointManager.listPoint(appRequest);
        } catch (ManagerException e) {
            e.printStackTrace();
            return null;
        }

        return problemPoints;
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
