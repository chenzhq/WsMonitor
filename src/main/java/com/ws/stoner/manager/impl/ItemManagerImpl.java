package com.ws.stoner.manager.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.item.ItemGetRequest;
import com.ws.bix4j.bean.ItemDO;
import com.ws.bix4j.exception.ZApiException;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.manager.ItemManager;
import com.ws.stoner.model.dto.BriefItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pc on 2017/6/9.
 */
@Service
public class ItemManagerImpl implements ItemManager {

    private static final Logger logger = LoggerFactory.getLogger(HostManagerImpl.class);
    @Autowired
    private ZApi zApi;


    /**
     * 根据request 获取 所有的item
     * @return
     * @throws ManagerException
     */
    @Override
    public List<BriefItemDTO> listItem(ItemGetRequest request) throws ManagerException {
        List<BriefItemDTO> items ;
        try {
            items = zApi.Item().get(request,BriefItemDTO.class);
        } catch (ZApiException e) {
            e.printStackTrace();
            return null;
        }
        return items;
    }

    /**
     * 获取在给定 触发器trigger list中的 item
     * @return
     * @throws ManagerException
     */
    @Override
    public List<ItemDO> listItemByTriggerIds(List<String> triggerIds) throws ManagerException {
        ItemGetRequest itemGetRequest = new ItemGetRequest();
        itemGetRequest.getParams().setTriggerIds(triggerIds);
        List<ItemDO> items ;
        try {
            items = zApi.Item().get(itemGetRequest);
        } catch (ZApiException e) {
            e.printStackTrace();
            return null;
        }
        return items;

    }
}
