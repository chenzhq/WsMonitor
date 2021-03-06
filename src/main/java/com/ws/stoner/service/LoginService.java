package com.ws.stoner.service;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.LoginDTO;
import com.ws.stoner.model.query.LoginFormQuery;

/**
 * Created by chenzheqi on 2017/5/23.
 */
public interface LoginService {
    LoginDTO login(LoginFormQuery loginFormQuery) throws ServiceException;

    boolean loginWithCookie(String zbxSessionId);

    boolean logout() throws ServiceException;

}
