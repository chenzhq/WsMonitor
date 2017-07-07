package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.item.ItemGetRequest;
import com.ws.bix4j.bean.ItemDO;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.service.ItemSerivce;
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
public class ItemSerivceImpl implements ItemSerivce {

    private static final Logger logger = LoggerFactory.getLogger(ItemSerivceImpl.class);
    @Autowired
    private ZApi zApi;


    /**
     * 根据request 获取 所有的item
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefItemDTO> listItem(ItemGetRequest request) throws ServiceException {
        List<BriefItemDTO> items ;
        try {
            items = zApi.Item().get(request,BriefItemDTO.class);
        } catch (ZApiException e) {
            if (e.getCode().equals(ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE)) {
                throw new ServiceException("");
            }
            e.printStackTrace();
            logger.error("查询监控项数量错误！{}", e.getMessage());
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
            items = zApi.Item().get(itemGetRequest);
        } catch (ZApiException e) {
            if (e.getCode().equals(ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE)) {
                throw new ServiceException("");
            }
            e.printStackTrace();
            logger.error("查询监控项list错误！{}", e.getMessage());
            return null;
        }
        return items;

    }
}
