package com.ws.stoner.daonew;

import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.dto.BriefHostDTO;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/11
 */
public interface HostDAO {
/*
*zabbix api方法
*/

    /**
     * 统计所有设备数
     * @return
     * @throws DAOException
     */
    int countAllHosts() throws DAOException;

    /**
     *  获取简约所有主机list 剔除停用的 包含points
     * @return
     * @throws DAOException
     */
    List<BriefHostDTO> listAllHosts() throws DAOException;

    /**
     *  获取所有主机list 不包含points
     * @return
     * @throws DAOException
     */
    List<BriefHostDTO> listAllHostsNoPoints() throws DAOException;

    /**
     * 获取所有主机list 包含 platform
     * @return
     * @throws DAOException
     */
    List<BriefHostDTO> listAllHostsWithPlatform() throws DAOException;
    /**
     * 根据 hostIds 获取 hostDTOS
     * @param hostIds
     * @return
     * @throws DAOException
     */
    List<BriefHostDTO> getHostsByHostIds(List<String> hostIds) throws DAOException;

    /**
     * 根据 hostIds 获取 hostDTOS 带 pointDTOS
     * @param hostIds
     * @return
     * @throws DAOException
     */
    List<BriefHostDTO> getHostsByHostIdsWithPoint(List<String> hostIds) throws DAOException;

    /**
     * 根据 platformIds 查询所有的 hostDTOS 带 platDTOS 和 pointDTOS
     * @param platformIds
     * @return
     * @throws DAOException
     */
    List<BriefHostDTO> getHostsByPlatIds(List<String> platformIds) throws DAOException;

    /**
     * 根据 platformIds 查询所有的 hostDTOS 不带 pointDTOS
     * @param platformIds
     * @return
     * @throws DAOException
     */
    List<BriefHostDTO> getHostsByPlatIdsWithOutPoint(List<String> platformIds) throws DAOException;


}
