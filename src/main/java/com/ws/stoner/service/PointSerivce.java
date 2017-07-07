package com.ws.stoner.service;

import com.ws.bix4j.access.application.ApplicationGetRequest;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefPointDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by zkf on 2017/6/1.
 */
public interface PointSerivce {

    /**
     * 根据 request 获取监控点数量
     * @param request
     * @return
     * @throws ServiceException
     */
    int countPoint(ApplicationGetRequest request) throws ServiceException;

    /**
     * List applications list.
     *
     * @return the list
     * @throws ServiceException the auth expire exception
     */
    List<BriefPointDTO> listPoint(ApplicationGetRequest request) throws ServiceException;

    /**
     * 获取所有的监控点
     * @return
     * @throws ServiceException
     */
    int countAllPoint() throws ServiceException;

    /**
     * 获取所有的警告监控点  point  warning
     * @return
     * @throws ServiceException
     */
    int countWarningPoint() throws ServiceException;

    /**
     * 获取所有的严重监控点  point  hight
     * @return
     * @throws ServiceException
     */
    int countHightPoint() throws ServiceException;

    /**
     * 获取正常监控点
     * @return
     * @throws ServiceException
     */
    int countOkPoint() throws  ServiceException;

    /**
     * 获取指定主机的所有监控点数量  all point hostids number
     * @return
     * @throws ServiceException
     */
    int countAllPointByHostIds(List<String> hostIds) throws ServiceException;

    /**
     * 获取指定主机的问题监控点数量  problem point hostids number
     * @param hostIds
     * @return
     * @throws ServiceException
     */
    int countProblemPointByHostIds(List<String> hostIds) throws ServiceException;

/*
 * list point
 */

    /**
     * 获取简约监控点application list
     * @return
     * @throws ServiceException
     */
    List<BriefPointDTO> listAllPoint() throws ServiceException;

    /**
     * 获取警告监控点 point list warning
     * @return
     * @throws ServiceException
     */
    List<BriefPointDTO> listWarningPoint() throws ServiceException;

    /**
     * 获取严重监控点 point list hight
     * @return
     * @throws ServiceException
     */
    List<BriefPointDTO> listHightPoint() throws ServiceException;


}
