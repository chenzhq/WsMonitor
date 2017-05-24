package com.ws.stoner.controller.rest;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.ResponseResult;
import com.ws.stoner.model.bo.HostStatusNumBO;
import com.ws.stoner.service.HostService;
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

    @RequestMapping(value = "host/count", method = RequestMethod.GET)
    public ResponseResult countHost() throws ServiceException {
        List<HostStatusNumBO> allHostNum = hostService.countAllHost();
        return RestResultGenerator.genResult(allHostNum, "success");
    }
}
