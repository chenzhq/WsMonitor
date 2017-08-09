package com.ws.stoner.service;

import com.ws.bix4j.access.hostgroup.HostGroupGetRequest;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefPlatformDTO;
import com.ws.stoner.model.view.*;

import java.util.List;
import java.util.Map;

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
     * 获取警告业务平台数量  warning number
     * @return
     * @throws ServiceException
     */
    int countWarningPlatform() throws ServiceException;


    /**
     * 获取严重业务平台数量  high number
     * @return
     * @throws ServiceException
     */
    int countHighPlatform() throws ServiceException;

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
     * 获取严重的 platform list high
     * @return
     * @throws ServiceException
     */
    List<BriefPlatformDTO> listHighPlatform() throws ServiceException;

/*
 * 业务监控模块
 */

    /**
     * 根据 platformId 获取指定业务平台数据 其中包括 其下设备 hostDTOS
     * @param platformId
     * @return
     * @throws ServiceException
     */
    BriefPlatformDTO getPlatformByPlatformId(String platformId) throws ServiceException;

    /**
     * 根据 业务平台Ids 获取 健康值  Map<key:platformId ,value:health>
     * @param platformIds
     * @return
     * @throws ServiceException
     */
    Map<String,Float> getHealthByPlatformIds(List<String> platformIds) throws ServiceException;

    /**
     * 获取页面展示上的 业务平台列表
     * @return
     * @throws ServiceException
     */
    List<PlatformListVO> getPlatformList() throws ServiceException;

    /**
     * 获取业务监控的 所有 业务方块 PlatformBlockVO
     * @return
     * @throws ServiceException
     */
    List<PlatformBlockVO> getPlatformBlock() throws ServiceException;

    /**
     * 获取业务监控的 指定id的 业务方块 PlatformBlockVO
     * @return
     * @throws ServiceException
     */
    PlatformBlockVO getPlatformBlockById(String platformId) throws ServiceException;

    /**
     * 获取指定 platformId 的 List<PlatDetailHostVO> 用于分类菜单显示
     * @param platformId
     * @return
     * @throws ServiceException
     */
    List<PlatDetailHostVO> getHostsByPlatformId(String platformId) throws ServiceException;

    /**
     * 获取指定 hostId 的 List<PlatDetailItemVO> 用于分类菜单 显示
     * @param hostId
     * @return
     * @throws ServiceException
     */
    List<PlatDetailPointVO> getPointsByHostId(String hostId) throws ServiceException;

    /**
     * 获取指定 pointId 的 List<PlatDetailPointVO> 用于分类菜单 显示
     * @param pointId
     * @return
     * @throws ServiceException
     */
    List<PlatDetailItemVO> getItemsByPointId(String pointId) throws ServiceException;

}
