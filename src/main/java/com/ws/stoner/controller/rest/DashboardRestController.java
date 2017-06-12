package com.ws.stoner.controller.rest;

import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.model.dto.StateNumDTO;
import com.ws.stoner.manager.HostManager;
import com.ws.stoner.manager.PlatformManager;
import com.ws.stoner.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public String countHost() throws ManagerException {
        StateNumDTO allHostNum = hostManager.countAllHostState();
        return RestResultGenerator.genResult(allHostNum, "查询成功").toString();
    }

    @RequestMapping(value = "platform/count", method = RequestMethod.GET)
    public String countPlatform() throws ManagerException {
        List<StateNumDTO> allPlatformStatusNum = platformManager.countAllPlatform();
        return RestResultGenerator.genResult(allPlatformStatusNum, "查询成功").toString();
    }
}
