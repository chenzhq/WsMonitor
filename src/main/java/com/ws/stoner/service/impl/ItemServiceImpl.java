package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.ZApiParameter;
import com.ws.bix4j.access.item.ItemGetRequest;
import com.ws.bix4j.bean.ItemDO;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.dao.MongoItemDAO;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.DO.mongo.Item;
import com.ws.stoner.model.dto.BriefItemDTO;
import com.ws.stoner.model.dto.BriefPointDTO;
import com.ws.stoner.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ws.bix4j.exception.ZApiExceptionEnum.NO_AUTH_ASSIGN;
import static com.ws.bix4j.exception.ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE;

/**
 * Created by pc on 2017/6/9.
 */
@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);
    @Autowired
    private ZApi zApi;

    @Autowired
    private MongoItemDAO mongoItemDAO;


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
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询监控项数量错误！{}", e.getMessage());
            throw new ServiceException(e.getMessage());
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
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询监控项错误！{}", e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return items;

    }

    /**
     * 根据指定的hostids 获取相应的数值型的 items BriefItemDTO  int和float类型
     * @param hostIds
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefItemDTO> getItemsByHostIds(List<String> hostIds) throws ServiceException {
        ItemGetRequest itemGetRequest = new ItemGetRequest();
        Map<String,Object> itemFilter = new HashMap<>();
        itemFilter.put("value_type", Arrays.toString(new int[]{ZApiParameter.ITEM_VALUE_TYPE.NUMERIC_UNSIGNED.value,ZApiParameter.ITEM_VALUE_TYPE.NUMERIC_FLOAT.value}));
        itemGetRequest.getParams()
                .setMonitored(true)
                .setHostIds(hostIds)
                .setOutput(BriefItemDTO.PROPERTY_NAMES)
                .setFilter(itemFilter);
        List<BriefItemDTO> itemDTOS = listItem(itemGetRequest);
        return itemDTOS;
    }



    /**
     * pointIds 获取相应的 items BriefItemDTO
     * @param pointIds
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefItemDTO> getItemsByPointIds(List<String> pointIds) throws ServiceException {
        ItemGetRequest itemGetRequest = new ItemGetRequest();
        itemGetRequest.getParams()
                .setMonitored(true)
                .setApplicationIds(pointIds)
                .setSelectApplications(BriefPointDTO.PROPERTY_NAMES)
                .setOutput(BriefItemDTO.PROPERTY_NAMES);
        List<BriefItemDTO> itemDTOS = listItem(itemGetRequest);
        return itemDTOS;
    }

    /**
     * pointIds 获取附带有触发器的 items BriefItemDTO
     * @param pointIds
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefItemDTO> getItemsWithTriggersByPointIds(List<String> pointIds) throws ServiceException {
        ItemGetRequest itemGetRequest = new ItemGetRequest();
        itemGetRequest.getParams()
                .setMonitored(true)
                .setApplicationIds(pointIds)
                .setWithTriggers(true)
                .setSelectApplications(BriefPointDTO.PROPERTY_NAMES)
                .setOutput(BriefItemDTO.PROPERTY_NAMES);
        List<BriefItemDTO> itemDTOS = listItem(itemGetRequest);
        return itemDTOS;
    }

    /**
     * 在mongodb数据库中根据hostid 查询出所有的 item
     * @param hostId
     * @return
     * @throws ServiceException
     */
    @Override
    public List<Item> getItemsByHostIdFromMongo(String hostId) throws ServiceException {
        List<Item> mongoItems = null;
        try {

            mongoItems  = mongoItemDAO.findItemByHostId(hostId);
        } catch (DAOException e) {
            logger.error("查询mongodb 的 item 错误！{}", e.getMessage());
            new ServiceException(e.getMessage());
        }
        return mongoItems;
    }
}
