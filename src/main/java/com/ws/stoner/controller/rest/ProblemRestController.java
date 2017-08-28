package com.ws.stoner.controller.rest;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.view.ProblemAcknowledgeVO;
import com.ws.stoner.model.view.ProblemAlertVO;
import com.ws.stoner.model.view.ProblemDetailListVO;
import com.ws.stoner.model.view.ProblemListVO;
import com.ws.stoner.service.AlertService;
import com.ws.stoner.service.EventService;
import com.ws.stoner.service.ProblemService;
import com.ws.stoner.service.TriggerService;
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
    @Autowired
    private TriggerService triggerService;
    @Autowired
    private ProblemService problemService;

    /**
     * 问题列表模块  当前问题 请求
     * @return
     */
    @RequestMapping(value = "problemlist/get_allproblems", method = RequestMethod.GET)
    public String getAllProblems() throws ServiceException {
        List<ProblemListVO> problemListVOS = triggerService.listProblemListVO();
        return RestResultGenerator.genResult(problemListVOS, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 问题列表模块 最近问题 30分钟内恢复的问题和所有没有恢复的问题
     * @return
     */
    @RequestMapping(value = "problemlist/get_lastproblems", method = RequestMethod.GET)
    public String getLastProblems() throws ServiceException {
        List<ProblemListVO> problemListVOS = problemService.getLastProblems();
        return RestResultGenerator.genResult(problemListVOS, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 问题列表模块  历史记录 请求 指定时间段 1503640848  1503642952
     * @return
     */
    @RequestMapping(value = "problemlist/get_historyproblems", method = RequestMethod.GET)
    public String getHistoryProblems(@RequestParam("begin_time") String beginTime,@RequestParam("end_time") String endTime) throws ServiceException {
        //时间格式：秒数
        List<ProblemListVO> problemListVOS = eventService.getHistoryProblemsByTime(beginTime,endTime);
        return RestResultGenerator.genResult(problemListVOS, REST_UPDATE_SUCCESS).toString();
    }


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

    /**
     * 问题详情  详情列表
     * @return
     */
    @RequestMapping(value = "problemdetail/get_list", method = RequestMethod.GET)
    public String getDetailListByTriggerId(@RequestParam("trigger_id") String triggerId) throws ServiceException {
        List<ProblemDetailListVO> problemDetailListVOS = eventService.getDetailListVOSByTriggerId(triggerId);
        return RestResultGenerator.genResult(problemDetailListVOS, REST_UPDATE_SUCCESS).toString();
    }
}
