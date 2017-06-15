package com.ws.stoner.service;

import com.ws.stoner.exception.ServiceException;

/**
 * Created by zkf on 2017/6/12.
 */
public interface CountStateService {

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
     * 获取所有业务平台数量 hostgroup number all
     * @return
     * @throws ServiceException
     */
    int countAllHostGroup() throws ServiceException;

    /**
     * 获取问题业务平台数量  problem
     * @return
     * @throws ServiceException
     */
    int countProblemHostGroup() throws ServiceException;


    /**
     * 获取正常的业务平台数量 OK
     * @return
     * @throws ServiceException
     */
    int countOkHostGroup() throws ServiceException;

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

/*
 * item service
 */



/*
 * trigger service
 */



}
