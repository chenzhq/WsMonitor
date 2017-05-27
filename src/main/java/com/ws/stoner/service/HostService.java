package com.ws.stoner.service;

import com.ws.bix4j.bean.HostDO;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.StateNumDTO;

import java.util.List;

/**
 * Created by chenzheqi on 2017/5/23.
 */
public interface HostService {
    /**
     * List host list.
     *
     * @return the list
     * @throws AuthExpireException the auth expire exception
     */
    List<HostDO> listHost() throws AuthExpireException;

    /**
     * 计算停用主机的数量.
     *
     * @return the int
     * @throws ServiceException the service exception
     */
    int countDisableHost() throws ServiceException;

    /**
     * 计算维护状态主机的数量.
     *
     * @return the int
     */
    int countMaintenanceHost();

    /**
     * 计算有问题的主机数量
     * 该主机触发器产生了Problem
     *
     * @return 有问题的主机数 int
     * @throws ServiceException the service exception
     */
    int countDangerHost() throws ServiceException;

    /**
     * Count unsupported host int.
     *
     * @return the int
     */
    int countUnsupportedHost();

    /**
     * 计算正常的主机数量
     *
     * @return the int
     */
    int countOkHost();

    /**
     * Count all host state state num dto.
     *
     * @return the state num dto
     * @throws ServiceException the service exception
     */
    StateNumDTO countAllHostState() throws ServiceException;


    /**
     * Count all host int.
     *
     * @return the int
     */
    int countAllHost();

    /**
     * Gets host.
     *
     * @param hostId the host id
     * @return the host
     */
    HostDO getHost(String... hostId);
}
