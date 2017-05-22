package com.ws.stoner.advice;

import com.ws.stoner.exception.ServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by chenzheqi on 2017/5/10.
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = ServiceException.class)
    public String exception() {
       return "404";
    }
}
