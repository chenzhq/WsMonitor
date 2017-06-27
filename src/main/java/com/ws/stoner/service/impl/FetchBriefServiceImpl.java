package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApiParameter;
import com.ws.bix4j.access.trigger.TriggerGetRequest;
import com.ws.stoner.constant.StatusEnum;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.manager.*;
import com.ws.stoner.model.dto.*;
import com.ws.stoner.model.view.BriefProblemVO;
import com.ws.stoner.model.view.DashboardHostVO;
import com.ws.stoner.model.view.DashboardPlatformVO;
import com.ws.stoner.model.view.DashboardPointVO;
import com.ws.stoner.service.FetchBriefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
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
    private PlatformManager platformManager;

    @Autowired
    private TemplateManager templateManager;

    /**
     * 组装业务host <DashboardHostVO>list
     * @return
     * @throws ServiceException
     */
    @Override
    public List<DashboardHostVO> listDashBoardHosts() throws ServiceException {
        //step1:取BriefHostDTO 类型所有主机allhostDTO
        List<BriefHostDTO> allhostDTO = null;
        List<BriefHostDTO> problemHostDTO = null;
        try {
            allhostDTO = hostManager.listAllHost();
            //step2:取BriefHostDTO 类型所有问题主机 并重组成hostids （String list）
            problemHostDTO = hostManager.listProblemHost();
        } catch (ManagerException e) {
            e.printStackTrace();
            return null;
        }
        List<String> problemHostIds = new ArrayList<>();
        for(BriefHostDTO problemHost : problemHostDTO) {
            problemHostIds.add(problemHost.getHostId());
        }
        //step3:获取所有模板allTemplateDTO
        List<BriefTemplateDTO> allTemplateDTO = null;
        try {
            allTemplateDTO = templateManager.listAllTemplate();
        } catch (ManagerException e) {
            e.printStackTrace();
            return null;
        }
        //step4:获取所有问题监控点
        List<BriefPointDTO> problemPointDTO = null;
        try {
            problemPointDTO = pointManager.listProblemPoint();
        } catch (ManagerException e) {
            e.printStackTrace();
            return null;
        }
        //step5:循环给DashBoardHostVO赋值
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
            hostVO.setAllNum(hostDTO.getPoints().size());
            //problemNum
            int prblemNum = 0;
            for(BriefPointDTO problemPoint : problemPointDTO) {
                if(problemPoint.getHostId().equals(hostDTO.getHostId())) {
                    prblemNum++;
                }
            }
            hostVO.setProblemNum(prblemNum);

            hostVOS.add(hostVO);
        }

        return hostVOS;
    }



    /**
     * 组装仪表板中的业务 platform <DashboardPlatformVO> list
     * @return
     * @throws ServiceException
     */
    @Override
    public List<DashboardPlatformVO> listDashboardPlatform() throws ServiceException {
        //step1:获取BriefPlatformDTO 类型的所有业务平台 allPlatformDTO
        List<BriefPlatformDTO> allPlatformDTO = null;
        try {
            allPlatformDTO = platformManager.listAllPlatform();
        } catch (ManagerException e) {
            e.printStackTrace();
            return null;
        }
        //step2:获取BriefPlatformDTO 类型的问题业务平台 problemPaltformDTO
        List<BriefPlatformDTO> problemPlatformDTO = null;
        try {
            problemPlatformDTO = platformManager.listProblemPlatform();
        } catch (ManagerException e) {
            e.printStackTrace();
            return null;
        }
        List<String> platformIds = new ArrayList<>();
        for(BriefPlatformDTO problemPlatform : problemPlatformDTO) {
            platformIds.add(problemPlatform.getPlatformId());
        }
        //step3:取所有监控中的主机，组装hostIds
        List<BriefHostDTO> hostDTOS = null;
        try {
            hostDTOS = hostManager.listAllHost();
        } catch (ManagerException e) {
            e.printStackTrace();
            return null;
        }
        List<String> hostIds = new ArrayList<>();
        for(BriefHostDTO host : hostDTOS) {
            hostIds.add(host.getHostId());
        }
        //step4:取所有问题主机,组装problemHostIds
        List<BriefHostDTO> problemHostDTOS = null;
        try {
            problemHostDTOS = hostManager.listProblemHost();
        } catch (ManagerException e) {
            e.printStackTrace();
            return null;
        }
        List<String> problemHostIds = new ArrayList<>();
        for(BriefHostDTO problemHost : problemHostDTOS) {
            problemHostIds.add(problemHost.getHostId());
        }
        //step5:新建List<DashboardPlatformVO>，循环allplatformDTO，新建DashboardPlatformVO，分别赋值
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
            int allNum = 0;
            for(BriefHostDTO host : platform.getHosts()) {
                if(hostIds.contains(host.getHostId())) {
                    allNum++;
                }
            }
            platformVO.setAllNum(allNum);
            //problemNum
            int problemNum = 0;
            for(BriefHostDTO host : platform.getHosts()) {
                if(problemHostIds.contains(host.getHostId())) {
                    problemNum++;
                }
            }
            platformVO.setProblemNum(problemNum);

            platformVOS.add(platformVO);
        }
        return platformVOS;
    }




    /**
     * 组装仪表板中的监控点 point <DashboardPointVO>list
     * @return
     * @throws ServiceException
     */
    @Override
    public List<DashboardPointVO> listDashboardPoint() throws ServiceException {
        //step1:获取BriefPointDTO 类型的所有启用的主机的监控点point allPointDTO
        List<BriefPointDTO> allPointDTO = null;
        try {
            allPointDTO = pointManager.listAllPoint();
        } catch (ManagerException e) {
            e.printStackTrace();
            return null;
        }
        //step2:获取BriefPointDTO 类型的所有启用的主机的问题监控点point problemPointDTO，并形成ids
        List<BriefPointDTO> problemPointDTO = null;
        try {
            problemPointDTO = pointManager.listProblemPoint();
        } catch (ManagerException e) {
            e.printStackTrace();
            return null;
        }
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
            //lastTime
            List<BriefItemDTO> items = point.getItems();
            if(items.size() != 0) {
                LocalDateTime lastTime = items.get(0).getLastTime();
                for(BriefItemDTO item : items) {
                    if(lastTime.compareTo(item.getLastTime()) < 0) {
                        lastTime = item.getLastTime();
                    }
                }
                pointVO.setLastTime(lastTime);
            }
            pointVOS.add(pointVO);
        }
        return pointVOS;
    }


    @Override
    public List<BriefProblemVO> listBriefProblems() {
        TriggerGetRequest request = new TriggerGetRequest();
        Map<String, Integer> triggerFilter = new HashMap<>();
        triggerFilter.put("state", ZApiParameter.TRIGGER_STATE.UP_TO_DATE.value);
        request.getParams()
                .setMonitored(true)
                .setOnlyTrue(true)
                .setSelectHosts(BriefHostDTO.PROPERTY_NAMES)
                .setOutput(BriefProblemVO.PROPERTY_NAMES)
                .setFilter(triggerFilter);
        return triggerManager.listTrigger(request, BriefProblemVO.class);
    }
}
