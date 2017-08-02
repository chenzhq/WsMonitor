package com.ws.stoner.controller.rest;

import com.ws.stoner.constant.GraphTypeEnum;
import com.ws.stoner.constant.ResponseErrorEnum;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.DO.mongo.Item;
import com.ws.stoner.model.dto.BriefHostDTO;
import com.ws.stoner.model.dto.BriefItemDTO;
import com.ws.stoner.model.view.*;
import com.ws.stoner.service.*;
import com.ws.stoner.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private HostService hostService;

    @Autowired
    private PointSerivce pointSerivce;

    @Autowired
    private GraphService graphService;


    /**
     * 监控点悬浮框
     * @return
     */
    @RequestMapping(value = "brief/point", method = RequestMethod.GET)
    public String listPointItemsFlow(@RequestParam("point_id") String pointId) throws ServiceException {
        HostDetailPointVO pointVO = pointSerivce.getItemsByPointId(pointId);
        return RestResultGenerator.genResult(pointVO, REST_RESPONSE_SUCCESS).toString();
    }

    /**
     * 获取设备所有监控项的图形
     * @return
     */
    @RequestMapping(value = "hostgraphs", method = RequestMethod.GET)
    public String listHostItemsGraph(@RequestParam("host_id") String hostId) throws ServiceException {
        List<HostDetailItemVO> itemVOS = graphService.getGraphItemByHostId(hostId);
        return RestResultGenerator.genResult(itemVOS, REST_RESPONSE_SUCCESS).toString();
    }

    /**
     * 获取指定主机的 point 下拉框列表
     * @param hostId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "hostgraphs/get_points", method = RequestMethod.GET)
    public String getPointsByHostId(@RequestParam("host_id") String hostId) throws ServiceException {
        //step1:根据 hostId 取对应 BreifHostDTO，单个 hostDTO
        List<String> hostIds = new ArrayList<>();
        hostIds.add(hostId);
        BriefHostDTO hostDTO = hostService.getHostsByHostIds(hostIds).get(0);
        //新建DetailHostVO对象,ItemVO对象 List,PointVO对象list,InterfaceVO对象
        List<HostDetailPointVO> pointVOS = hostService.getPointsByHostDTO(hostDTO);
        for(HostDetailPointVO pointVO : pointVOS) {
            pointVO.setState(null);
        }
        return RestResultGenerator.genResult(pointVOS, REST_RESPONSE_SUCCESS).toString();
    }

    /**
     * 获取指定监控点的 item 下拉框列表
     * @param pointId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "hostgraphs/get_items", method = RequestMethod.GET)
    public String getItemsByPointId(@RequestParam("point_id") String pointId) throws ServiceException {
        List<String> pointIds = new ArrayList<>();
        pointIds.add(pointId);
        List<BriefItemDTO> itemDTOS = itemService.getItemsByPointIds(pointIds);
        List<HostDetailItemVO> itemVOS = new ArrayList<>();
        for(BriefItemDTO itemDTO : itemDTOS) {
            HostDetailItemVO itemVO = new HostDetailItemVO();
            itemVO.setItemId(itemDTO.getItemId());
            itemVO.setItemName(itemDTO.getName());
            // 如何单位是 % ，采用value_type = % 的图形列表
            if("%".equals(itemDTO.getUnits())) {
                itemVO.setValueType(itemDTO.getUnits());
            }else {
                itemVO.setValueType(itemDTO.getValueType());
            }
            itemVOS.add(itemVO);
        }
        return RestResultGenerator.genResult(itemVOS, REST_RESPONSE_SUCCESS).toString();
    }

    /**
     * 获取指定 value_type 的 图形 graph 下拉框列表
     * @param valueType
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "hostgraphs/get_graphs", method = RequestMethod.GET)
    public String getGraphsByValueType(@RequestParam("value_type") String valueType) throws ServiceException {

        List<String> graphTypes = graphService.getGraphTypeByValueTypeFromMongo(valueType);
        List<HostDetailGraphVO> graphVOS = new ArrayList<>();
        for(String graphType : graphTypes) {
            HostDetailGraphVO graphVO = new HostDetailGraphVO();
            graphVO.setGraphType(graphType);
            switch (graphType) {
                case "line":
                    graphVO.setGraphName(GraphTypeEnum.LINE.name);
                    break;
                case "bar":
                    graphVO.setGraphName(GraphTypeEnum.BAR.name);
                    break;
                case "area":
                    graphVO.setGraphName(GraphTypeEnum.AREA.name);
                    break;
                case "gauge":
                    graphVO.setGraphName(GraphTypeEnum.GAUGE.name);
                    break;
            }
           graphVOS.add(graphVO);
        }
        return RestResultGenerator.genResult(graphVOS, REST_RESPONSE_SUCCESS).toString();
    }

    /**
     * 保存用户自定义图形配置
     * @return
     */
    @RequestMapping(value = "hostgraphs/save_graph", method = RequestMethod.POST)
    public String saveHostItemsGraph( @RequestBody Item graphItem) throws ServiceException {
        boolean success =  itemService.saveGraphItemFromMongo(graphItem);
        if(success) {
            List<HostDetailItemVO> itemVOS = graphService.getGraphItemByHostId(graphItem.getHostId());
            return RestResultGenerator.genResult(itemVOS, REST_RESPONSE_SUCCESS).toString();
        }else {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
    }
}
