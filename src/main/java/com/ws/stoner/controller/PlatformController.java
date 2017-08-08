package com.ws.stoner.controller;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.view.PlatformListVO;
import com.ws.stoner.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by zkf on 2017/8/8.
 */
@Controller
@RequestMapping("/platform")
public class PlatformController {

    @Autowired
    private PlatformService platformService;

    @RequestMapping(value = {"/", ""})
    public ModelAndView platformList() throws ServiceException {
        List<PlatformListVO> platformListVOS = platformService.getPlatformList();
        ModelAndView mav = new ModelAndView("platformlist");
        mav.addObject("platformListVOS", platformListVOS );
        return mav;
    }

    @RequestMapping(value = {"/block", ""})
    public String platformBlock() {
        return "platformview";
    }

    @RequestMapping(value = {"/detail", ""})
    public String platformDetail() {
        return "platformdetail";
    }
}
