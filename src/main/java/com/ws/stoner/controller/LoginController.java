package com.ws.stoner.controller;

import com.ws.bix4j.access.user.UserLoginResponse;
import com.ws.bix4j.bean.HostDO;
import com.ws.bix4j.bean.UserDO;
import com.ws.stoner.model.query.LoginFormQuery;
import com.ws.stoner.model.view.UserInfoVO;
import com.ws.stoner.service.HostService;
import com.ws.stoner.service.LoginService;
import com.ws.stoner.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by chenzheqi on 2017/4/26.
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private LoginService loginService;
    @Autowired
    private HostService hostService;

    @Autowired
    private UserService userService;
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("loginFormQuery", new LoginFormQuery());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute LoginFormQuery loginFormQuery, Model model, HttpSession session) {
        UserLoginResponse.Result result = (UserLoginResponse.Result) loginService.login(loginFormQuery);
        UserInfoVO userInfoVO = new UserInfoVO(result);
        session.setAttribute("userInfo", userInfoVO);
        List<UserDO> userDOList = userService.listUser();
        List<HostDO> hostDOList = hostService.listHost();
        model.addAttribute("users", userDOList);
        model.addAttribute("hosts", hostDOList);
        logger.debug("login success, auth store in session. {}", userInfoVO.getAuth());
        return "dashboard";
    }
}
