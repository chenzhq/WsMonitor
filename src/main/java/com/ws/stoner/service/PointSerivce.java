package com.ws.stoner.service;

import com.ws.bix4j.access.application.ApplicationGetRequest;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefPointDTO;

import java.util.List;

/**
 * Created by zkf on 2017/6/1.
 */
public interface PointSerivce {

/*
 *zabbix api方法
 */
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
     * 获取所有的严重监控点  point  high
     * @return
     * @throws ServiceException
     */
    int countHighPoint() throws ServiceException;

    /**
     * 获取正常监控点
     * @return
     * @throws ServiceException
     */
    int countOkPoint() throws  ServiceException;

    /**
     * 根据hostIds 获取 point 所有数量
     * @param hostIds
     * @return
     * @throws ServiceException
     */
    int countAllPointByHostIds(List<String> hostIds) throws ServiceException;

    /**
     * 根据hostIds 获取 point 警告数量
     * @param hostIds
     * @return
     * @throws ServiceException
     */
    int countWarningPointByHostIds(List<String> hostIds) throws ServiceException;

    /**
     * 根据hostIds 获取 point 严重数量
     * @param hostIds
     * @return
     * @throws ServiceException
     */
    int countHighPointByHostIds(List<String> hostIds) throws ServiceException;


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
     * 获取严重监控点 point list high
     * @return
     * @throws ServiceException
     */
    List<BriefPointDTO> listHighPoint() throws ServiceException;

    /**
     * 根据 hostIds 获取  List<BriefPointDTO> list 用于 分类菜单 显示
     * @param hostIds
     * @return
     * @throws ServiceException
     */
    List<BriefPointDTO> getPointsByHostIds(List<String> hostIds) throws ServiceException;

    /**
     * 根据 itemIds 获取 List<BriefPointDTO> list 用于基础信息 名称
     * @param itemIds
     * @return
     * @throws ServiceException
     */
    List<BriefPointDTO> getPointsByItemIds(List<String> itemIds) throws ServiceException;



}
