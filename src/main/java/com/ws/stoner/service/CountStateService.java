package com.ws.stoner.service;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.StateNumDTO;

import javax.xml.ws.Service;

/**
 * Created by zkf on 2017/6/12.
 */
public interface CountStateService {

/*
 *主机业务状态组合
 */
     StateNumDTO countHostState() throws ServiceException;

/*
 *业务平台业务状态组合
 */
    StateNumDTO countPlatformState() throws ServiceException;
/*
 *监控点业务状态组合
 */
    StateNumDTO countPointState() throws ServiceException;
/*
 * host service
 */
    /**
     * 获取主机总数量，排除停用主机，filter： status:0
     * @return
     * @throws ServiceException
     */
    int countAllHost() throws ServiceException;

    /**
     * 获取问题主机数量
     * 1、根据触发器状态获取问题主机
     * filter: a.  state:up to date  value:problem  only_true:true
     *         b.  state:unknown
     * 2、筛选四种监控接口中至少一个有问题的主机，这是另一部分问题状态的主机filter+searchByAny
     * @return
     * @throws ServiceException
     */
    int countProblemHost() throws ServiceException;

    /**
     * 获取正常主机的数量
     *  okHostNum = allHostNum - problemHostNum
     * @return
     * @throws ServiceException
     */
    int countOkHost() throws ServiceException;

/*
 * hostGroup service
 */
    /**
     * 获取所有业务平台数量 platform number all
     * @return
     * @throws ServiceException
     */
    int countAllPlatform() throws ServiceException;

    /**
     * 获取问题业务平台数量  problem
     * @return
     * @throws ServiceException
     */
    int countProblemPlatform() throws ServiceException;


    /**
     * 获取正常的业务平台数量 OK
     * @return
     * @throws ServiceException
     */
    int countOkPlatform() throws ServiceException;

/**
 * application service
 */
    /**
     * 获取所有的监控点
     * @return
     * @throws ServiceException
     */
    int countAllApp() throws ServiceException;

    /**
     * 获取所有的问题监控点
     * @return
     * @throws ServiceException
     */
    int countProblemApp() throws ServiceException;

    /**
     * 获取正常监控点
     * @return
     * @throws ServiceException
     */
    int countOkApp() throws  ServiceException;

    /**
     * 获取指定主机的所有监控点数量  all point hostid number
     * @return
     * @throws ServiceException
     */
    int countAllPointByHostId(String hostId) throws ServiceException;

    /**
     * 获取指定主机的问题监控点数量  problem point hostid number
     * @param hostId
     * @return
     * @throws ServiceException
     */
    int countProblemPointByHostId(String hostId) throws ServiceException;

/*
 * item service
 */



/*
 * trigger service
 */



}
