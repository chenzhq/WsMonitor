package com.ws.stoner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chenzheqi on 2017/7/11.
 */
@Controller
@RequestMapping("/overview")
public class OverviewController {

    @RequestMapping(value = {"/", ""})
    public String overview() {
        return "overview";
    }
}
