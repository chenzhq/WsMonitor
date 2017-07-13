package com.ws.stoner.service;


import com.ws.bix4j.access.item.ItemGetRequest;
import com.ws.bix4j.bean.ItemDO;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.DO.mongo.Item;
import com.ws.stoner.model.dto.BriefItemDTO;

import java.util.List;

/**
 * Created by zkf on 2017/6/9.
 */
public interface ItemService {
    /**
     * 根据request请求获取item list
     *
     * @return the list
     * @throws ServiceException the auth expire exception
     */
    List<BriefItemDTO> listItem(ItemGetRequest request) throws ServiceException;

    /**
     * 获取满足触发器在提供的 TriggerIds 内的所有item
     * @return
     * @throws ServiceException
     */
    List<ItemDO> listItemByTriggerIds(List<String> triggerIds) throws ServiceException;

    /**
     * 根据指定的hostids 获取相应的 items BriefItemDTO
     * @param hostIds
     * @return
     * @throws ServiceException
     */
    List<BriefItemDTO> getItemsByHostIds(List<String> hostIds) throws ServiceException;

    /**
     * pointIds 获取相应的 items BriefItemDTO
     * @param pointIds
     * @return
     * @throws ServiceException
     */
    List<BriefItemDTO> getItemsByPointIds(List<String> pointIds) throws ServiceException;

    /**
     * 在mongodb数据库中根据hostid 查询出所有的 item
     * @param hostId
     * @return
     * @throws ServiceException
     */
    List<Item> getItemsByHostIdFromMongo(String hostId) throws ServiceException;
}
