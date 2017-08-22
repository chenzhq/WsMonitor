package com.ws.stoner.controller;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.view.ProblemListVO;
import com.ws.stoner.service.TriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by zkf on 2017/8/22.
 */
@Controller
@RequestMapping("")
public class ProblemController {

    @Autowired
    private TriggerService triggerService;

    @RequestMapping(value = {"/problemlist", ""})
    public ModelAndView problemList(Model model) throws ServiceException {
        ModelAndView mav = new ModelAndView("problemlist");
        List<ProblemListVO> problemListVOS = triggerService.listProblemListVO();
        mav.addObject("problemListVOS", problemListVOS);
        return mav;
    }
}
