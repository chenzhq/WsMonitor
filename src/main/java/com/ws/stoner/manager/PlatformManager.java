package com.ws.stoner.manager;

import com.ws.bix4j.access.hostgroup.HostGroupGetRequest;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.model.dto.BriefPlatformDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by zkf on 2017/6/1.
 */
@CacheConfig(cacheNames = "platformManager")
public interface PlatformManager {
    /**
     * List hostgroup list.
     *
     * @return the list
     * @throws AuthExpireException the auth expire exception
     */
    List<BriefPlatformDTO> listPlatform(HostGroupGetRequest request) throws ManagerException;

    /**
     * 根据request获取业务平台数量 hostgroup number
     * @return
     * @throws ManagerException
     */
    int countPlatform(HostGroupGetRequest request) throws ManagerException;

    /**
     * 获取所有业务平台数量 platform number all
     * @return
     * @throws ManagerException
     */
    int countAllPlatform() throws ManagerException;

    /**
     * 获取问题业务平台数量  problem number
     * @return
     * @throws ManagerException
     */
    int countProblemPlatform(List<String> triggerIds) throws ManagerException;


    /**
     * 获取正常的业务平台数量 OK number
     * @return
     * @throws ManagerException
     */
    int countOkPlatform(List<String> triggerIds) throws ManagerException;

    /**
     * 获取指定业务平台的所有主机数量 all host number by platformIds
     */
    int countAllHostByPlatformIds(List<String> platformIds) throws ManagerException;

    /**
     * 获取指定业务平台的问题主机数量 problem host number by platformIds
     * @param platformIds
     * @return
     * @throws ManagerException
     */
    int countProblemHostByPlatformIds(List<String> platformIds) throws ManagerException;


/*
 *list platform
 */

    /**
     * 获取简约 platform list all
     * @return
     * @throws ManagerException
     */
    @Cacheable
    List<BriefPlatformDTO> listAllPlatform() throws ManagerException;

    /**
     * 获取问题的 platform list problem
     * @return
     * @throws ManagerException
     */
    List<BriefPlatformDTO> listProblemPlatform(List<String> triggerIds) throws ManagerException;

}
