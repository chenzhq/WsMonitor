package com.ws.stoner.controller;

import com.ws.bix4j.access.user.UserLoginResponse;
import com.ws.bix4j.bean.HostDO;
import com.ws.bix4j.bean.UserDO;
import com.ws.stoner.model.query.LoginFormQuery;
import com.ws.stoner.model.view.UserInfoVO;
import com.ws.stoner.service.HostService;
import com.ws.stoner.service.LoginService;
import com.ws.stoner.service.UserService;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by chenzheqi on 2017/4/26.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    public static final int DEFAULT_EXPIRE_MIN = 30;
    @Autowired
    private LoginService loginService;
    @Autowired
    private HostService hostService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", ""})
    public String index(Model model) {
        model.addAttribute("loginFormQuery", new LoginFormQuery());
        return "login";
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String login(@ModelAttribute LoginFormQuery loginFormQuery, Model model, HttpSession session,
                        RedirectAttributes redirectAttributes, HttpServletRequest httpRequest, HttpServletResponse response) {
        UserLoginResponse.Result result = (UserLoginResponse.Result) loginService.login(loginFormQuery);
        logger.debug("登录成功");

        session.setAttribute("zbx_session", result.getSessionId());
        logger.debug("auth 存入 session");

        if(loginFormQuery.isRememberMe()) {

        }
        session.setMaxInactiveInterval(1 * 60);
//        Cookie cookie = new Cookie("JSESSION", session.getId());
//        cookie.setMaxAge(DEFAULT_EXPIRE_MIN * 60);

        UserInfoVO userInfoVO = new UserInfoVO(result);
        session.setAttribute("userInfo", userInfoVO);

//        response.addCookie(new Cookie("JSESSION", session.getId()));
//        response.addCookie(new Cookie("ZSESSION", result.getSessionId()));

        List<UserDO> userDOList = userService.listUser();
        List<HostDO> hostDOList = hostService.listHost();
        model.addAttribute("users", userDOList);
        model.addAttribute("hosts", hostDOList);
        redirectAttributes.addFlashAttribute("users", userDOList);
        logger.debug("login success, auth store in session. {}", userInfoVO.getAuth());
        return "redirect:/dashboard";
    }
}
