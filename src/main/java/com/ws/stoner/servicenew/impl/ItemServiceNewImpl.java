package com.ws.stoner.servicenew.impl;

import com.ws.stoner.constant.BaseConsts;
import com.ws.stoner.constant.ValueTypeEnum;
import com.ws.stoner.daonew.ItemDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefItemDTO;
import com.ws.stoner.model.vo.item.*;
import com.ws.stoner.servicenew.ItemService;
import com.ws.stoner.utils.StatusConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongkf on 2017/12/15
 */
@Service
public class ItemServiceNewImpl implements ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemServiceNewImpl.class);

    @Autowired
    private ItemDAO itemDAO;


    @Override
    public List<BriefItemDTO> getItemDTOSByPointId(String pointId) throws ServiceException {
        List<String> pointIds = new ArrayList<>();
        pointIds.add(pointId);
        List<BriefItemDTO> itemDTOS = null;
        try {
            itemDTOS = itemDAO.getItemDTOSByPointIds(pointIds);
        } catch (DAOException e) {
            logger.error("调用 itemDAO 获取 itemDTOS出错！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        return itemDTOS;

    }

    @Override
    public List<ItemVO> getItemVOSByPOintId(String pointId) throws ServiceException {
        List<String> pointIds = new ArrayList<>();
        pointIds.add(pointId);
        List<BriefItemDTO> itemDTOS = null;
        try {
            itemDTOS = itemDAO.getItemDTOSByPointIds(pointIds);
        } catch (DAOException e) {
            logger.error("调用 itemDAO 获取 itemDTOS出错！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        List<ItemVO> itemVOS = new ArrayList<>();
        for(BriefItemDTO itemDTO :itemDTOS) {
            ItemVO itemVO = getItemVOByDTO(itemDTO);
            itemVOS.add(itemVO);
        }
        return itemVOS;
    }

    @Override
    public ItemVO getItemVOByDTO(BriefItemDTO itemDTO) throws ServiceException {
        //获取监控项数据类型
        String type = StatusConverter.valueTypeTransform(itemDTO.getValueType(),itemDTO.getDataType());
        ItemVO itemVO = null;
        if(ValueTypeEnum.INTEGER.type.equals(type)) {
            itemVO = new LongItemVO(Long.parseLong(itemDTO.getLastValue()));
        }else if(ValueTypeEnum.FLOAT.type.equals(type)) {
            itemVO = new FloatItemVO(Float.parseFloat(itemDTO.getLastValue()));
        }else if(ValueTypeEnum.BOOLEAN.type.equals(type)) {
            itemVO = new BoolItemVO(Boolean.parseBoolean(itemDTO.getLastValue()));
        }else {
            itemVO = new StringItemVO(itemDTO.getLastValue());
        }
        itemVO.setId(itemDTO.getItemId());
        itemVO.setName(itemDTO.getName());
        itemVO.setState(StatusConverter.statusTransForItem(itemDTO.getCustomState(),itemDTO.getWeight()));
        itemVO.setValueType(type);
        if(itemDTO.getLastTime() == null) {
            itemVO.setLastTime("未取到值");
        }else {
            itemVO.setLastTime(itemDTO.getLastTime().format(BaseConsts.TIME_FORMATTER));
        }

        itemVO.setUnit(itemDTO.getUnits());
        itemVO.setWeight(itemDTO.getWeight());
        itemVO.setCore(itemDTO.getWeight()!=0);
        return itemVO;
    }

    @Override
    public List<ItemVO> getItemVOSByHostId(String hostId) throws ServiceException {
        List<String> hostIds = new ArrayList<>();
        hostIds.add(hostId);
        List<BriefItemDTO> itemDTOS = null;
        try {
            itemDTOS = itemDAO.getItemDTOByHostIds(hostIds);
        } catch (DAOException e) {
            logger.error("调用 itemDAO getItemVOSByHostId 错误！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        List<ItemVO> itemVOS = new ArrayList<>();
        for(BriefItemDTO itemDTO : itemDTOS) {
            ItemVO itemVO = getItemVOByDTO(itemDTO);
            itemVOS.add(itemVO);
        }
        return itemVOS;
    }

    @Override
    public List<ItemVO> getCoreItemVOSByHostId(String hostId) throws ServiceException {
        List<String> hostIds = new ArrayList<>();
        hostIds.add(hostId);
        List<BriefItemDTO> itemDTOS = null;
        try {
            itemDTOS = itemDAO.getCoreItemByHostIds(hostIds);
        } catch (DAOException e) {
            logger.error("调用 itemDAO getCoreItemByHostIds 错误！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        List<ItemVO> itemVOS = new ArrayList<>();
        for(BriefItemDTO itemDTO : itemDTOS) {
            ItemVO itemVO = getItemVOByDTO(itemDTO);
            itemVOS.add(itemVO);
        }
        return itemVOS;
    }

    @Override
    public BriefItemDTO getDTOByItemId(String itemId) {
        List<String> itemIds = new ArrayList<>();
        itemIds.add(itemId);
        BriefItemDTO itemDTO = null;
        try {
            List<BriefItemDTO> itemDTOS = itemDAO.getItemDTOSByItemIds(itemIds);
            if(itemDTOS != null && itemDTOS.size() > 0) {
                itemDTO = itemDTOS.get(0);
            }
        } catch (DAOException e) {
            logger.error("调用 itemDAO getItemDTOSByItemIds 错误！",e.getMessage());
            new ServiceException(e.getMessage());
        }
        return itemDTO;
    }
}
