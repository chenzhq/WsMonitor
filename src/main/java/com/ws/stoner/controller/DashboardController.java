package com.ws.stoner.controller;

import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.view.BriefProblemVO;
import com.ws.stoner.service.FetchBriefService;
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
    private FetchBriefService fetchBriefService;

    @RequestMapping(value = {"/", ""})
    public ModelAndView dashboard(Model model) throws ManagerException {
        ModelAndView mav = new ModelAndView("dashboard");
        List<BriefProblemVO> problemVOList = null;
        try {
            problemVOList = fetchBriefService.listBriefProblems();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        mav.addObject("problemList", problemVOList);
        return mav;
    }
}
