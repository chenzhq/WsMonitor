package com.ws.stoner.service;

import com.ws.bix4j.access.hostgroup.HostGroupGetRequest;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefPlatformDTO;

import java.util.List;

/**
 * Created by zkf on 2017/6/1.
 */
public interface PlatformService {
    /**
     * List hostgroup list.
     *
     * @return the list
     * @throws AuthExpireException the auth expire exception
     */
    List<BriefPlatformDTO> listPlatform(HostGroupGetRequest request) throws ServiceException;

    /**
     * 根据request获取业务平台数量 hostgroup number
     * @return
     * @throws ServiceException
     */
    int countPlatform(HostGroupGetRequest request) throws ServiceException;

    /**
     * 获取所有业务平台数量 platform number all
     * @return
     * @throws ServiceException
     */
    int countAllPlatform() throws ServiceException;

    /**
     * 获取告警业务平台数量  warning number
     * @return
     * @throws ServiceException
     */
    int countWarningPlatform() throws ServiceException;


    /**
     * 获取严重业务平台数量  hight number
     * @return
     * @throws ServiceException
     */
    int countHightPlatform() throws ServiceException;

    /**
     * 获取正常的业务平台数量 OK number
     * @return
     * @throws ServiceException
     */
    int countOkPlatform() throws ServiceException;

    /**
     * 获取指定业务平台的所有主机数量 all host number by platformIds
     */
    int countAllHostByPlatformIds(List<String> platformIds) throws ServiceException;

    /**
     * 获取指定业务平台的问题主机数量 problem host number by platformIds
     * @param platformIds
     * @return
     * @throws ServiceException
     */
    int countProblemHostByPlatformIds(List<String> platformIds) throws ServiceException;


/*
 *list platform
 */

    /**
     * 获取简约 platform list all
     * @return
     * @throws ServiceException
     */
    List<BriefPlatformDTO> listAllPlatform() throws ServiceException;

    /**
     * 获取告警的 platform list warning
     * @return
     * @throws ServiceException
     */
    List<BriefPlatformDTO> listWarningPlatform() throws ServiceException;

    /**
     * 获取严重的 platform list hight
     * @return
     * @throws ServiceException
     */
    List<BriefPlatformDTO> listHightPlatform() throws ServiceException;

}
