package com.ws.stoner.manager.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.user.UserGetRequest;
import com.ws.bix4j.bean.UserDO;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.manager.UserManager;
import com.ws.stoner.model.dto.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenzheqi on 2017/4/28.
 */
@Service
public class UserManagerImpl implements UserManager {
    @Autowired
    private ZApi zApi;

    @Override
    public UserInfoDTO getUser(String id) {
        UserGetRequest userGetRequest = new UserGetRequest();
        List<String> ids = new ArrayList<>(1);
        ids.add(id);
        userGetRequest.getParams().setUserIds(ids);
        List<UserDO> userList = new ArrayList<>();
        try {
            userList = zApi.User().get(userGetRequest);
        } catch (ZApiException e) {
            e.printStackTrace();
        }
        if (null != userList) {
            return new UserInfoDTO(userList.get(0));
        }
        return null;
    }

    public List<UserDO> listUser() throws AuthExpireException {
        UserGetRequest userGetRequest = new UserGetRequest();
        userGetRequest.getParams();
        List<UserDO> userDOList = null;
        try {
            userDOList = zApi.User().get(userGetRequest);
        } catch (ZApiException e) {
            if (e.getCode().equals(ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE)) {
                throw new AuthExpireException("");
            }
            e.printStackTrace();
        }
        return userDOList;
    }
}
