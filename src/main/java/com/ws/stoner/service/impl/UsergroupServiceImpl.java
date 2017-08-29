package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.alert.AlertGetRequest;
import com.ws.bix4j.access.usergroup.UsergroupGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefAlertDTO;
import com.ws.stoner.model.dto.BriefPermissionDTO;
import com.ws.stoner.model.dto.BriefUserGroupDTO;
import com.ws.stoner.service.UsergroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ws.bix4j.exception.ZApiExceptionEnum.NO_AUTH_ASSIGN;
import static com.ws.bix4j.exception.ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE;

/**
 * Created by zkf on 2017/8/29.
 */
@Service
public class UsergroupServiceImpl implements UsergroupService {

    private static final Logger logger = LoggerFactory.getLogger(UsergroupServiceImpl.class);
    @Autowired
    private ZApi zApi;

    private List<BriefUserGroupDTO> listUsergroup(UsergroupGetRequest request) throws ServiceException {
        List<BriefUserGroupDTO> usergroups;
        try {
            usergroups = zApi.Usergroup().get(request,BriefUserGroupDTO.class);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询用户组 usergroup 错误！{}", e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return usergroups;
    }



    /**
     * 根据 userids 获取指定的 用户组 usergroupDTO
     * @param userIds
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefUserGroupDTO> getUsrgrpsByUserIds(List<String> userIds) throws ServiceException {
        UsergroupGetRequest usergroupGetRequest = new UsergroupGetRequest();
        usergroupGetRequest.getParams()
                .setUserIds(userIds)
                .setSelectRights(BriefPermissionDTO.PROPERTY_NAMES)
                .setOutput(BriefUserGroupDTO.PROPERTY_NAMES);
        return listUsergroup(usergroupGetRequest);
    }
}
