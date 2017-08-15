package com.ws.stoner.controller.rest;

import com.ws.stoner.exception.ServiceException;
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
        List<PlatformGraphVO> platformGraphVOS = graphService.getPlatformGraphByhostIds(hostIds);
        return RestResultGenerator.genResult(platformGraphVOS, REST_UPDATE_SUCCESS).toString();
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


}
