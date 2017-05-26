package com.ws.stoner.controller;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.LoginDTO;
import com.ws.stoner.model.query.LoginFormQuery;
import com.ws.stoner.service.LoginService;
import com.ws.stoner.utils.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

import static com.ws.stoner.constant.CookieConsts.*;

/**
 * Created by chenzheqi on 2017/4/26.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private LoginService loginService;
    @Autowired
    private Map<String, String> sessionMap;

    @RequestMapping(value = {"/", ""})
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {

        String zbx_session = CookieUtils.getValue(request, ZBX_SESSION);
        if(zbx_session != null) {
            if (loginService.loginWithCookie(zbx_session)) {
                request.getSession().setAttribute(ZBX_SESSION, zbx_session);
                request.getSession().setAttribute(REMEMBER_ME, true);
                sessionMap.put(request.getSession().getId(), zbx_session);
                return "redirect:/dashboard";
            }
            logger.debug("zbx_session expire, re-login.");
            CookieUtils.remove(response, ZBX_SESSION);
        }
        model.addAttribute("loginFormQuery", new LoginFormQuery());
        return "login";
    }



    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String login(@Valid @ModelAttribute LoginFormQuery loginFormQuery, BindingResult bindingResult,
                        HttpSession session, HttpServletResponse response) throws ServiceException {
        if(bindingResult.hasErrors()) {
            return "login";
        }

        LoginDTO loginReslut = loginService.login(loginFormQuery);
        if(!loginReslut.isLoginSuccess()) {
            FieldError fieldError = new FieldError("loginFormQuery", "password", "用户名或密码错误");
            bindingResult.addError(fieldError);
            return "login";
        }
        logger.info("登录成功");

        String zbx_session = loginReslut.getSessionId();
        session.setAttribute(ZBX_SESSION, zbx_session);

        sessionMap.put(session.getId(), zbx_session);

        if (!loginFormQuery.isRememberMe()) {
            CookieUtils.add(response, ZBX_SESSION, zbx_session);
        } else {
            CookieUtils.add(response, ZBX_SESSION, zbx_session, REMEMBER_ME_EXPIRE_TIME);
            session.setAttribute(REMEMBER_ME, true);
        }

        return "redirect:/dashboard";
    }
}
