package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.user.UserGetRequest;
import com.ws.bix4j.access.user.UserLoginResponse;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.LoginDTO;
import com.ws.stoner.model.dto.UserInfoDTO;
import com.ws.stoner.model.query.LoginFormQuery;
import com.ws.stoner.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenzheqi on 2017/4/26.
 */
@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Autowired
    private ZApi zApi;

    public LoginDTO login(LoginFormQuery loginFormQuery) throws ServiceException {
        UserLoginResponse.Result result;
        LoginDTO loginDTO = new LoginDTO(false);
        try {
            result = zApi.login(loginFormQuery.getUsername(), loginFormQuery.getPassword()).getResult();
            loginDTO.setLoginSuccess(true).setSessionId(result.getSessionId())
                    .setUserInfoDTO(new UserInfoDTO(result));
        } catch (ZApiException e) {
            if (e.getCode().equals(ZApiExceptionEnum.ZBX_API_LOGIN_ERROR)) {
                return loginDTO;
            } else {
                //如果是网络问题，或者请求参数不合法等问题
                throw new ServiceException(e.getMessage());
            }

        }
        logger.info("{} login success.", loginFormQuery.getUsername());

        return loginDTO;
    }

    public boolean loginWithCookie(String zbxSessionId) {
        zApi.cacheLogin(zbxSessionId);
        try {
            zApi.User().get(new UserGetRequest());
            return true;
        } catch (ZApiException e) {
            if (e.getCode().equals(ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE)) {
                return false;
            } else {
                //网络问题
                return false;
            }
        }
    }
}
