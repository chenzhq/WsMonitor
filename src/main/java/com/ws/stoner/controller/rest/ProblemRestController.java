package com.ws.stoner.controller.rest;

import com.ws.stoner.constant.CookieConsts;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefAcknowledgeDTO;
import com.ws.stoner.model.dto.UserInfoDTO;
import com.ws.stoner.model.query.CalendarFormQuery;
import com.ws.stoner.model.view.*;
import com.ws.stoner.service.*;
import com.ws.stoner.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.ws.stoner.constant.MessageConsts.REST_UPDATE_SUCCESS;

/**
 * Created by zkf on 2017/8/23.
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

    @Autowired
    private GraphService graphService;

    /**
     * 问题列表模块  当前问题 请求
     * @return
     */
    @RequestMapping(value = "/problems/get_current", method = RequestMethod.GET)
    public String getAllProblems() throws ServiceException {
        List<ProblemListVO> problemListVOS = triggerService.listProblemListVO();
        return RestResultGenerator.genResult(problemListVOS, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 问题列表模块 最近问题 30分钟内恢复的问题和所有没有恢复的问题
     * @return
     */
    @RequestMapping(value = "problems/get_last", method = RequestMethod.GET)
    public String getLastProblems() throws ServiceException {
        List<ProblemListVO> problemListVOS = problemService.getLastProblems();
        return RestResultGenerator.genResult(problemListVOS, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 问题列表模块  历史记录 请求 指定时间段 1503640848  1503642952
     * @return
     */
    @RequestMapping(value = "problems/get_history", method = RequestMethod.GET)
    public String getHistoryProblems(@RequestParam("begin_time") String beginTime,@RequestParam("end_time") String endTime) throws ServiceException {
        //时间格式：秒数
        List<ProblemListVO> problemListVOS = eventService.getHistoryProblemsByTime(beginTime,endTime);
        return RestResultGenerator.genResult(problemListVOS, REST_UPDATE_SUCCESS).toString();
    }


    /**
     * 问题列表  确认记录 pop
     * @return
     */
    @RequestMapping(value = "acknowledges/get_acknowledge", method = RequestMethod.GET)
    public String getAcknowledgesByEventId(@RequestParam("event_id") String eventId) throws ServiceException {
        List<ProblemAcknowledgeVO> acknowledgeVOS = eventService.getAcknowledgeVOSByEventId(eventId);
        return RestResultGenerator.genResult(acknowledgeVOS, REST_UPDATE_SUCCESS).toString();
    }

    /**
     *  确认操作 判断是否可关闭问题
     * @return
     */
    @RequestMapping(value = "acknowledge/is_closed", method = RequestMethod.GET)
    public String getAcknowledgeCheckBoxByEventId(@RequestParam("event_id") String eventId,HttpSession session) throws ServiceException {
        UserInfoDTO userInfoDTO = (UserInfoDTO) session.getAttribute(CookieConsts.USER_INFO);
        AcknowledgeCheckboxVO checkboxVO = eventService.getCheckboxVOByEventId(eventId,userInfoDTO.getUserId());
        checkboxVO.setAcknowledgeHistory(eventService.getAcknowledgeVOSByEventId(eventId));
        return RestResultGenerator.genResult(checkboxVO, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 执行确认动作
     * @return
     */
    @RequestMapping(value = "acknowledge/acknowledge_event", method = RequestMethod.POST)
    public String getAcknowledgesByEventId(@RequestBody BriefAcknowledgeDTO acknowledgeDTO) throws ServiceException {
        AcknowledgeVO acknowledgeVO = eventService.acknowledgeEvent(acknowledgeDTO);
        return RestResultGenerator.genResult(acknowledgeVO, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 问题列表  告警记录 pop
     * @return
     */
    @RequestMapping(value = "alerts/get_alert", method = RequestMethod.GET)
    public String getAlertsByEventId(@RequestParam("event_id") String eventId) throws ServiceException {
        List<ProblemAlertVO> alertVOS = alertService.getAlertVOByEventId(eventId);
        return RestResultGenerator.genResult(alertVOS, REST_UPDATE_SUCCESS).toString();
    }

       /**
     * 问题详情  详情列表
     * @return
     */
    @RequestMapping(value = "problem/detail_list", method = RequestMethod.GET)
    public String getDetailListByTriggerId(@RequestParam("trigger_id") String triggerId,@RequestParam("begin_time") String beginTime,@RequestParam("end_time") String endTime) throws ServiceException {
        List<ProblemDetailListVO> problemDetailListVOS = eventService.getDetailListByTriggerId(triggerId,beginTime,endTime);
        return RestResultGenerator.genResult(problemDetailListVOS, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 问题详情  时序图形 1504594265 1501915865 15170
     * @return
     */
    @RequestMapping(value = "problem/detail_graph", method = RequestMethod.GET)
    public String getDetailGraphByTriggerId(@RequestParam("trigger_id") String triggerId,@RequestParam("begin_time") String beginTime,@RequestParam("end_time") String endTime) throws ServiceException {
        List<ProblemGraphVO> problemGraphVOS = eventService.getGraphProblemByTriggerId(triggerId, beginTime, endTime);
        List<List<Object>> graphDatas = new ArrayList<>();
        for(ProblemGraphVO graphVO : problemGraphVOS) {
            List<Object> graphData = new ArrayList<>();
            graphData.add(graphVO.getBeginTime());
            graphData.add(graphVO.getEndTime());
            graphData.add(graphVO.getIsAlert());
            graphData.add(graphVO.getColor() == null ? "" : graphVO.getColor());
            graphData.add(graphVO.getTooltip());
            graphDatas.add(graphData);
        }
        return RestResultGenerator.genResult(graphDatas, REST_UPDATE_SUCCESS).toString();
    }


    /**
     * 事件详情  事件细节信息
     * @return
     */
    @RequestMapping(value = "event/get_detail", method = RequestMethod.GET)
    public String getEventDetailByEventId(@RequestParam("event_id") String eventId) throws ServiceException {
        EventDetailVO eventDetailVO = eventService.getEventDetailByEventId(eventId);
        return RestResultGenerator.genResult(eventDetailVO, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 事件详情  告警详情信息
     * @return
     */
    @RequestMapping(value = "alerts/get_detail", method = RequestMethod.GET)
    public String getAlertDetailByEventId(@RequestParam("event_id") String eventId) throws ServiceException {
        List<ProblemAlertVO> problemAlertVOS = alertService.getDetailAlertByEventId(eventId);
        return RestResultGenerator.genResult(problemAlertVOS, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 根据查询参数 获取告警日历数据
     * @return
     */
    @RequestMapping(value = "calendar/get_graph", method = RequestMethod.GET)
    public String getCalendarData(@RequestParam(required = false, value = "hostIds[]") List<String> hostIds,
                                  @RequestParam(required = false, value = "problemNum[]") List<Integer> problemNum,
                                  @RequestParam String priority,
                                  @RequestParam String acknowledge) throws ServiceException {
        CalendarFormQuery formQuery = new CalendarFormQuery();
        formQuery.setHostIds(hostIds).setProblemNum(problemNum).setPriority(priority).setAcknowledge(acknowledge);
        CalendarVO calendarVO = graphService.getCalendarGraphDatas(formQuery);
        return RestResultGenerator.genResult(calendarVO, REST_UPDATE_SUCCESS).toString();
    }

    /**
     * 根据查询参数 获取一天的日历信息 ： 一天的问题历史记录  List<ProblemListVO>
     * @return
     */
    @RequestMapping(value = "calendar/get_list", method = RequestMethod.GET)
    public String getOneDayListCalendar(@RequestParam(required = false, value = "hostIds[]") List<String> hostIds,
                                        @RequestParam(required = false, value = "problemNum[]") List<Integer> problemNum,
                                        @RequestParam String priority,
                                        @RequestParam String acknowledge,
                                        @RequestParam(required = false) String date) throws ServiceException {
        CalendarFormQuery formQuery = new CalendarFormQuery();
        formQuery.setHostIds(hostIds).setProblemNum(problemNum).setPriority(priority).setAcknowledge(acknowledge).setDate(date);
        List<ProblemListVO> problemListVOS = eventService.getOneDayProblemListVOS(formQuery);
        return RestResultGenerator.genResult(problemListVOS, REST_UPDATE_SUCCESS).toString();
    }
}
