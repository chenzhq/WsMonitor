package com.ws.stoner.controller.rest;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.view.PlatDetailHostVO;
import com.ws.stoner.model.view.PlatDetailItemVO;
import com.ws.stoner.model.view.PlatDetailPointVO;
import com.ws.stoner.model.view.PlatformBlockVO;
import com.ws.stoner.service.PlatformService;
import com.ws.stoner.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ws.stoner.constant.MessageConsts.*;

/**
 * Created by zkf on 2017/8/8.
 */
@RestController
public class PlatformRestController {

    @Autowired
    private PlatformService platformService;

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

}
