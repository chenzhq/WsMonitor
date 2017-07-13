package com.ws.stoner.controller.rest;

import com.ws.stoner.constant.StatusEnum;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.DO.mongo.Item;
import com.ws.stoner.model.dto.BriefHistoryDTO;
import com.ws.stoner.model.dto.BriefItemDTO;
import com.ws.stoner.model.view.HostDetailItemVO;
import com.ws.stoner.model.view.HostDetailPointItemVO;
import com.ws.stoner.model.view.HostDetailPointVO;
import com.ws.stoner.service.HistoryService;
import com.ws.stoner.service.ItemService;
import com.ws.stoner.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.ws.stoner.constant.MessageConsts.REST_RESPONSE_SUCCESS;

/**
 * Created by zkf on 2017/7/13.
 */
@RestController
public class HostDetailRestController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private HistoryService historyService;

    /**
     * 监控点悬浮框
     * @return
     */
    @RequestMapping(value = "brief/point", method = RequestMethod.GET)
    public String listPointItemsFlow(@RequestParam("point") HostDetailPointVO point) throws ServiceException {
        List<String> pointIds = new ArrayList<>();
        pointIds.add(point.getHostId());
        List<BriefItemDTO> itemDTOS = itemService.getItemsByPointIds(pointIds);
        List<HostDetailPointItemVO> itemVOS = new ArrayList<>();
        for(BriefItemDTO itemDTO :itemDTOS) {
            HostDetailPointItemVO itemVO = new HostDetailPointItemVO();
            itemVO.setItemId(itemDTO.getItemId());
            itemVO.setName(itemDTO.getName());
            itemVO.setValue(itemDTO.getLastValue());
            //state
            if(StatusEnum.WARNING.code == itemDTO.getCustomState()) {
                itemVO.setState(StatusEnum.WARNING.getName());
            }else if(StatusEnum.HIGH.code == itemDTO.getCustomState()){
                itemVO.setState(StatusEnum.HIGH.getName());
            }else {
                itemVO.setState(StatusEnum.OK.getName());
            }
            itemVOS.add(itemVO);
        }
        point.setItems(itemVOS);

        return RestResultGenerator.genResult(point, REST_RESPONSE_SUCCESS).toString();
    }

    /**
     * 获取设备所有监控项的图形
     * @return
     */
    @RequestMapping(value = "hostgraphs", method = RequestMethod.POST)
    public String listHostItemsGraph(@RequestParam("host_id") String hostId) throws ServiceException {
        //取 BriefItemDTO list, 过滤出 value_type=0,3
        List<String> hostIds = new ArrayList<>();
        hostIds.add(hostId);
        List<BriefItemDTO> itemDTOS = itemService.getItemsByHostIds(hostIds);
        //取mongodb的所有hostid下的items
        List<Item> mongoItems = itemService.getItemsByHostIdFromMongo(hostId);
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
                    itemVO.setHostId(mongoItem.getHostId());
                    itemVO.setValueType(itemDTO.getValueType());
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
                itemVO.setHostId(hostId);
                itemVO.setItemId(itemDTO.getItemId());
                itemVO.setItemName(itemDTO.getName());
                itemVO.setFlag(false);
                itemVO.setGraphName(itemDTO.getName());
                itemVO.setGraphType("line");
                itemVO.setValueType(itemDTO.getValueType());
                //state
                if(StatusEnum.HIGH.code == itemDTO.getCustomState()) {
                    itemVO.setState(StatusEnum.HIGH.getName());
                }else if(StatusEnum.WARNING.code == itemDTO.getCustomState()) {
                    itemVO.setState(StatusEnum.WARNING.getName());
                }else {
                    itemVO.setState(StatusEnum.OK.getName());
                }
                itemVOS.add(itemVO);
            }
        }
        //根据value_type取对应的history.get,时间区间为前7天的数据 得到 BriefHistory list
        for(HostDetailItemVO itemVO : itemVOS) {
            List<BriefHistoryDTO> historyDTOS = historyService.getHistoryByItemId(itemVO.getItemId(),itemVO.getValueType());
            List<Float> datas = new ArrayList<>();
            List<LocalDateTime> dataTime = new ArrayList<>();
            //赋值 取list BriefHistory的 valueList 给 date，lastTimeList 给 data_time，
            for(BriefHistoryDTO historyDTO : historyDTOS) {
                datas.add(historyDTO.getValue());
                dataTime.add(historyDTO.getLastTime());
            }
            itemVO.setData(datas.toArray(new Float[0]));
            itemVO.setDataTime(dataTime.toArray(new LocalDateTime[0]));
        }

        return RestResultGenerator.genResult(itemVOS, REST_RESPONSE_SUCCESS).toString();
    }
}
