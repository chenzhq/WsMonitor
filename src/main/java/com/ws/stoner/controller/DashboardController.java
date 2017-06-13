package com.ws.stoner.controller;

import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.manager.GroupManager;
import com.ws.stoner.manager.HostManager;
import com.ws.stoner.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chenzheqi on 2017/5/8.
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private HostManager hostManager;

    @Autowired
    private UserManager userManager;

    @Autowired
    private GroupManager groupManager;


    @RequestMapping(value = {"/", ""})
    public String dashboard(Model model) throws ManagerException {
        return "dashboard";
    }
}
