package com.ws.stoner.controller.rest;

import com.ws.stoner.constant.StatusEnum;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.*;
import com.ws.stoner.model.view.DashboardHostVO;
import com.ws.stoner.model.view.DashboardPlatformVO;
import com.ws.stoner.model.view.DashboardPointVO;
import com.ws.stoner.model.view.StateNumVO;
import com.ws.stoner.service.HostService;
import com.ws.stoner.service.PlatformService;
import com.ws.stoner.service.PointSerivce;
import com.ws.stoner.service.TemplateService;
import com.ws.stoner.utils.RestResultGenerator;
import com.ws.stoner.utils.StatusConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.ws.stoner.constant.MessageConsts.REST_RESPONSE_SUCCESS;

/**
 * Created by chenzheqi on 2017/5/24.
 */
@RestController
public class DashboardRestController {
    @Autowired
    private HostService hostService;

    @Autowired
    private PlatformService platformService;

    @Autowired
    private PointSerivce pointSerivce;

    @Autowired
    private TemplateService templateService;


    @RequestMapping(value = "host/count", method = RequestMethod.GET)
    public String countHost() throws ServiceException {
        StateNumVO hostState = new StateNumVO();
        List<StateNumVO.StateNum> stateNums = new ArrayList<>();
        int allHostNum = 0;
        int warningHostNum = 0;
        int highHostNum = 0;
        allHostNum = hostService.countAllHost();
        warningHostNum = hostService.countWarningHost();
        highHostNum = hostService.countHighHost();
        StateNumVO.StateNum warningStateNum = new StateNumVO.StateNum(StatusEnum.WARNING,warningHostNum);
        StateNumVO.StateNum highStateNum = new StateNumVO.StateNum(StatusEnum.HIGH, highHostNum);
        StateNumVO.StateNum okStateNum = new StateNumVO.StateNum(StatusEnum.OK,allHostNum - warningHostNum - highHostNum);
        stateNums.add(okStateNum);
        stateNums.add(warningStateNum);
        stateNums.add(highStateNum);
        hostState.setTotalNum(allHostNum).setStateNum(stateNums);
        return RestResultGenerator.genResult(hostState, REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "point/count",method = RequestMethod.GET)
    public String countPoint() throws ServiceException {
        StateNumVO pointState = new StateNumVO();
        List<StateNumVO.StateNum> stateNums = new ArrayList<>();
        int allPointNum;
        int warningPointNum;
        int highPointNum;
        allPointNum = pointSerivce.countAllPoint();
        warningPointNum = pointSerivce.countWarningPoint();
        highPointNum = pointSerivce.countHighPoint();
        StateNumVO.StateNum warningStateNum = new StateNumVO.StateNum(StatusEnum.WARNING,warningPointNum);
        StateNumVO.StateNum highStateNum = new StateNumVO.StateNum(StatusEnum.HIGH, highPointNum);
        StateNumVO.StateNum okStateNum = new StateNumVO.StateNum(StatusEnum.OK,allPointNum - warningPointNum - highPointNum);
        stateNums.add(okStateNum);
        stateNums.add(warningStateNum);
        stateNums.add(highStateNum);
        pointState.setTotalNum(allPointNum).setStateNum(stateNums);
        return RestResultGenerator.genResult(pointState, REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "platform/count", method = RequestMethod.GET)
    public String countPlatform() throws ServiceException {
        StateNumVO platformState = new StateNumVO();
        List<StateNumVO.StateNum> stateNums = new ArrayList<>();
        int allPlatformNum;
        int warningPlatformNum;
        int highPlatformNum;
        allPlatformNum = platformService.countAllPlatform();
        warningPlatformNum = platformService.countWarningPlatform();
        highPlatformNum = platformService.countHighPlatform();
        StateNumVO.StateNum warningStateNum = new StateNumVO.StateNum(StatusEnum.WARNING,warningPlatformNum);
        StateNumVO.StateNum highStateNum = new StateNumVO.StateNum(StatusEnum.HIGH, highPlatformNum);
        StateNumVO.StateNum okStateNum = new StateNumVO.StateNum(StatusEnum.OK,allPlatformNum - warningPlatformNum - highPlatformNum);
        stateNums.add(okStateNum);
        stateNums.add(warningStateNum);
        stateNums.add(highStateNum);
        platformState.setTotalNum(allPlatformNum).setStateNum(stateNums);
        return RestResultGenerator.genResult(platformState, REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "host/list", method = RequestMethod.GET)
    public String listHost() throws ServiceException {
        List<BriefHostDTO> allhostDTO = null;
        List<BriefTemplateDTO> allTemplateDTO = null;
        //step1:取BriefHostDTO 类型所有主机allhostDTO
        allhostDTO = hostService.listAllHost();
        //step2:获取所有模板allTemplateDTO
        allTemplateDTO = templateService.listAllTemplate();
        //step3:循环给DashBoardHostVO赋值
        List<DashboardHostVO> hostVOS = new ArrayList<>();
        for(BriefHostDTO hostDTO : allhostDTO) {
            DashboardHostVO hostVO = new DashboardHostVO();
            //hostid,name赋值
            hostVO.setHostId(hostDTO.getHostId());
            hostVO.setName(hostDTO.getName());
            //ip
            hostVO.setIp(hostDTO.getInterfaces().get(0).getIp());
            hostVO.setState(StatusConverter.StatusTransform(hostDTO.getCustomState(),hostDTO.getCustomAvailableState()));
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
            //warningNum
            //highNum
            int warningNum = 0;
            int highNum = 0;
            for(BriefPointDTO point : hostDTO.getPoints()) {
                if(StatusEnum.WARNING.code == point.getCustomState()) {
                    warningNum++;
                }
                if(StatusEnum.HIGH.code == point.getCustomState()) {
                    highNum++;
                }
            }
            hostVO.setWarningNum(warningNum);
            hostVO.setHighNum(highNum);
            hostVOS.add(hostVO);
        }
        return RestResultGenerator.genResult(hostVOS, REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "platform/list", method = RequestMethod.GET)
    public String listPlatform() throws ServiceException {
        List<BriefPlatformDTO> allPlatformDTO = null;
        List<BriefHostDTO> hostDTOS = null;
        //step1:获取BriefPlatformDTO 类型的所有业务平台 allPlatformDTO
        allPlatformDTO = platformService.listAllPlatform();
        //step2:取所有监控中的主机，组装hostIds
        hostDTOS = hostService.listAllHost();
        List<String> hostIds = new ArrayList<>();
        for(BriefHostDTO host : hostDTOS) {
            hostIds.add(host.getHostId());
        }
        List<String> platformIds = new ArrayList<>();
        for(BriefPlatformDTO platformDTO : allPlatformDTO) {
            platformIds.add(platformDTO.getPlatformId());
        }
        //获取 健康值 的map对象
        Map<String,Float> healthMap = platformService.getHealthByPlatformIds(platformIds);
        //step3:新建List<DashboardPlatformVO>，循环allplatformDTO，新建DashboardPlatformVO，分别赋值
        List<DashboardPlatformVO> platformVOS = new ArrayList<>();
        for(BriefPlatformDTO platform : allPlatformDTO) {
            DashboardPlatformVO platformVO = new DashboardPlatformVO();
            //赋值 id,name,availability
            platformVO.setPlatformId(platform.getPlatformId());
            platformVO.setName(platform.getName());
            platformVO.setAvailability(healthMap.get(platform.getPlatformId()));
            platformVO.setState(StatusConverter.StatusTransform(platform.getCustomState()));
            //allNum，warningNum,highNum
            int allNum = 0;
            int warningNum = 0;
            int highNum = 0;
            for(BriefHostDTO host : platform.getHosts()) {
                if(hostIds.contains(host.getHostId())) {
                    allNum++;
                }
                if(StatusEnum.WARNING.code == host.getCustomState() && StatusEnum.OK.code == host.getCustomAvailableState()) {
                    warningNum++;
                }
                if(StatusEnum.HIGH.code == host.getCustomState() || StatusEnum.WARNING.code == host.getCustomAvailableState()) {
                    highNum++;
                }
            }
            platformVO.setAllNum(allNum);
            platformVO.setWarningNum(warningNum);
            platformVO.setHighNum(highNum);

            platformVOS.add(platformVO);
        }
        return RestResultGenerator.genResult(platformVOS, REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "point/list", method = RequestMethod.GET)
    public String listPoint() throws ServiceException {
        //step1:获取BriefPointDTO 类型的所有启用的主机的监控点point allPointDTO
        List<BriefPointDTO> allPointDTO = null;
        allPointDTO = pointSerivce.listAllPoint();
        //step2:新建List<DashboardPointVO>，循环allPointDTO，新建DashboardPointVO，分别赋值
        List<DashboardPointVO> pointVOS = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for(BriefPointDTO point :allPointDTO) {
            DashboardPointVO pointVO = new DashboardPointVO();
            //赋值 id,name,hostid,hostname
            pointVO.setPointId(point.getPointId());
            pointVO.setName(point.getName());
            pointVO.setHostId(point.getHostId());
            pointVO.setHostName(point.getHost().getName());
            pointVO.setState(StatusConverter.StatusTransform(point.getCustomState()));
            //lastTime
            List<BriefItemDTO> items = point.getItems();
            if(items.size() != 0) {
                LocalDateTime lastTime = null;
                for(BriefItemDTO item : items) {
                    if(item.getLastTime() != null) {
                        lastTime = item.getLastTime();
                        break;
                    }
                }
                for(BriefItemDTO item : items) {
                    if(item.getLastTime() != null && lastTime.compareTo(item.getLastTime()) < 0) {
                        lastTime = item.getLastTime();
                    }
                }
                if(lastTime != null) {
                    pointVO.setLastTime(lastTime.format(dateTimeFormatter));
                }
            }
            pointVOS.add(pointVO);
        }
        return RestResultGenerator.genResult(pointVOS, REST_RESPONSE_SUCCESS).toString();
    }
}
