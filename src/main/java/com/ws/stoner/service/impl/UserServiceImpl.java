package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.alert.AlertGetRequest;
import com.ws.bix4j.access.user.UserGetRequest;
import com.ws.bix4j.bean.UserDO;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefAlertDTO;
import com.ws.stoner.model.dto.BriefUserGroupDTO;
import com.ws.stoner.service.UserService;
import com.ws.stoner.model.dto.UserInfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ws.bix4j.exception.ZApiExceptionEnum.NO_AUTH_ASSIGN;
import static com.ws.bix4j.exception.ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE;

/**
 * Created by chenzheqi on 2017/4/28.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private ZApi zApi;

    private List<UserInfoDTO> listUser(UserGetRequest request) throws ServiceException {
        List<UserInfoDTO> users;
        try {
            users = zApi.User().get(request,UserInfoDTO.class);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询用户 alert 错误！{}", e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return users;
    }

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

    /**
     * 根据 userIds 查询所有指定的 userDTO selecetUsrgrps
     * @param userIds
     * @return
     * @throws ServiceException
     */
    @Override
    public List<UserInfoDTO> getUsersByUserIds(List<String> userIds) throws ServiceException {
        UserGetRequest userGetRequest = new UserGetRequest();
        userGetRequest.getParams()
                .setUserIds(userIds)
                .setSelectUsrgrps(BriefUserGroupDTO.PROPERTY_NAMES)
                .setOutput(UserInfoDTO.PROPERTY_NAMES);


        return null;
    }


}
