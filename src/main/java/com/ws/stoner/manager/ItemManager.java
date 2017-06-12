package com.ws.stoner.manager;


import com.ws.bix4j.bean.ItemDO;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ManagerException;

import java.util.List;

/**
 * Created by zkf on 2017/6/9.
 */
public interface ItemManager {
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
     * @throws ManagerException
     */
    List<ItemDO> listItemByTriggerIds(List<String> triggerIds) throws ManagerException;


}
