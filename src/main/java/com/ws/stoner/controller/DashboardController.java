package com.ws.stoner.controller;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.view.DashboardProblemVO;
import com.ws.stoner.service.TriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by chenzheqi on 2017/5/8.
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private TriggerService triggerService;

    @RequestMapping(value = {"/", ""})
    public ModelAndView dashboard(Model model) throws ServiceException {
        ModelAndView mav = new ModelAndView("dashboard");
        List<DashboardProblemVO> problemVOList = triggerService.listBriefProblems();
        mav.addObject("problemList", problemVOList);
        return mav;
    }
}
