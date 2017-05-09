package com.ws.stoner.service;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.ZApiException;
import com.ws.bix4j.access.ZResponse;
import com.ws.bix4j.access.ZResult;
import com.ws.bix4j.access.user.UserGetRequest;
import com.ws.bix4j.access.user.UserLoginResponse;
import com.ws.stoner.model.query.LoginFormQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by chenzheqi on 2017/4/26.
 */
@Service
public class LoginService {

    private Logger logger = LoggerFactory.getLogger(LoginService.class);
    @Autowired
    private ZApi zApi;
    @Autowired
    private UserService userService;
    @Autowired
    private Map<String, String> sessionMap;

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

    public void loginWithCache(String zbxSessionId) {
        zApi.cacheLogin(zbxSessionId);
        UserGetRequest userGetRequest = new UserGetRequest();

    }
}
