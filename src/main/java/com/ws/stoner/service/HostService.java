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
     */
    List<HostDO> listHost() throws AuthExpireException;

    /**
     * 停用主机的数量.
     *
     * @return the int
     */
    int countDisableHost();

    /**
     * 维护状态主机的数量.
     *
     * @return the int
     */
    int countMaintenanceHost();

    /**
     * 返回有问题的主机数量
     * 该主机触发器产生了Problem
     *
     * @return 有问题的主机数
     */
    int countDangerHost() throws ServiceException;

    int countUnsupportedHost();
    /**
     * Count ok host int.
     *
     * @return the int
     */
    int countOkHost();

    StateNumDTO countAllHostState() throws ServiceException;


    int countAllHost();
    /**
     * Gets host.
     *
     * @param hostId the host id
     * @return the host
     */
    HostDO getHost(String... hostId);
}
