package com.ws.stoner.controller.rest;

import com.ws.stoner.constant.ResponseErrorEnum;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.StateNumDTO;
import com.ws.stoner.model.view.DashboardHostVO;
import com.ws.stoner.model.view.DashboardPlatformVO;
import com.ws.stoner.service.CountStateService;
import com.ws.stoner.service.FetchBriefService;
import com.ws.stoner.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ws.stoner.constant.MessageConsts.REST_RESPONSE_SUCCESS;

/**
 * Created by chenzheqi on 2017/5/24.
 */
@RestController
public class DashboardRestController {
    @Autowired
    private CountStateService countStateService;

    @Autowired
    private FetchBriefService fetchBriefService;

    @RequestMapping(value = "host/count", method = RequestMethod.GET)
    public String countHost() {
        StateNumDTO allHostNum;
        try {
            allHostNum = countStateService.countHostState();
        } catch (ServiceException e) {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
        return RestResultGenerator.genResult(allHostNum, REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "point/count",method = RequestMethod.GET)
    public String countPoint() {
        StateNumDTO allPointNum;
        try {
            allPointNum = countStateService.countPointState();
        } catch (ServiceException e) {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
        return RestResultGenerator.genResult(allPointNum, REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "platform/count", method = RequestMethod.GET)
    public String countPlatform() {
        StateNumDTO allPlatformStatusNum;
        try {
            allPlatformStatusNum = countStateService.countPlatformState();
        } catch (ServiceException e) {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
        return RestResultGenerator.genResult(allPlatformStatusNum, REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "host/list", method = RequestMethod.GET)
    public String listHost() {
        List<DashboardHostVO> hostVOS;
        try {
            hostVOS = fetchBriefService.listDashBoardHosts();
        } catch (ServiceException e) {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
        return RestResultGenerator.genResult(hostVOS, REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "platform/list", method = RequestMethod.GET)
    public String listPlatform() {
        List<DashboardPlatformVO> platformVOS;
        try {
            platformVOS = fetchBriefService.listDashboardPlatform();
        } catch (ServiceException e) {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
        return RestResultGenerator.genResult(platformVOS, REST_RESPONSE_SUCCESS).toString();
    }
}
