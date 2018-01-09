package com.ws.stoner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by chenzheqi on 2017/5/8.
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {


    @RequestMapping(value = {"/", ""})
    public ModelAndView dashboard() {
        ModelAndView mav = new ModelAndView("dashboard");

        return mav;
    }
}
