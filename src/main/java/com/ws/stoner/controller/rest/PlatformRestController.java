package com.ws.stoner.controller.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ws.stoner.constant.ResponseErrorEnum;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.DO.mongo.Item;
import com.ws.stoner.model.DO.mongo.PlatformGraph;
import com.ws.stoner.model.view.*;
import com.ws.stoner.service.GraphService;
import com.ws.stoner.service.PlatformService;
import com.ws.stoner.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static com.ws.stoner.constant.MessageConsts.*;

/**
 * Created by zkf on 2017/8/8.
 */
@RestController
public class PlatformRestController {

    @Autowired
    private PlatformService platformService;

    @Autowired
    private GraphService graphService;

    /**
     * 业务方块  block 展示
     * @return
     */
    @RequestMapping(value = "platformview/get_platforms", method = RequestMethod.GET)
    public String listPlatformView() throws ServiceException {
        List<PlatformBlockVO> platformBlockVOS = platformService.getPlatformBlock();
        return RestResultGenerator.genResult(platformBlockVOS, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 业务分析  概述 标签页 数值数据
     * @return
     */
    @RequestMapping(value = "platformdetail/get_datas", method = RequestMethod.GET)
    public String getBasePlatformDetail(@RequestParam("platform_id") String platformId) throws ServiceException {
        List<PlatformBlockVO> platformBlockVOS = platformService.getPlatformBlock();
        PlatformBlockVO platformVO = null;
        for(PlatformBlockVO platformBlockVO : platformBlockVOS) {
            if(platformBlockVO.getPlatformId().equals(platformId)) {
                platformVO = platformBlockVO;
            }
        }
        return RestResultGenerator.genResult(platformVO, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 针对指定的业务平台 初始化 设备清单
     * @param platformId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "platformdetail/get_hosts",method = RequestMethod.GET)
    public String getPlatformHosts(@RequestParam("platform_id") String platformId) throws ServiceException {
        List<PlatDetailHostVO> hostVOS = platformService.getHostsByPlatformId(platformId);
        return RestResultGenerator.genResult(hostVOS, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 选择指定 设备 加载监控点
     * @param hostId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "platformdetail/get_points",method = RequestMethod.GET)
    public String getPlatformPoints(@RequestParam("host_id") String hostId) throws ServiceException {
        List<PlatDetailPointVO> pointVOS = platformService.getPointsByHostId(hostId);
        return RestResultGenerator.genResult(pointVOS, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 选择指定 监控点 加载监控项
     * @param pointId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "platformdetail/get_items",method = RequestMethod.GET)
    public String getPlatformItems(@RequestParam("point_id") String pointId) throws ServiceException {
        List<PlatDetailItemVO> itemVOS = platformService.getItemsByPointId(pointId);
        return RestResultGenerator.genResult(itemVOS, REST_UPDATE_SUCCESS).toString();
    }


    /**
     * 获取指定 hostIds 的 业务平台监控项图形 数据
     * @param hostIdsArr
     * @return
     * @throws ServiceException
     */
    @ResponseBody
    @RequestMapping(value = "platformdetail/get_graphs",method = RequestMethod.POST)
    public String getPlatformGraphs(@RequestParam(value = "host_ids") String[] hostIdsArr) throws ServiceException {
        List<String> hostIds = Arrays.asList(hostIdsArr);
        List<PlatformGraphVO> platformGraphVOS = graphService.getPlatformGraphsByhostIds(hostIds);
        return RestResultGenerator.genResult(platformGraphVOS, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 保存 业务平台监控项图形 数据
     * @param platformGraph
     * @return
     * @throws ServiceException
     */
    @ResponseBody
    @RequestMapping(value = "platformdetail/save_graph",method = RequestMethod.POST)
    public String savePlatformGraph(@RequestBody PlatformGraph platformGraph ) throws ServiceException {
        boolean success =  graphService.savePlatformGraph(platformGraph);
        if(success) {
//            List<PlatformGraphVO> platformGraphVOS = graphService.getPlatformGraphsByPlatformId(platformGraph.getPlatformId());
            return RestResultGenerator.genResult(success, REST_RESPONSE_SUCCESS).toString();
        }else {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
    }

    /**
     * 获取 修改原始数据 配置
     * @return
     */
    @RequestMapping(value = "platformdetail/get_update", method = RequestMethod.GET)
    public String getUpdatePlatformGraph( @RequestParam("item_id") String itemId) throws ServiceException {
        HostDetailItemGraphVO itemGraphVO = graphService.getUpdatePlatformGraph(itemId);
        return RestResultGenerator.genResult(itemGraphVO, REST_RESPONSE_SUCCESS).toString();
    }

    /**
     * 修改 业务图形报告 数据
     * @param platformGraph
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "platformdetail/update_graph",method = RequestMethod.POST)
    public String updatePlatformGraph(@RequestBody PlatformGraph platformGraph ) throws ServiceException {
        boolean success =  graphService.updatePlatformGraph(platformGraph);
        if(success) {
            return RestResultGenerator.genResult(success, REST_RESPONSE_SUCCESS).toString();
        }else {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
    }

    /**
     * 删除 业务图形报告 数据
     * @param itemId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "platformdetail/del_graph",method = RequestMethod.GET)
    public String deletePlatformGraph( @RequestParam("item_id") String itemId) throws ServiceException {
        boolean success =  graphService.deletePlatformGraph(itemId);
        if(success) {
            return RestResultGenerator.genResult(success, REST_RESPONSE_SUCCESS).toString();
        }else {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
    }

    /**
     * 获取业务平台下拉框
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "platformdetail/get_platforms",method = RequestMethod.GET)
    public String getPlatformsSelect() throws ServiceException {
        List<PlatDetailPlatformVO> platDetailPlatformVOS = platformService.getPlatDetailSelect();
        return RestResultGenerator.genResult(platDetailPlatformVOS, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 获取指定 platformId 的 业务树 数据和结构
     * @param platformId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "platformtree/get_tree",method = RequestMethod.GET)
    public String getPlatformTrees(@RequestParam("platform_id") String platformId) throws ServiceException {
        PlatformTreeVO platformTreeVO = graphService.getPlatTreeByPlatformId(platformId);
        return RestResultGenerator.genResult(platformTreeVO, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 获取 更新树
     * @param platformId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "platformtree/get_update",method = RequestMethod.GET)
    public String getUpdateTrees(@RequestParam("platform_id") String platformId) throws ServiceException {
        PlatformTreeUpdateVO updateVO = graphService.getUpdateTreeByPlatformId(platformId);
        return RestResultGenerator.genResult(updateVO, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 更新 业务树
     * @param dataTree
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "platformtree/update_tree",method = RequestMethod.POST)
    public String updatePlatformTrees(@RequestParam("data") String dataTree) throws ServiceException {
        PlatformTreeUpdateVO updateVO = JSON.parseObject(dataTree,PlatformTreeUpdateVO.class);
        boolean success = graphService.updatePlatformTree(updateVO);
        return RestResultGenerator.genResult(success, REST_UPDATE_SUCCESS).toString();
    }

}
