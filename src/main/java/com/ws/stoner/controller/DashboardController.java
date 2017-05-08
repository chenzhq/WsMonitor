package com.ws.stoner.controller;

import com.ws.bix4j.bean.HostDO;
import com.ws.bix4j.bean.UserDO;
import com.ws.stoner.service.HostService;
import com.ws.stoner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by chenzheqi on 2017/5/8.
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private HostService hostService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", ""})
    public String dashboard(Model model) {

        List<UserDO> userDOList = userService.listUser();
        List<HostDO> hostDOList = hostService.listHost();
        model.addAttribute("users", userDOList);
        model.addAttribute("hosts", hostDOList);

        return "dashboard";
    }
}
