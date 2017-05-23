package com.ws.stoner.service;

import com.ws.bix4j.bean.HostDO;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;

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
     * Count unmonitored host int.
     *
     * @return the int
     */
    int countUnmonitoredHost();

    /**
     * Count maintenance host int.
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
    int countProblemHost() throws ServiceException;

    /**
     * Count ok host int.
     *
     * @return the int
     */
    int countOkHost();

    /**
     * Gets host.
     *
     * @param hostId the host id
     * @return the host
     */
    HostDO getHost(String... hostId);
}
