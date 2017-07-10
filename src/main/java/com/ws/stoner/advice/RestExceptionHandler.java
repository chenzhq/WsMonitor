package com.ws.stoner.advice;

import com.ws.stoner.constant.ResponseErrorEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.utils.RestResultGenerator;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by chenzheqi on 2017/7/7.
 */
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = AuthExpireException.class)
    public String handleAuthExpire() {
        return RestResultGenerator.genErrorResult(ResponseErrorEnum.AUTH_EXPIRE).toString();
    }

    @ExceptionHandler(value = ServiceException.class)
    public String handleNormalException() {
        return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
    }
}
