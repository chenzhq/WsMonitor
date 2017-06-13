package com.ws.stoner.controller;

import com.ws.bix4j.access.host.HostGetRequest;
import com.ws.bix4j.access.hostgroup.HostGroupGetRequest;
import com.ws.bix4j.bean.HostDO;
import com.ws.bix4j.bean.HostGroupDO;
import com.ws.bix4j.bean.UserDO;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.manager.GroupManager;
import com.ws.stoner.manager.HostManager;
import com.ws.stoner.manager.UserManager;
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
    private HostManager hostManager;

    @Autowired
    private UserManager userManager;

    @Autowired
    private GroupManager groupManager;


    @RequestMapping(value = {"/", ""})
    public String dashboard(Model model) throws ManagerException {
        HostGetRequest hostGetRequest = new HostGetRequest();
        HostGroupGetRequest hostGroupGetRequest = new HostGroupGetRequest();
        List<UserDO> userDOList = userManager.listUser();
        List<HostDO> hostDOList = hostManager.listHost();
        List<HostGroupDO> groupDOList = groupManager.listGroup(hostGroupGetRequest);
        int allHost = hostManager.countHost(hostGetRequest);
        int disbleHost = hostManager.countDisableHost();
        int okHost = hostManager.countOkHost();
        int maintenanceHost = hostManager.countMaintenanceHost();
        model.addAttribute("users", userDOList);
        model.addAttribute("hosts", hostDOList);
        model.addAttribute("groups",groupDOList);
        model.addAttribute("hostsNum", allHost);
        model.addAttribute("disHostsNum", disbleHost);
        model.addAttribute("okHostsNum", okHost);
        model.addAttribute("maintenanceHostsNum", maintenanceHost);
        return "dashboard";
    }
}
