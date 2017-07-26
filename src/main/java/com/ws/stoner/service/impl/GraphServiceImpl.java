package com.ws.stoner.service.impl;

import com.ws.stoner.constant.StatusEnum;
import com.ws.stoner.dao.MongoGraphDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.DO.mongo.GraphType;
import com.ws.stoner.model.DO.mongo.Item;
import com.ws.stoner.model.dto.BriefHistoryDTO;
import com.ws.stoner.model.dto.BriefItemDTO;
import com.ws.stoner.model.view.HostDetailItemVO;
import com.ws.stoner.service.GraphService;
import com.ws.stoner.service.HistoryService;
import com.ws.stoner.service.ItemService;
import com.ws.stoner.utils.StatusConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pc on 2017/7/18.
 */
@Service
public class GraphServiceImpl implements GraphService {

    private static final Logger logger = LoggerFactory.getLogger(GraphServiceImpl.class);

    @Autowired
    private MongoGraphDAO mongoGraphDAO;

    @Autowired
    private ItemService itemService;

    @Autowired
    private HistoryService historyService;



    /**
     * 根据 valueType 查询出对应支持的图形插件名称
     * @param valueType
     * @return
     * @throws ServiceException
     */
    @Override
    public List<String> getGraphTypeByValueTypeFromMongo(String valueType) throws ServiceException {
        GraphType graphType = null;
        try {
            graphType = mongoGraphDAO.findGraphTypeByValueType(valueType);
        } catch (DAOException e) {
            logger.error("根据 valueType 查询 GraphType 错误！{}", e.getMessage());
            new ServiceException(e.getMessage());
        }
        List<String> graphTypesTemp = Arrays.asList(graphType.getGraphType());
        List<String> graphTypes = new ArrayList<>(graphTypesTemp);
        return graphTypes;
    }

    /**
     * 根据 hostIds 查询出指定设备的 图形监控项 graph item
     * @param hostId
     * @return
     * @throws ServiceException
     */
    @Override
    public List<HostDetailItemVO> getGraphItemByHostId(String hostId) throws ServiceException {
        //取 BriefItemDTO list, 过滤出 value_type=0,3
        List<String> hostIds = new ArrayList<>();
        hostIds.add(hostId);
        List<BriefItemDTO> itemDTOS = itemService.getValueItemsByHostIds(hostIds);
        //取mongodb的所有hostid下的items
        List<Item> mongoItems = itemService.getItemsByHostIdFromMongo(hostIds.get(0));
        //step3:循环 List BriefItemDTO，根据itemid取mongodb的 items，新建ItemVO对象
        List<HostDetailItemVO> itemVOS = new ArrayList<>();
        //用于组装既是问题也是自定义的itemIds
        List<String> problemIds = new ArrayList<>();
        //循环组装自定义item
        for(BriefItemDTO itemDTO : itemDTOS) {
            HostDetailItemVO itemVO = new HostDetailItemVO();
            for(Item mongoItem :mongoItems) {
                if(mongoItem.getItemId().equals(itemDTO.getItemId())) {
                    // if 有匹配的，确定是用户自定义,flag赋给ItemVO的flag，用的是什么图形，graph_type赋给ItemVO的graph_type，graph_name赋给itemVO 的graph_name
                    itemVO.setItemId(itemDTO.getItemId());
                    itemVO.setItemName(itemDTO.getName());
                    itemVO.setFlag(true);
                    itemVO.setGraphName(mongoItem.getGraphName());
                    itemVO.setGraphType(mongoItem.getGraphType());
                    itemVO.setValueType(itemDTO.getValueType());
                    itemVO.setUnits(itemDTO.getUnits());
                    //state
                    if(StatusEnum.HIGH.code == itemDTO.getCustomState()) {
                        itemVO.setState(StatusEnum.HIGH.getName());
                        problemIds.add(itemDTO.getItemId());
                    }else if(StatusEnum.WARNING.code == itemDTO.getCustomState()) {
                        itemVO.setState(StatusEnum.WARNING.getName());
                        problemIds.add(itemDTO.getItemId());
                    }else {
                        itemVO.setState(StatusEnum.OK.getName());
                    }
                    itemVOS.add(itemVO);
                }
            }
        }
        //循环组装问题item
        for(BriefItemDTO itemDTO : itemDTOS) {
            //问题item，且非用户自定义
            if(itemDTO.getCustomState() != StatusEnum.OK.code && !problemIds.contains(itemDTO.getItemId())) {
                HostDetailItemVO itemVO = new HostDetailItemVO();
                itemVO.setItemId(itemDTO.getItemId());
                itemVO.setItemName(itemDTO.getName());
                itemVO.setFlag(false);
                itemVO.setGraphName(itemDTO.getName());
                itemVO.setGraphType("line");
                itemVO.setValueType(itemDTO.getValueType());
                itemVO.setState(StatusConverter.StatusTransform(itemDTO.getCustomState()));
                itemVO.setUnits(itemDTO.getUnits());
                itemVOS.add(itemVO);
            }
        }
        //根据value_type取对应的history.get,时间区间为前1天的数据 得到 BriefHistory list
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for(HostDetailItemVO itemVO : itemVOS) {
            List<BriefHistoryDTO> historyDTOS = historyService.getHistoryByItemId(itemVO.getItemId(),itemVO.getValueType(),1);
            List<Float> datas = new ArrayList<>();
            List<String> dataTime = new ArrayList<>();
            //赋值 取list BriefHistory的 valueList 给 date，lastTimeList 给 data_time，
            for(BriefHistoryDTO historyDTO : historyDTOS) {
                datas.add(Float.parseFloat(historyDTO.getValue()));
                String dataTimeString = historyDTO.getLastTime().format(formatter);
                dataTime.add(dataTimeString);
            }
            itemVO.setData(datas.toArray(new Float[0]));
            itemVO.setDataTime(dataTime.toArray(new String[0]));
        }
        return itemVOS;
    }

    /**
     * 根据 pointId 查询出指定 point 的 图形监控项 graph item 图形报告 标签页
     * @param pointId
     * @return
     * @throws ServiceException
     */
    @Override
    public List<HostDetailItemVO> getGraphItemByPointId(String pointId,int time) throws ServiceException {
        //取 BriefItemDTO list, 过滤出 value_type=0,3
        List<String> pointIds = new ArrayList<>();
        pointIds.add(pointId);
        List<BriefItemDTO> itemDTOS = itemService.getValueItemsByPointIds(pointIds);
        //step3:循环 List BriefItemDTO
        List<HostDetailItemVO> itemVOS = new ArrayList<>();
        //循环组装itemVO
        for(BriefItemDTO itemDTO : itemDTOS) {
                HostDetailItemVO itemVO = new HostDetailItemVO();
                itemVO.setItemId(itemDTO.getItemId());
                itemVO.setItemName(itemDTO.getName());
                itemVO.setGraphName(itemDTO.getName());
                itemVO.setGraphType("line");
                itemVO.setValueType(itemDTO.getValueType());
                itemVO.setUnits(itemDTO.getUnits());
                itemVO.setState(StatusConverter.StatusTransform(itemDTO.getCustomState()));
                itemVOS.add(itemVO);
        }
        //根据value_type取对应的history.get,时间区间为前 time 天的数据 得到 BriefHistory list
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for(HostDetailItemVO itemVO : itemVOS) {
            List<BriefHistoryDTO> historyDTOS = null;
            if(time == 40) {
                historyDTOS = historyService.getHistoryByItemIdLimit(itemVO.getItemId(),itemVO.getValueType(),time);
            }else {
                historyDTOS = historyService.getHistoryByItemId(itemVO.getItemId(),itemVO.getValueType(),time);
            }
            List<Float> datas = new ArrayList<>();
            List<String> dataTime = new ArrayList<>();
            //赋值 取list BriefHistory的 valueList 给 date，lastTimeList 给 data_time，
            for(BriefHistoryDTO historyDTO : historyDTOS) {
                datas.add(Float.parseFloat(historyDTO.getValue()));
                String dataTimeString = historyDTO.getLastTime().format(formatter);
                dataTime.add(dataTimeString);
            }
            itemVO.setData(datas.toArray(new Float[0]));
            itemVO.setDataTime(dataTime.toArray(new String[0]));
        }
        return itemVOS;
    }

}
