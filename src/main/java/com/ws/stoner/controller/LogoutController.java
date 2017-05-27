package com.ws.stoner.controller;

import com.ws.stoner.constant.CookieConsts;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.service.LoginService;
import com.ws.stoner.utils.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by chenzheqi on 2017/5/27.
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {
    public static final Logger logger = LoggerFactory.getLogger(LogoutController.class);

    @Autowired
    private LoginService loginService;
    @Autowired
    private Map<String, String> sessionMap;

    @RequestMapping(value = {"/", ""})
    public String logout(HttpServletResponse response, HttpSession session) throws ServiceException {
        try {
            loginService.logout();
        } catch (AuthExpireException e) {
            //添加了拦截器后，正常情况应该不会产生这个异常
            //除非，有人手动更改了后台数据库中的数据
        }
        session.removeAttribute(CookieConsts.ZBX_SESSION);
        session.removeAttribute(CookieConsts.REMEMBER_ME);
        session.removeAttribute(CookieConsts.USER_INFO);
        sessionMap.remove(session.getId());

        CookieUtils.remove(response, CookieConsts.USER_ID);
        CookieUtils.remove(response, CookieConsts.ZBX_SESSION);

        return "redirect:/login";
    }
}
