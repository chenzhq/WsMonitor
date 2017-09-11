package com.ws.stoner.service;

import com.ws.bix4j.bean.UserDO;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.UserInfoDTO;

import java.util.List;

/**
 * Created by chenzheqi on 2017/5/23.
 */
public interface UserService {

    UserInfoDTO getUser(String id);

    List<UserDO> listUser() throws AuthExpireException;

    /**
     * 根据 userIds 查询所有指定的 userDTO selecetUsrgrps
     * @param userIds
     * @return
     * @throws ServiceException
     */
    List<UserInfoDTO> getUsersByUserIds(List<String> userIds) throws ServiceException ;
}
