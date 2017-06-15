package com.ws.stoner.controller.rest;

import com.ws.stoner.constant.ResponseErrorEnum;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.manager.HostManager;
import com.ws.stoner.manager.PlatformManager;
import com.ws.stoner.model.dto.StateNumDTO;
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
    private HostManager hostManager;

    @Autowired
    private PlatformManager platformManager;

    @RequestMapping(value = "host/count", method = RequestMethod.GET)
    public String countHost() {
        StateNumDTO allHostNum;
        try {
            allHostNum = hostManager.countAllHostState();
        } catch (ManagerException e) {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
        return RestResultGenerator.genResult(allHostNum, REST_RESPONSE_SUCCESS).toString();
    }

    @RequestMapping(value = "platform/count", method = RequestMethod.GET)
    public String countPlatform() {
        List<StateNumDTO> allPlatformStatusNum;
        try {
            allPlatformStatusNum = platformManager.countAllPlatform();
        } catch (ManagerException e) {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
        return RestResultGenerator.genResult(allPlatformStatusNum, REST_RESPONSE_SUCCESS).toString();
    }
}
