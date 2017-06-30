package com.ws.stoner.manager;

import com.ws.bix4j.access.host.HostGetRequest;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.model.dto.BriefHostDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by chenzheqi on 2017/5/23.
 */
@CacheConfig(cacheNames = "hostManager")
public interface HostManager {

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
     * 获取问题主机数量
     * 1、根据触发器状态获取问题主机
     * filter: a.  state:up to date  value:problem  only_true:true
     *         b.  state:unknown
     * 2、筛选四种监控接口中至少一个有问题的主机，这是另一部分问题状态的主机filter+searchByAny
     * @return
     * @throws ManagerException
     */
    int countProblemHost(List<String> triggerIds) throws ManagerException;

    /**
     * 获取正常主机的数量
     *  okHostNum = allHostNum - problemHostNum
     * @return
     * @throws ManagerException
     */
    int countOkHost(List<String> triggerIds) throws ManagerException;

/*
 *list host
 */

    /**
     *  获取简约所有主机list 剔除停用的
     * @return
     * @throws ManagerException
     */
    @Cacheable
    List<BriefHostDTO> listAllHost() throws ManagerException;

    /**
     * 获取问题主机 list  problem
     * @return
     * @throws ManagerException
     */
    List<BriefHostDTO> listProblemHost(List<String> triggerIds) throws ManagerException;

    /**
     * 获取OK主机 list OK
     * @return
     * @throws ManagerException
     */
    List<BriefHostDTO> listOkHost(List<String> triggerIds) throws ManagerException;





}
