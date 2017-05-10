package com.ws.stoner.service;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.access.user.UserGetRequest;
import com.ws.bix4j.bean.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenzheqi on 2017/4/28.
 */
@Service
public class UserService {
    @Autowired
    private ZApi zApi;

    public List<UserDO> listUser() {
        UserGetRequest userGetRequest = new UserGetRequest();
        userGetRequest.getParams();
        List<UserDO> userDOList = null;
        try {
            userDOList = zApi.User().get(userGetRequest).getResult();
        } catch (ZApiException e) {
            e.printStackTrace();
        }
        return userDOList;
    }
}
