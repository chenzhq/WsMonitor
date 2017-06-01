package com.ws.stoner.advice;

import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.query.LoginFormQuery;
import com.ws.stoner.utils.CookieUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletResponse;

import static com.ws.stoner.constant.CookieConsts.ZBX_SESSION;

/**
 * Created by chenzheqi on 2017/5/10.
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = AuthExpireException.class)
    public ModelAndView authExpireHandle(HttpServletResponse response) {
        CookieUtils.remove(response, ZBX_SESSION);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/login");
        mav.addObject(new LoginFormQuery());
        return mav;
    }

    @ExceptionHandler(value = ServiceException.class)
    public String exception() {
       return "404";
    }
}
