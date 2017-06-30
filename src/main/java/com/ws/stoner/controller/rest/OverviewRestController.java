package com.ws.stoner.controller.rest;

import com.ws.stoner.constant.ResponseErrorEnum;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.view.OverviewVO;
import com.ws.stoner.service.OverviewService;
import com.ws.stoner.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ws.stoner.constant.MessageConsts.REST_MONGOLIST_SUCCESS;


/**
 * Created by zkf on 2017/6/30.
 */
@RestController
public class OverviewRestController {

    @Autowired
    private OverviewService overviewService;

    @RequestMapping(value = "host/overview", method = RequestMethod.GET)
    public String listMongoGroup() {
        List<OverviewVO> overviewVo;
        try {
            overviewVo = overviewService.listOverviewGroup();
        } catch (ServiceException e) {
            return RestResultGenerator.genErrorResult(ResponseErrorEnum.SERVICE_HANDLE_ERROR).toString();
        }
        return RestResultGenerator.genResult(overviewVo, REST_MONGOLIST_SUCCESS).toString();
    }
}
