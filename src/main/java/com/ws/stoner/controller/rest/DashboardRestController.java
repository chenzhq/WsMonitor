package com.ws.stoner.controller.rest;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.StateNumDTO;
import com.ws.stoner.service.HostService;
import com.ws.stoner.service.PlatformService;
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
    private HostService hostService;

    @Autowired
    private PlatformService platformService;

    @RequestMapping(value = "host/count", method = RequestMethod.GET)
    public String countHost() throws ServiceException {
        StateNumDTO allHostNum = hostService.countAllHostState();
        return RestResultGenerator.genResult(allHostNum, "查询成功").toString();
    }

    @RequestMapping(value = "platform/count", method = RequestMethod.GET)
    public String countPlatform() throws ServiceException {
        List<StateNumDTO> allPlatformStatusNum = platformService.countAllPlatform();
        return RestResultGenerator.genResult(allPlatformStatusNum, "查询成功").toString();
    }
}
