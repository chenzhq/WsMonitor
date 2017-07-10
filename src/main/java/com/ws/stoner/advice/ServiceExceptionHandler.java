package com.ws.stoner.advice;

import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by chenzheqi on 2017/7/7.
 */
@Aspect
@Component
public class ServiceExceptionHandler {

    @AfterThrowing(
            pointcut = "execution(* com.ws.stoner.service..*.*(..))",
            throwing = "ex")
    public void dost(ZApiException ex) {
        int eCode = ex.getCode().code;
        if (eCode == ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE.code ||
                eCode == ZApiExceptionEnum.NO_AUTH_ASSIGN.code) {
        } else {
        }

    }
}
