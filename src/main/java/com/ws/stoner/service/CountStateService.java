package com.ws.stoner.service;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.StateNumDTO;

/**
 * Created by zkf on 2017/6/12.
 */
public interface CountStateService {

/*
 *主机业务状态组合
 */
    StateNumDTO countHostState() throws ServiceException;

/*
 *业务平台业务状态组合
 */
    StateNumDTO countPlatformState() throws ServiceException;
/*
 *监控点业务状态组合
 */
    StateNumDTO countPointState() throws ServiceException;







/*
 * point service
 */


/*
 * item service
 */



/*
 * trigger service
 */



}
