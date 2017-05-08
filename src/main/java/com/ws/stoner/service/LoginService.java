package com.ws.stoner.service;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.ZApiException;
import com.ws.bix4j.access.ZResponse;
import com.ws.bix4j.access.ZResult;
import com.ws.bix4j.access.user.UserLoginResponse;
import com.ws.stoner.model.query.LoginFormQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenzheqi on 2017/4/26.
 */
@Service
public class LoginService {

    private Logger logger = LoggerFactory.getLogger(LoginService.class);
    @Autowired
    private ZApi zApi;

    public ZResult login(LoginFormQuery loginFormQuery) {
        ZResult result = null;
        try {
            result = zApi.login(loginFormQuery.getUsername(),loginFormQuery.getPassword()).getResult();
        } catch (ZApiException e) {
            e.printStackTrace();
        }
        logger.info("{} login success.", loginFormQuery.getUsername());

        if(loginFormQuery.isRememberMe()) {

        }
        return result;
    }
}
