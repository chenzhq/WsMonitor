package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.user.UserGetRequest;
import com.ws.bix4j.bean.UserDO;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenzheqi on 2017/4/28.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ZApi zApi;

    public List<UserDO> listUser() throws AuthExpireException {
        UserGetRequest userGetRequest = new UserGetRequest();
        userGetRequest.getParams();
        List<UserDO> userDOList = null;
        try {
            userDOList = zApi.User().get(userGetRequest).getResult();
        } catch (ZApiException e) {
            if (e.getCode().equals(ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE)) {
                throw new AuthExpireException("");
            }
            e.printStackTrace();
        }
        return userDOList;
    }
}
