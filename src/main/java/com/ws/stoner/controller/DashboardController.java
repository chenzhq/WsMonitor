package com.ws.stoner.controller;

import com.ws.bix4j.bean.GroupDO;
import com.ws.bix4j.bean.HostDO;
import com.ws.bix4j.bean.UserDO;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.service.GroupService;
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

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = {"/", ""})
    public String dashboard(Model model) throws ServiceException {

        List<UserDO> userDOList = userService.listUser();
        List<HostDO> hostDOList = hostService.listHost();
        List<GroupDO> groupDOList = groupService.listGroup();
        int allHost = hostService.countAllHost();
        int disbleHost = hostService.countDisableHost();
        int okHost = hostService.countOkHost();
        int maintenanceHost = hostService.countMaintenanceHost();
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
