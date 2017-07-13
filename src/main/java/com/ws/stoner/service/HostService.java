package com.ws.stoner.service;

import com.ws.bix4j.access.host.HostGetRequest;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefHostDTO;
import com.ws.stoner.model.view.HostDetailVO;

import java.util.List;

/**
 * Created by chenzheqi on 2017/5/23.
 */
public interface HostService {

/*
 *count host
 */

    /**
     * 根据 request 获取主机总数量
     * @return
     * @throws ServiceException
     */
    int countHost(HostGetRequest request) throws ServiceException;



    /**
     * List host list.
     *
     * @return the list
     * @throws ServiceException the auth expire exception
     */
    List<BriefHostDTO> listHost(HostGetRequest request) throws ServiceException;

    /**
     * 获取主机总数量，排除停用主机，filter： status:0
     * @return
     * @throws ServiceException
     */
    int countAllHost() throws ServiceException;

    /**
     * 获取告警主机数量
     * 1、根据custom_state 和 custom_available_state字段联合判断是否是问题主机：
     *  custom_state = 1，表示根据触发器状态是问题设定主机为问题
     *  custom_available_state = 1，表示根据四种接口判断是否为问题主机
     * @return
     * @throws ServiceException
     */
    int countWarningHost() throws ServiceException;

    /**
     * 获取严重主机数量
     * 1、根据custom_state 和 custom_available_state字段联合判断是否是严重主机：
     *  custom_state = 2，表示根据触发器状态是问题设定主机为问题 或者
     *  custom_available_state = 1，表示根据四种接口判断是否为问题主机
     * @return
     * @throws ServiceException
     */
    int countHighHost() throws ServiceException;

    /**
     * 获取正常主机的数量
     * 根据custom_state 和 custom_available_state字段联合判断是否是正常主机：
     * custom_state和custom_available_state同时为0即为正常
     * @return
     * @throws ServiceException
     */
    int countOkHost() throws ServiceException;

/*
 *list host
 */

    /**
     *  获取简约所有主机list 剔除停用的
     * @return
     * @throws ServiceException
     */
    List<BriefHostDTO> listAllHost() throws ServiceException;

    /**
     * 获取警告主机 list  warning
     * @return
     * @throws ServiceException
     */
    List<BriefHostDTO> listWarningHost() throws ServiceException;

    /**
     * 获取严重主机 list  hight
     * @return
     * @throws ServiceException
     */
    List<BriefHostDTO> listHightHost() throws ServiceException;

    /**
     * 获取OK主机 list OK
     * @return
     * @throws ServiceException
     */
    List<BriefHostDTO> listOkHost() throws ServiceException;


    /**
     * 根据指定的 hostids 获取 List<BriefHostDTO> list
     * @param hostIds
     * @return
     * @throws ServiceException
     */
    List<BriefHostDTO> getHostsByHostIds(List<String> hostIds) throws ServiceException;



}
