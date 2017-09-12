package com.ws.stoner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zkf on 2017/9/12.
 */
@Controller
@RequestMapping("")
public class ViewController {

    @RequestMapping(value = {"/view", ""})
    public String view() {
        return "view";
    }
}
