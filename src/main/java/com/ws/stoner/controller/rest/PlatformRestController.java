package com.ws.stoner.controller.rest;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.OverviewListGroupDTO;
import com.ws.stoner.model.view.PlatformBlockVO;
import com.ws.stoner.service.PlatformService;
import com.ws.stoner.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @RequestMapping(value = "platform/view", method = RequestMethod.GET)
    public String listPlatformView() throws ServiceException {
        List<PlatformBlockVO> platformBlockVOS = platformService.getPlatformBlock();
        return RestResultGenerator.genResult(platformBlockVOS, REST_UPDATE_SUCCESS).toString();
    }
}
