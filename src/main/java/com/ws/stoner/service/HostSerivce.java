package com.ws.stoner.service;

import com.ws.bix4j.access.host.HostGetRequest;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.model.dto.BriefHostDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by chenzheqi on 2017/5/23.
 */
public interface HostSerivce {

/*
 *count host
 */

    /**
     * 根据 request 获取主机总数量
     * @return
     * @throws ManagerException
     */
    int countHost(HostGetRequest request) throws ManagerException;



    /**
     * List host list.
     *
     * @return the list
     * @throws ManagerException the auth expire exception
     */
    List<BriefHostDTO> listHost(HostGetRequest request) throws ManagerException;

    /**
     * 获取主机总数量，排除停用主机，filter： status:0
     * @return
     * @throws ManagerException
     */
    int countAllHost() throws ManagerException;

    /**
     * 获取告警主机数量
     * 1、根据custom_state 和 custom_available_state字段联合判断是否是问题主机：
     *  custom_state = 1，表示根据触发器状态是问题设定主机为问题
     *  custom_available_state = 1，表示根据四种接口判断是否为问题主机
     * @return
     * @throws ManagerException
     */
    int countWarningHost() throws ManagerException;

    /**
     * 获取严重主机数量
     * 1、根据custom_state 和 custom_available_state字段联合判断是否是严重主机：
     *  custom_state = 2，表示根据触发器状态是问题设定主机为问题 或者
     *  custom_available_state = 1，表示根据四种接口判断是否为问题主机
     * @return
     * @throws ManagerException
     */
    int countHightHost() throws ManagerException;

    /**
     * 获取正常主机的数量
     * 根据custom_state 和 custom_available_state字段联合判断是否是正常主机：
     * custom_state和custom_available_state同时为0即为正常
     * @return
     * @throws ManagerException
     */
    int countOkHost() throws ManagerException;

/*
 *list host
 */

    /**
     *  获取简约所有主机list 剔除停用的
     * @return
     * @throws ManagerException
     */
    List<BriefHostDTO> listAllHost() throws ManagerException;

    /**
     * 获取警告主机 list  warning
     * @return
     * @throws ManagerException
     */
    List<BriefHostDTO> listWarningHost() throws ManagerException;

    /**
     * 获取严重主机 list  hight
     * @return
     * @throws ManagerException
     */
    List<BriefHostDTO> listHightHost() throws ManagerException;

    /**
     * 获取OK主机 list OK
     * @return
     * @throws ManagerException
     */
    List<BriefHostDTO> listOkHost() throws ManagerException;





}
