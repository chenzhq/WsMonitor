package com.ws.stoner.manager;

import com.ws.bix4j.bean.HostDO;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.model.dto.StateNumDTO;

import java.util.List;

/**
 * Created by chenzheqi on 2017/5/23.
 */
public interface HostManager {
    /**
     * List host list.
     *
     * @return the list
     * @throws AuthExpireException the auth expire exception
     */
    List<HostDO> listHost() throws AuthExpireException;

    /**
     * 获取停用主机list
     * @return
     * @throws ManagerException
     */
    List<HostDO> listDisableHost() throws ManagerException;

    /**
     *
     * 获取维护主机list
     * @return
     * @throws ManagerException
     */
    List<HostDO> listMaintenanceHost() throws ManagerException;

    /**
     * 获取问题主机list
     * @return
     * @throws ManagerException
     */
    List<HostDO> listDangerHost() throws ManagerException;

    /**
     * 计算停用主机的数量.
     *
     * @return the int
     * @throws ManagerException the service exception
     */
    int countDisableHost() throws ManagerException;

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
     * @throws ManagerException the service exception
     */
    int countDangerHost() throws ManagerException;

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
    int countOkHost() throws ManagerException;

    /**
     * Count all host state state num dto.
     *
     * @return the state num dto
     * @throws ManagerException the service exception
     */
    StateNumDTO countAllHostState() throws ManagerException;


    /**
     * Count all host int.
     *
     * @return the int
     */
    int countAllHost() throws AuthExpireException;

    /**
     * Gets host.
     *
     * @param hostId the host id
     * @return the host
     */
    HostDO getHost(String... hostId);
}