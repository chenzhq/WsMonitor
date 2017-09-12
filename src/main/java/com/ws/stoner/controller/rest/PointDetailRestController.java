package com.ws.stoner.controller.rest;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.view.host.HostDetailItemVO;
import com.ws.stoner.model.view.host.HostDetailPointItemVO;
import com.ws.stoner.model.view.host.HostDetailPointVO;
import com.ws.stoner.service.GraphService;
import com.ws.stoner.service.ItemService;
import com.ws.stoner.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import static com.ws.stoner.constant.MessageConsts.REST_RESPONSE_SUCCESS;

/**
 * Created by zkf on 2017/7/24.
 */
@RestController

public class PointDetailRestController {

    @Autowired
    private GraphService graphService;

    @Autowired
    private ItemService itemService;

    /**
     * 监控点 概述 标签页
     * @return
     */
    @RequestMapping(value = "/pointdetail", method = RequestMethod.GET)
    public String listPointDetail(@RequestParam("point_id") String pointId) throws ServiceException {
        HostDetailPointVO pointVO = itemService.getDetailPointByPointId(pointId);
        return RestResultGenerator.genResult(pointVO, REST_RESPONSE_SUCCESS).toString();
    }

    /**
     * 监控点 图形报告 标签页
     * @return
     */
    @RequestMapping(value = "/pointdetail/get_graphs", method = RequestMethod.GET)
    public String getPointGraphs(@RequestParam("point_id") String pointId,@RequestParam("time") int time) throws ServiceException {
        List<HostDetailItemVO> itemVOS = graphService.getGraphItemByPointId(pointId,time);
        return RestResultGenerator.genResult(itemVOS, REST_RESPONSE_SUCCESS).toString();
    }

    /**
     * 监控点详情 时序数据 标签页
     * @return
     */
    @RequestMapping(value = "/pointdetail/get_datas", method = RequestMethod.GET)
    public String getPointDatas(@RequestParam("item_id") String itemId,@RequestParam("time") int time) throws ServiceException {
        List<HostDetailPointItemVO> itemDatas = itemService.getItemDatasByItemId(itemId,time);
        return RestResultGenerator.genResult(itemDatas, REST_RESPONSE_SUCCESS).toString();
    }

}
