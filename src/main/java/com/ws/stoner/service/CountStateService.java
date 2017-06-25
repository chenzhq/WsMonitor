package com.ws.stoner.service;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.StateNumDTO;
import java.util.List;

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
 * hostGroup service
 */
    /**
     * 获取所有业务平台数量 platform number all
     * @return
     * @throws ServiceException
     */
    int countAllPlatform() throws ServiceException;

    /**
     * 获取问题业务平台数量  problem number
     * @return
     * @throws ServiceException
     */
    int countProblemPlatform() throws ServiceException;


    /**
     * 获取正常的业务平台数量 OK number
     * @return
     * @throws ServiceException
     */
    int countOkPlatform() throws ServiceException;



/*
 * point service
 */
    /**
     * 获取所有的监控点
     * @return
     * @throws ServiceException
     */
    int countAllPoint() throws ServiceException;

    /**
     * 获取所有的问题监控点
     * @return
     * @throws ServiceException
     */
    int countProblemPoint() throws ServiceException;

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
 * item service
 */



/*
 * trigger service
 */



}
