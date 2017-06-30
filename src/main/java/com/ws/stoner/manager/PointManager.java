package com.ws.stoner.manager;

import com.ws.bix4j.access.application.ApplicationGetRequest;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.model.dto.BriefPointDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by zkf on 2017/6/1.
 */
@CacheConfig(cacheNames = "pointManager")
public interface PointManager {

    /**
     * 根据 request 获取监控点数量
     * @param request
     * @return
     * @throws ManagerException
     */
    int countPoint(ApplicationGetRequest request) throws ManagerException;

    /**
     * List applications list.
     *
     * @return the list
     * @throws ManagerException the auth expire exception
     */
    List<BriefPointDTO> listPoint(ApplicationGetRequest request) throws ManagerException;

    /**
     * 获取所有的监控点
     * @return
     * @throws ManagerException
     */
    int countAllPoint() throws ManagerException;

    /**
     * 获取所有的问题监控点
     * @return
     * @throws ManagerException
     */
    int countProblemPoint(List<String> triggerIds) throws ManagerException;

    /**
     * 获取正常监控点
     * @return
     * @throws ManagerException
     */
    int countOkPoint(List<String> triggerIds) throws  ManagerException;

    /**
     * 获取指定主机的所有监控点数量  all point hostids number
     * @return
     * @throws ManagerException
     */
    int countAllPointByHostIds(List<String> hostIds) throws ManagerException;

    /**
     * 获取指定主机的问题监控点数量  problem point hostids number
     * @param hostIds
     * @return
     * @throws ManagerException
     */
    int countProblemPointByHostIds(List<String> hostIds) throws ManagerException;

/*
 * list point
 */

    /**
     * 获取简约监控点application list
     * @return
     * @throws ManagerException
     */
    @Cacheable
    List<BriefPointDTO> listAllPoint() throws ManagerException;

    /**
     * 获取问题监控点 point list
     * @return
     * @throws ManagerException
     */
    List<BriefPointDTO> listProblemPoint(List<String> triggerIds) throws ManagerException;


}
