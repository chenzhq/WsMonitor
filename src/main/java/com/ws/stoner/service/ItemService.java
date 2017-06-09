package com.ws.stoner.service;


import com.ws.bix4j.bean.ItemDO;
import com.ws.bix4j.bean.TriggerDO;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;

import java.util.List;

/**
 * Created by zkf on 2017/6/9.
 */
public interface ItemService {
    /**
     * List item list.
     *
     * @return the list
     * @throws AuthExpireException the auth expire exception
     */
    List<ItemDO> listItem() throws AuthExpireException;

    /**
     * 获取满足触发器在提供的 TriggerIds 内的所有item
     * @return
     * @throws ServiceException
     */
    List<ItemDO> listItemByTriggerIds(List<String> triggerIds) throws ServiceException;


}
