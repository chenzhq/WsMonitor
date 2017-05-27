package com.ws.stoner.service;

import com.ws.bix4j.bean.UserDO;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.model.dto.UserInfoDTO;

import java.util.List;

/**
 * Created by chenzheqi on 2017/5/23.
 */
public interface UserService {

    UserInfoDTO getUser(String id);

    List<UserDO> listUser() throws AuthExpireException;
}
