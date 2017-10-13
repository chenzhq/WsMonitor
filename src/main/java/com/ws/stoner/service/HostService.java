package com.ws.stoner.service;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefHostDTO;
import com.ws.stoner.model.view.host.HostDetailInterfaceVO;
import com.ws.stoner.model.view.host.HostDetailPointVO;
import com.ws.stoner.model.view.host.HostDetailVO;

import java.util.List;

/**
 * Created by chenzheqi on 2017/5/23.
 */
public interface HostService {

/*
 *zabbix api方法
 */

    /*
     count 数据获取的方法
     */

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

    /**
     * 根据指定的hostIds 统计 警告主机数量
     * @param hostIds
     * @return
     * @throws ServiceException
     */
    int countWarningHostByHostIds(List<String> hostIds) throws ServiceException;

    /**
     * 根据指定的hostIds 统计 严重主机数量
     * @param hostIds
     * @return
     * @throws ServiceException
     */
    int countHighHostByHostIds(List<String> hostIds) throws ServiceException;

    /*
     *list 数据获取的方法
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

    /**
     * 根据指定的 platformIds 获取 List<BriefHostDTO> list  用于 分类菜单 显示设备
     * @param platformIds
     * @return
     * @throws ServiceException
     */
    List<BriefHostDTO> getHostByPlatformIds(List<String> platformIds) throws ServiceException;

/*
设备详情页面下的业务方法
 */

    /**
     * 根据 BriefHostDTO hostDTO 组装 基本信息的 HostDetailVO
     * @return
     * @throws ServiceException
     */
    HostDetailVO getHostDetailByHostDTO(BriefHostDTO hostDTO) throws ServiceException;

    /**
     * 根据 BriefHostDTO hostDTO 组装 设备下所有监控点状态信息 的 pointVO
     * @return
     * @throws ServiceException
     */
    List<HostDetailPointVO> getPointsByHostDTO(BriefHostDTO hostDTO) throws ServiceException;



}
