package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.item.ItemGetRequest;
import com.ws.bix4j.bean.ItemDO;
import com.ws.bix4j.bean.TriggerDO;
import com.ws.bix4j.exception.ZApiException;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pc on 2017/6/9.
 */
@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger logger = LoggerFactory.getLogger(HostServiceImpl.class);
    @Autowired
    private ZApi zApi;


    /**
     * 获取 所有的item
     * @return
     * @throws AuthExpireException
     */
    @Override
    public List<ItemDO> listItem() throws AuthExpireException {
        ItemGetRequest itemGetRequest = new ItemGetRequest();
        List<ItemDO> items ;
        try {
            items = zApi.Item().get(itemGetRequest).getResult();
        } catch (ZApiException e) {
            e.printStackTrace();
            return null;
        }
        return items;
    }

    /**
     * 获取在给定 触发器trigger list中的 item
     * @return
     * @throws ServiceException
     */
    @Override
    public List<ItemDO> listItemByTriggerIds(List<String> triggerIds) throws ServiceException {
        ItemGetRequest itemGetRequest = new ItemGetRequest();
        itemGetRequest.getParams().setTriggerIds(triggerIds);
        List<ItemDO> items ;
        try {
            items = zApi.Item().get(itemGetRequest).getResult();
        } catch (ZApiException e) {
            e.printStackTrace();
            return null;
        }
        return items;

    }
}
