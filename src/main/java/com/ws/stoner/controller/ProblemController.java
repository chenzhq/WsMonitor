package com.ws.stoner.controller;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.view.ProblemDetailVO;
import com.ws.stoner.service.TriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by zkf on 2017/8/22.
 */
@Controller
@RequestMapping("")
public class ProblemController {

    @Autowired
    private TriggerService triggerService;

    @RequestMapping(value = {"/problemlist", ""})
    public String problemList() {
        return "problemlist";
    }

    @RequestMapping(value = {"/problemdetail", ""})
    public ModelAndView ProblemDetail(@RequestParam(name = "trigger_id")String triggerId) throws ServiceException {
        ModelAndView mav = new ModelAndView("problemdetail");
        ProblemDetailVO problemDetailVO = triggerService.getProblemDetailVOByTriggerId(triggerId);
        mav.addObject("problemDetailVO",problemDetailVO);
        return mav;
    }

}
