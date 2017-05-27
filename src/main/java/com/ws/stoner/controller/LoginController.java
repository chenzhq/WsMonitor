package com.ws.stoner.controller;

import com.ws.stoner.constant.CookieConsts;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.LoginDTO;
import com.ws.stoner.model.dto.UserInfoDTO;
import com.ws.stoner.model.query.LoginFormQuery;
import com.ws.stoner.service.LoginService;
import com.ws.stoner.service.UserService;
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
    private UserService userService;
    @Autowired
    private Map<String, String> sessionMap;

    @RequestMapping(value = {"/", ""})
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {

        String zbx_session = CookieUtils.getValue(request, ZBX_SESSION);
        String userId = CookieUtils.getValue(request, CookieConsts.USER_ID);
        if (null == zbx_session || null == userId) {
            model.addAttribute("loginFormQuery", new LoginFormQuery());
            return "login";
        } else if (loginService.loginWithCookie(zbx_session)) {
            request.getSession().setAttribute(ZBX_SESSION, zbx_session);
            request.getSession().setAttribute(REMEMBER_ME, true);

            UserInfoDTO userInfo = userService.getUser(userId);
            request.getSession().setAttribute(CookieConsts.USER_INFO, userInfo);
            sessionMap.put(request.getSession().getId(), zbx_session);
            return "redirect:/dashboard";
        }
        logger.debug("zbx_session expire, re-login.");
        CookieUtils.remove(response, ZBX_SESSION);
        CookieUtils.remove(response, CookieConsts.USER_ID);

        model.addAttribute("loginFormQuery", new LoginFormQuery());
        return "login";
    }



    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String login(@Valid @ModelAttribute LoginFormQuery loginFormQuery, BindingResult bindingResult,
                        HttpSession session, HttpServletResponse response) throws ServiceException {
        if(bindingResult.hasErrors()) {
            return "login";
        }

        LoginDTO loginResult = loginService.login(loginFormQuery);
        if (!loginResult.isLoginSuccess()) {
            FieldError fieldError = new FieldError("loginFormQuery", "password", "用户名或密码错误");
            bindingResult.addError(fieldError);
            return "login";
        }

        String zbx_session = loginResult.getSessionId();
        UserInfoDTO userInfo = loginResult.getUserInfoDTO();
        session.setAttribute(ZBX_SESSION, zbx_session);
        session.setAttribute(CookieConsts.USER_INFO, userInfo);

        sessionMap.put(session.getId(), zbx_session);

        /*
        关于为什么要存userId
        因为api中未提供通过auth反向查找用户的功能，所以如果即使使用cookie存储了auth，但仍查不到是哪个用户
        因此存储userId可以解决这个问题，并且id并非隐秘数据，但是需要考虑用户修改了id后带来的变化
        */
        if (!loginFormQuery.isRememberMe()) {
            CookieUtils.add(response, ZBX_SESSION, zbx_session);
            CookieUtils.add(response, CookieConsts.USER_ID, userInfo.getUserId());
        } else {
            CookieUtils.add(response, ZBX_SESSION, zbx_session, REMEMBER_ME_EXPIRE_TIME);
            CookieUtils.add(response, CookieConsts.USER_ID, userInfo.getUserId(), REMEMBER_ME_EXPIRE_TIME);
            session.setAttribute(REMEMBER_ME, true);
        }

        return "redirect:/dashboard";
    }
}
