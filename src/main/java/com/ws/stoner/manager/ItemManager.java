package com.ws.stoner.manager;


import com.ws.bix4j.access.item.ItemGetRequest;
import com.ws.bix4j.bean.ItemDO;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.model.dto.BriefItemDTO;

import java.util.List;

/**
 * Created by zkf on 2017/6/9.
 */
public interface ItemManager {
    /**
     * 根据request请求获取item list
     *
     * @return the list
     * @throws AuthExpireException the auth expire exception
     */
    List<BriefItemDTO> listItem(ItemGetRequest request) throws AuthExpireException;

    /**
     * 获取满足触发器在提供的 TriggerIds 内的所有item
     * @return
     * @throws ManagerException
     */
    List<ItemDO> listItemByTriggerIds(List<String> triggerIds) throws ManagerException;


}
