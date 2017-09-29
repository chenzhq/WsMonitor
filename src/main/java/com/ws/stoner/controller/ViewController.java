package com.ws.stoner.controller;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.DO.mongo.carousel.ViewPage;
import com.ws.stoner.model.view.carousel.PageVO;
import com.ws.stoner.model.view.problem.ProblemDetailVO;
import com.ws.stoner.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by zkf on 2017/9/12.
 */
@Controller
@RequestMapping("")
public class ViewController {

    @Autowired
    private ViewService viewService;

    @RequestMapping(value = {"/view", ""})
    public String view() {
        return "monitorview";
    }

    @RequestMapping(value = {"/carousel", ""})
    public String carousel() {
        return "carouselview";
    }

    @RequestMapping(value = {"/carouselfull", ""})
    public ModelAndView carouselfull(@RequestParam(name = "group_name")String groupName) throws ServiceException {
        ModelAndView mav = new ModelAndView("carouselfull");
        List<ViewPage> pages = viewService.getAllViewPagesByGroupName(groupName);
        mav.addObject("pages",pages);
        mav.addObject("groupName",groupName);
        return mav;
    }
}
