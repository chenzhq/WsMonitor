package com.ws.stoner.controller;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefHostDTO;
import com.ws.stoner.model.view.statepie.StateViewVO;
import com.ws.stoner.service.HostService;
import com.ws.stoner.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenzheqi on 2017/7/11.
 */
@Controller
@RequestMapping("/overview")
public class OverviewController {

    @Autowired
    private ViewService viewService;

    @Autowired
    private HostService hostService;

    @RequestMapping(value = {"/", ""})
    public ModelAndView overview() throws ServiceException{
        ModelAndView mav = new ModelAndView("overview");
        List<BriefHostDTO> hostDTOS = hostService.listAllHost();
        List<String> hostIds = new ArrayList<>();
        for(BriefHostDTO hostDTO : hostDTOS) {
            hostIds.add(hostDTO.getHostId());
        }
        StateViewVO stateViewVO = viewService.getStateViewByHostIds(hostIds);
        mav.addObject("stateViewVO", stateViewVO );
        return mav;
    }
}
