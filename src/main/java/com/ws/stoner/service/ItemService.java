package com.ws.stoner.service;


import com.ws.bix4j.bean.ItemDO;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.DO.mongo.item.Item;
import com.ws.stoner.model.dto.BriefItemDTO;
import com.ws.stoner.model.view.host.HostDetailPointItemVO;
import com.ws.stoner.model.view.host.HostDetailPointVO;
import com.ws.stoner.model.view.itemvalue.ItemConfigVO;

import java.util.List;

/**
 * Created by zkf on 2017/6/9.
 */
public interface ItemService {

/*
 *ZABBIX API 方法
  */


    /**
     * 获取满足触发器在提供的 TriggerIds 内的所有item
     * @return
     * @throws ServiceException
     */
    List<ItemDO> listItemByTriggerIds(List<String> triggerIds) throws ServiceException;

    /**
     * 根据 itemIds 获取指定的 itemDTOS
     * @param itemIds
     * @return
     * @throws ServiceException
     */
    List<BriefItemDTO> getItemsByItemIds(List<String> itemIds) throws ServiceException ;

    /**
     * 根据 hostIds 获取指定的 itemDTOS select hostDTO pointDTOS triggerDTOS
     * @param hostIds
     * @return
     * @throws ServiceException
     */
    List<BriefItemDTO> getItemsByHostIds(List<String> hostIds) throws ServiceException;

    /**
     * 根据指定的hostids 获取相应的 items BriefItemDTO
     * @param hostIds
     * @return
     * @throws ServiceException
     */
    List<BriefItemDTO> getValueItemsByHostIds(List<String> hostIds) throws ServiceException;

    /**
     * 根据指定的 pointids 获取相应的 items BriefItemDTO value_type =0,3
     * @param pointIds
     * @return
     * @throws ServiceException
     */
    List<BriefItemDTO> getValueItemsByPointIds(List<String> pointIds) throws ServiceException;

    /**
     * 根据指定的 itemIds 获取相应的 items BriefItemDTO value_type =0,3
     * @param itemIds
     * @return
     * @throws ServiceException
     */
    List<BriefItemDTO> getValueItemsByItemIds(List<String> itemIds) throws ServiceException;

    /**
     * pointIds 获取相应的 items BriefItemDTO
     * @param pointIds
     * @return
     * @throws ServiceException
     */
    List<BriefItemDTO> getItemsByPointIds(List<String> pointIds) throws ServiceException;

    /**
     * 根据 platformIds 获取附带有触发器的 items BriefItemDTO
     * @param platformIds
     * @return
     * @throws ServiceException
     */
    List<BriefItemDTO> getItemsWithTriggersByPlatfromIds(List<String> platformIds) throws ServiceException;

    /**
     * 根据 hostIds 获取附带有触发器的 items BriefItemDTO
     * @param hostIds
     * @return
     * @throws ServiceException
     */
    List<BriefItemDTO> getItemsWithTriggersByHostIds(List<String> hostIds) throws ServiceException;

    /**
     * 根据 pointIds 获取附带有触发器的 items BriefItemDTO
     * @param pointIds
     * @return
     * @throws ServiceException
     */
    List<BriefItemDTO> getItemsWithTriggersByPointIds(List<String> pointIds) throws ServiceException;



/*
item 在 mongodb下的业务方法
 */
    /**
     * 在mongodb数据库中根据hostid 查询出所有的 item
     * @param hostId
     * @return
     * @throws ServiceException
     */
    List<Item> getItemsByHostIdFromMongo(String hostId) throws ServiceException;

    /**
     * 在mongodb数据库中根据 itemId 查询出所有的 item
     * @param itemId
     * @return
     * @throws ServiceException
     */
    Item getItemByItemIdFromMongo(String itemId) throws ServiceException;

    /**
     * 在mongodb数据库中 插入保存 item
     * @param item
     * @return
     * @throws ServiceException
     */
    boolean saveGraphItemFromMongo(Item item) throws ServiceException;

    /**
     * 在mongodb数据库中 更新保存 item
     * @param item
     * @return
     * @throws ServiceException
     */
    boolean updateGraphItemFromMongo(Item item) throws ServiceException;

    /**
     * 在mongodb数据库中 根据 itemid 删除 item
     * @param itemId
     * @return
     * @throws ServiceException
     */
    boolean deleteGraphItemFromMongo(String itemId) throws ServiceException;

/*
 *设备详情功能业务service
 */


    /**
     * 根据 pointId 组装设备详情页面中 监控点悬浮框 的业务数据
     * @param pointId
     * @return
     * @throws ServiceException
     */
    HostDetailPointVO getItemsByPointId(String pointId) throws ServiceException;

    /**
     * 根据 pointId 组装监控点详情页面中 概述 的业务数据
     * @param pointId
     * @return
     * @throws ServiceException
     */
    HostDetailPointVO getDetailPointByPointId(String pointId) throws ServiceException;


    /**
     * 根据 itemId 组装监控点详情页面中 时序数据 的业务数据
     * @param itemId
     * @return
     * @throws ServiceException
     */
    List<HostDetailPointItemVO> getItemDatasByItemId (String itemId, int time) throws ServiceException;

    /**
     * 根据itemid 获取 归属的 hostid 和 pointid
     * @param itemId
     * @return
     * @throws ServiceException
     */
    ItemConfigVO getItemConfigByItemId(String itemId) throws ServiceException;
}
