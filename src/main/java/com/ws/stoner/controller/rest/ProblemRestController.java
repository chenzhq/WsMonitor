package com.ws.stoner.controller.rest;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.view.PlatformBlockVO;
import com.ws.stoner.model.view.ProblemAcknowledgeVO;
import com.ws.stoner.model.view.ProblemAlertVO;
import com.ws.stoner.service.AlertService;
import com.ws.stoner.service.EventService;
import com.ws.stoner.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ws.stoner.constant.MessageConsts.REST_UPDATE_SUCCESS;

/**
 * Created by pc on 2017/8/23.
 */
@RestController
public class ProblemRestController {

    @Autowired
    private EventService eventService;
    @Autowired
    private AlertService alertService;

    /**
     * 问题列表  确认记录 pop
     * @return
     */
    @RequestMapping(value = "problemlist/get_acknowledges", method = RequestMethod.GET)
    public String getAcknowledgesByEventId(@RequestParam("event_id") String eventId) throws ServiceException {
        List<ProblemAcknowledgeVO> acknowledgeVOS = eventService.getAcknowledgeVOSByEventId(eventId);
        return RestResultGenerator.genResult(acknowledgeVOS, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 问题列表  告警记录 pop
     * @return
     */
    @RequestMapping(value = "problemlist/get_alerts", method = RequestMethod.GET)
    public String getAlertsByEventId(@RequestParam("event_id") String eventId) throws ServiceException {
        List<ProblemAlertVO> alertVOS = alertService.getAlertVOByEventId(eventId);
        return RestResultGenerator.genResult(alertVOS, REST_UPDATE_SUCCESS).toString();
    }
}
