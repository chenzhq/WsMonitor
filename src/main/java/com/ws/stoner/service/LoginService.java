package com.ws.stoner.service;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.user.UserLoginResponse;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.access.ZResult;
import com.ws.bix4j.access.user.UserGetRequest;
import com.ws.stoner.model.bo.LoginBO;
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

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
    @Autowired
    private ZApi zApi;

    public LoginBO login(LoginFormQuery loginFormQuery) {
        UserLoginResponse.Result result;
        LoginBO loginBO = new LoginBO(false);
        try {
            result = zApi.login(loginFormQuery.getUsername(),loginFormQuery.getPassword()).getResult();
            loginBO.setLoginSuccess(true).setSessionId(result.getSessionId());
        } catch (ZApiException e) {
            if(e.getCode().equals(ZApiExceptionEnum.ZBX_API_LOGIN_ERROR)) {
                return loginBO;
            } else {
                //如果是网络问题，或者请求参数不合法等问题
                e.printStackTrace();
            }

        }
        logger.info("{} login success.", loginFormQuery.getUsername());

        return loginBO;
    }

    public void loginWithCookie(String zbxSessionId) throws ZApiException {
        zApi.cacheLogin(zbxSessionId);
            zApi.User().get(new UserGetRequest());


    }
}
