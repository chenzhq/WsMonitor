package com.ws.stoner.controller;

import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.access.user.UserLoginResponse;
import com.ws.stoner.model.bo.LoginBO;
import com.ws.stoner.model.query.LoginFormQuery;
import com.ws.stoner.service.HostService;
import com.ws.stoner.service.LoginService;
import com.ws.stoner.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

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
    private Map<String, String> sessionMap;

    @RequestMapping(value = {"/", ""})
    public String index(HttpServletRequest request, Model model) {

        String zbx_session = getCookies(request.getCookies(), "zbx_session");
        if(zbx_session != null) {
            if (loginService.loginWithCookie(zbx_session)) {
                request.getSession().setAttribute("zbx_session", zbx_session);
                request.getSession().setAttribute("rememberMe", true);
                sessionMap.put(request.getSession().getId(), zbx_session);
                return "redirect:/dashboard";
            }
            logger.debug("zbx_session expire, re-login.");
        }
        model.addAttribute("loginFormQuery", new LoginFormQuery());
        return "login";
    }


    private String getCookies(Cookie[] cookies, String key) {
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(key)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String login(@Valid @ModelAttribute LoginFormQuery loginFormQuery, BindingResult bindingResult,
                        HttpSession session, HttpServletResponse response) {
        if(bindingResult.hasErrors()) {
            return "login";
        }

        LoginBO loginReslut = loginService.login(loginFormQuery);
        if(!loginReslut.isLoginSuccess()) {
            FieldError fieldError = new FieldError("loginFormQuery", "password", "用户名或密码错误");
            bindingResult.addError(fieldError);
            return "login";
        }
        logger.info("登录成功");

        String zbx_session = loginReslut.getSessionId();
        session.setAttribute("zbx_session", zbx_session);

//        session.setMaxInactiveInterval(90);
        sessionMap.put(session.getId(), zbx_session);

        Cookie zbxCookie = new Cookie("zbx_session", zbx_session);
        if(loginFormQuery.isRememberMe()) {
            zbxCookie.setMaxAge(7 * 24 * 60 * 60 );
            session.setAttribute("rememberMe", true);
        }
        zbxCookie.setPath("/");
        response.addCookie(zbxCookie);

        return "redirect:/dashboard";
    }
}
