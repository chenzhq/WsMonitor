package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.ZApiParameter;
import com.ws.bix4j.access.item.ItemGetRequest;
import com.ws.bix4j.bean.ItemDO;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.constant.StatusEnum;
import com.ws.stoner.dao.MongoItemDAO;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.DO.mongo.Item;
import com.ws.stoner.model.dto.*;
import com.ws.stoner.model.view.HostDetailPointItemVO;
import com.ws.stoner.model.view.PointDetailItemDatasVO;
import com.ws.stoner.service.HistoryService;
import com.ws.stoner.service.ItemService;
import com.ws.stoner.service.TriggerService;
import com.ws.stoner.utils.StatusConverter;
import com.ws.stoner.utils.ThresholdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.*;

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

    @Autowired
    private HistoryService historyService;

    @Autowired
    private TriggerService triggerService;


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
     * 根据 itemIds 获取指定的 itemDTOS
     * @param itemIds
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefItemDTO> getItemsByItemIds(List<String> itemIds) throws ServiceException {
        ItemGetRequest itemGetRequest = new ItemGetRequest();
        itemGetRequest.getParams()
                .setMonitored(true)
                .setItemIds(itemIds)
                .setOutput(BriefItemDTO.PROPERTY_NAMES);
        List<BriefItemDTO> itemDTOS = listItem(itemGetRequest);
        return itemDTOS;
    }

    /**
     * 根据指定的hostids 获取相应的数值型的 items BriefItemDTO  int和float类型
     * @param hostIds
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefItemDTO> getValueItemsByHostIds(List<String> hostIds) throws ServiceException {
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
     * 根据指定的 pointids 获取相应数值型的 items BriefItemDTO value_type =0,3
     * @param pointIds
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefItemDTO> getValueItemsByPointIds(List<String> pointIds) throws ServiceException {
        ItemGetRequest itemGetRequest = new ItemGetRequest();
        Map<String,Object> itemFilter = new HashMap<>();
        itemFilter.put("value_type", Arrays.toString(new int[]{ZApiParameter.ITEM_VALUE_TYPE.NUMERIC_UNSIGNED.value,ZApiParameter.ITEM_VALUE_TYPE.NUMERIC_FLOAT.value}));
        itemGetRequest.getParams()
                .setMonitored(true)
                .setApplicationIds(pointIds)
                .setOutput(BriefItemDTO.PROPERTY_NAMES)
                .setFilter(itemFilter);
        List<BriefItemDTO> itemDTOS = listItem(itemGetRequest);
        return itemDTOS;
    }


    /**
     * pointIds 获取相应的所有类型 items BriefItemDTO
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
     * 根据 hostIds 获取附带有触发器的 items BriefItemDTO
     * @param hostIds
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefItemDTO> getItemsWithTriggersByHostIds(List<String> hostIds) throws ServiceException {
        ItemGetRequest itemGetRequest = new ItemGetRequest();
        itemGetRequest.getParams()
                .setMonitored(true)
                .setHostIds(hostIds)
                .setWithTriggers(true)
                .setOutput(BriefItemDTO.PROPERTY_NAMES);
        List<BriefItemDTO> itemDTOS = listItem(itemGetRequest);
        return itemDTOS;
    }

    /**
     * 根据 pointIds 获取附带有触发器的 items BriefItemDTO
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

    @Override
    public boolean saveGraphItemFromMongo(Item item) throws ServiceException {
        try {
            mongoItemDAO.save(item);
        } catch (DAOException e) {
            logger.error("保存 mongodb 的 item 错误！{}", e.getMessage());
            new ServiceException(e.getMessage());
            return false;
        }
        return true;
    }


    /**
     * 根据 itemId 组装监控点详情页面中 时序数据 的业务数据
     * @param itemId
     * @return
     * @throws ServiceException
     */
    @Override
    public List<HostDetailPointItemVO> getItemDatasByItemId(String itemId, int time) throws ServiceException {
        List<String> itemIds = new ArrayList<>();
        itemIds.add(itemId);
        //step1:根据itemId获取 itemDTO
        BriefItemDTO itemDTO = getItemsByItemIds(itemIds).get(0);
        //step2:根据itemId 获取 historyDTOS
        List<BriefHistoryDTO> historyDTOS = null;
        if(time == 40) {
            historyDTOS = historyService.getHistoryByItemIdLimit(itemDTO.getItemId(),itemDTO.getValueType(),time);
        }else {
            historyDTOS = historyService.getHistoryByItemId(itemDTO.getItemId(),itemDTO.getValueType(),time);
        }
        //step3:根据itemIds获取相关触发器 triggerDTO list 来获取阀值
        List<BriefTriggerDTO> triggerDTOS = triggerService.getTriggersByItemIds(itemIds);
        String highPoint = null;
        String warningPoint = null;
        String symbol = null;
        Boolean withTrigger = false;
        if(triggerDTOS.size() != 0) {
            withTrigger = true;
            //阀值赋值：highPoint,warningPoint
            //循环triggerDTOS，筛选出属于该itemDTO的触发器，取List<String> expression,priority  ,
            for(BriefTriggerDTO triggerDTO : triggerDTOS) {
                String expression = triggerDTO.getExpression();
                symbol = ThresholdUtils.getThresholdSymbol(expression);
                if(triggerDTO.getPriority() == 2) {
                    // priority为2:警告阀值取expression的逻辑比较符号后面数据；
                    warningPoint = ThresholdUtils.getThresholdValue(expression);
                }else if(triggerDTO.getPriority() == 4) {
                    // priority为4:严重阀值取expression的逻辑比较符号后面数据；
                    highPoint = ThresholdUtils.getThresholdValue(expression);
                }
            }
        }
        //step4:循环 historyDTOS 赋值 HostDetailPointItemVO 组装成VOS
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<HostDetailPointItemVO> itemHistoryDatas = new ArrayList<>();
        for(BriefHistoryDTO historyDTO : historyDTOS) {
            HostDetailPointItemVO itemHistoryData = new HostDetailPointItemVO();
            itemHistoryData.setItemId(itemDTO.getItemId());
            itemHistoryData.setName(itemDTO.getName());
            itemHistoryData.setUnits(itemDTO.getUnits());
            itemHistoryData.setValue(ThresholdUtils.transformValueUnits(historyDTO.getValue(),itemDTO.getUnits()));
            itemHistoryData.setLastTime(historyDTO.getLastTime().format(formatter));
            itemHistoryData.setWithTriggers(withTrigger);
            itemHistoryData.setWarningPoint(warningPoint);
            itemHistoryData.setHighPoint(highPoint);
            //state
            //数据化 warningPoint 和 highPoint
            Float warningPointValue = null;
            Float highPointValue = null;
            String state = null;
            if(warningPoint != null) {
                warningPointValue =Float.parseFloat(ThresholdUtils.getTransformValue(warningPoint));
            }
            if(highPoint != null) {
                highPointValue =Float.parseFloat(ThresholdUtils.getTransformValue(highPoint));
            }
            Float valueInfo = Float.parseFloat(historyDTO.getValue());
            //状态转换
            if(warningPointValue != null || highPointValue != null) {
                state = StatusConverter.getStatusByThresholdValue(valueInfo,warningPointValue,highPointValue,symbol);
            }else{
                state = StatusEnum.OK.getName();
            }
            itemHistoryData.setState(state);
            itemHistoryDatas.add(itemHistoryData);
        }
        return itemHistoryDatas;

    }
}
