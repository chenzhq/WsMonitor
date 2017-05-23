package com.ws.stoner.service;

import com.ws.bix4j.bean.UserDO;
import com.ws.stoner.exception.AuthExpireException;

import java.util.List;

/**
 * Created by chenzheqi on 2017/5/23.
 */
public interface UserService {
    List<UserDO> listUser() throws AuthExpireException;
}
