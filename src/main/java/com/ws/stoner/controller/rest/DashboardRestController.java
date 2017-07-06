package com.ws.stoner.controller.rest;

import com.ws.stoner.constant.ResponseErrorEnum;
import com.ws.stoner.constant.StatusEnum;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.model.dto.*;
import com.ws.stoner.model.view.DashboardHostVO;
import com.ws.stoner.model.view.DashboardPlatformVO;
import com.ws.stoner.model.view.DashboardPointVO;
import com.ws.stoner.service.*;
import com.ws.stoner.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.ws.stoner.constant.MessageConsts.REST_RESPONSE_SUCCESS;

/**
 * Created by chenzheqi on 2017/5/24.
 */
@RestController
public class DashboardRestController {
    @Autowired
    private HostSerivce hostSerivce;

    @Autowired
    private PlatformSerivce platformSerivce;

    @Autowired
    private PointSerivce pointSerivce;

    @Autowired
    private TemplateService templateService;


    @RequestMapping(value = "host/count", method = RequestMethod.GET)
    public String countHost() {
        StateNumDTO hostState = new StateNumDTO();
        List<StateNumDTO.StateNum> stateNums = new ArrayList<>();
        int allHostNum = 0;
        int warningHostNum = 0;
        int hightHostNum = 0;
        try {
            allHostNum = hostSerivce.countAllHost();
            warningHostNum = hostSerivce.countWarningHost();
            hightHostNum = hostSerivce.countHightHost();
        } catch (ManagerException e) {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
        StateNumDTO.StateNum warningStateNum = new StateNumDTO.StateNum(StatusEnum.WARNING,warningHostNum);
        StateNumDTO.StateNum hightStateNum = new StateNumDTO.StateNum(StatusEnum.HIGHT,hightHostNum);
        StateNumDTO.StateNum okStateNum = new StateNumDTO.StateNum(StatusEnum.OK,allHostNum - warningHostNum - hightHostNum);
        stateNums.add(okStateNum);
        stateNums.add(warningStateNum);
        stateNums.add(hightStateNum);
        hostState.setTotalNum(allHostNum).setStateNum(stateNums);
        return RestResultGenerator.genResult(hostState, REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "point/count",method = RequestMethod.GET)
    public String countPoint() {
        StateNumDTO pointState = new StateNumDTO();
        List<StateNumDTO.StateNum> stateNums = new ArrayList<>();
        int allPointNum = 0;
        int warningPointNum = 0;
        int hightPoinNum = 0;
        try {
            allPointNum = pointSerivce.countAllPoint();
            warningPointNum = pointSerivce.countWarningPoint();
            hightPoinNum = pointSerivce.countHightPoint();
        } catch (ManagerException e) {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
        StateNumDTO.StateNum warningStateNum = new StateNumDTO.StateNum(StatusEnum.WARNING,warningPointNum);
        StateNumDTO.StateNum hightStateNum = new StateNumDTO.StateNum(StatusEnum.HIGHT,hightPoinNum);
        StateNumDTO.StateNum okStateNum = new StateNumDTO.StateNum(StatusEnum.OK,allPointNum - warningPointNum - hightPoinNum);
        stateNums.add(okStateNum);
        stateNums.add(warningStateNum);
        stateNums.add(hightStateNum);
        pointState.setTotalNum(allPointNum).setStateNum(stateNums);
        return RestResultGenerator.genResult(pointState, REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "platform/count", method = RequestMethod.GET)
    public String countPlatform() {
        StateNumDTO platformState = new StateNumDTO();
        List<StateNumDTO.StateNum> stateNums = new ArrayList<>();
        int allPlarformNum = 0;
        int warningPlatformNum = 0;
        int hightPlatformNum = 0;
        try {
            allPlarformNum = platformSerivce.countAllPlatform();
            warningPlatformNum = platformSerivce.countWarningPlatform();
            hightPlatformNum = platformSerivce.countHightPlatform();
        } catch (ManagerException e) {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
        StateNumDTO.StateNum warningStateNum = new StateNumDTO.StateNum(StatusEnum.WARNING,warningPlatformNum);
        StateNumDTO.StateNum hightStateNum = new StateNumDTO.StateNum(StatusEnum.HIGHT,hightPlatformNum);
        StateNumDTO.StateNum okStateNum = new StateNumDTO.StateNum(StatusEnum.OK,allPlarformNum - warningPlatformNum - hightPlatformNum);
        stateNums.add(okStateNum);
        stateNums.add(warningStateNum);
        stateNums.add(hightStateNum);
        platformState.setTotalNum(allPlarformNum).setStateNum(stateNums);
        return RestResultGenerator.genResult(platformState, REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "host/list", method = RequestMethod.GET)
    public String listHost() {
        List<BriefHostDTO> allhostDTO = null;
        List<BriefTemplateDTO> allTemplateDTO = null;
        try {
            //step1:取BriefHostDTO 类型所有主机allhostDTO
            allhostDTO = hostSerivce.listAllHost();
            //step2:获取所有模板allTemplateDTO
            allTemplateDTO = templateService.listAllTemplate();
        } catch (ManagerException e) {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
        //step3:循环给DashBoardHostVO赋值
        List<DashboardHostVO> hostVOS = new ArrayList<>();
        for(BriefHostDTO hostDTO : allhostDTO) {
            DashboardHostVO hostVO = new DashboardHostVO();
            //hostid,name赋值
            hostVO.setHostId(hostDTO.getHostId());
            hostVO.setName(hostDTO.getName());
            //ip
            hostVO.setIp(hostDTO.getInterfaces().get(0).getIp());
            //state
            if("0".equals(hostDTO.getCustomState()) && "0".equals(hostDTO.getCustomAvailableState())) {
                hostVO.setState(StatusEnum.OK.getName());
            }else if("1".equals(hostDTO.getCustomState()) && "0".equals(hostDTO.getCustomAvailableState())) {
                hostVO.setState(StatusEnum.WARNING.getName());
            }else {
                hostVO.setState(StatusEnum.HIGHT.getName());
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
            //warningNum
            //hightNum
            int warningNum = 0;
            int hightNum = 0;
            for(BriefPointDTO point : hostDTO.getPoints()) {
                if("1".equals(point.getCustomState())) {
                    warningNum++;
                }
                if("2".equals(point.getCustomState())) {
                    hightNum++;
                }
            }
            hostVO.setWarningNum(warningNum);
            hostVO.setHightNum(hightNum);
            hostVOS.add(hostVO);
        }
        return RestResultGenerator.genResult(hostVOS, REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "platform/list", method = RequestMethod.GET)
    public String listPlatform() {
        List<BriefPlatformDTO> allPlatformDTO = null;
        List<BriefHostDTO> hostDTOS = null;
        try {
            //step1:获取BriefPlatformDTO 类型的所有业务平台 allPlatformDTO
            allPlatformDTO = platformSerivce.listAllPlatform();
            //step2:取所有监控中的主机，组装hostIds
            hostDTOS = hostSerivce.listAllHost();
        } catch (ManagerException e) {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
        List<String> hostIds = new ArrayList<>();
        for(BriefHostDTO host : hostDTOS) {
            hostIds.add(host.getHostId());
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
            if("1".equals(platform.getCustomState())) {
                platformVO.setState(StatusEnum.WARNING.getName());
            }else if("2".equals(platform.getCustomState())) {
                platformVO.setState(StatusEnum.HIGHT.getName());
            }else {
                platformVO.setState(StatusEnum.OK.getName());
            }
            //allNum，warningNum,hightNum
            int allNum = 0;
            int warningNum = 0;
            int hightNum = 0;
            for(BriefHostDTO host : platform.getHosts()) {
                if(hostIds.contains(host.getHostId())) {
                    allNum++;
                }
                if("1".equals(host.getCustomState()) && "0".equals(host.getCustomAvailableState())) {
                    warningNum++;
                }
                if("2".equals(host.getCustomState()) || "1".equals(host.getCustomAvailableState())) {
                    hightNum++;
                }
            }
            platformVO.setAllNum(allNum);
            platformVO.setWarningNum(warningNum);
            platformVO.setHightNum(hightNum);

            platformVOS.add(platformVO);
        }
        return RestResultGenerator.genResult(platformVOS, REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "point/list", method = RequestMethod.GET)
    public String listPoint() {
        //step1:获取BriefPointDTO 类型的所有启用的主机的监控点point allPointDTO
        List<BriefPointDTO> allPointDTO = null;
        try {
            allPointDTO = pointSerivce.listAllPoint();
        } catch (ManagerException e) {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
        //step2:新建List<DashboardPointVO>，循环allPointDTO，新建DashboardPointVO，分别赋值
        List<DashboardPointVO> pointVOS = new ArrayList<>();
        for(BriefPointDTO point :allPointDTO) {
            DashboardPointVO pointVO = new DashboardPointVO();
            //赋值 id,name,hostid,hostname
            pointVO.setPointId(point.getPointId());
            pointVO.setName(point.getName());
            pointVO.setHostId(point.getHostId());
            pointVO.setHostName(point.getHost().getName());
            //state
            if("1".equals(point.getCustomState())) {
                pointVO.setState(StatusEnum.WARNING.getName());
            }else if("2".equals(point.getCustomState())){
                pointVO.setState(StatusEnum.HIGHT.getName());
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
        return RestResultGenerator.genResult(pointVOS, REST_RESPONSE_SUCCESS).toString();
    }
}
