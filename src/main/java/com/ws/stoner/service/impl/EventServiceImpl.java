package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.ZApiParameter;
import com.ws.bix4j.access.event.EventGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.*;
import com.ws.stoner.model.view.ProblemAcknowledgeVO;
import com.ws.stoner.model.view.ProblemDetailListVO;
import com.ws.stoner.model.view.ProblemListVO;
import com.ws.stoner.service.AlertService;
import com.ws.stoner.service.EventService;
import com.ws.stoner.service.TriggerService;
import com.ws.stoner.utils.AlertStatusConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.ws.bix4j.exception.ZApiExceptionEnum.NO_AUTH_ASSIGN;
import static com.ws.bix4j.exception.ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE;

/**
 * Created by zkf on 2017/8/22.
 */
@Service
public class EventServiceImpl implements EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);
    @Autowired
    private ZApi zApi;

    @Autowired
    private TriggerService triggerService;

    @Autowired
    private AlertService alertService;

    @Override
    public List<BriefEventDTO> listEvent(EventGetRequest request) throws ServiceException {
        List<BriefEventDTO> events;
        try {
            events = zApi.Event().get(request,BriefEventDTO.class);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询事件event错误！{}", e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return events;
    }

    /**
     * 根据 eventIds 查询所有指定的事件 BriefEventDTO
     * @param eventIds
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefEventDTO> getEventByEventId(List<String> eventIds) throws ServiceException {
        EventGetRequest eventGetRequest = new EventGetRequest();
        eventGetRequest.getParams()
                .setEventIds(eventIds)
                .setSelectAcknowledges(BriefAcknowledgeDTO.PROPERTY_NAMES)
                .setSelectHosts(BriefHostDTO.PROPERTY_NAMES)
                .setSelectRelatedObject(BriefTriggerDTO.PROPERTY_NAMES)
                .setOutput(BriefEventDTO.PROPERTY_NAMES);
        return listEvent(eventGetRequest);
    }

    /**
     * 获取指定时间区间内 的 所有事件  all eventDTOS
     * @param beginTime  秒数 字符串
     * @param endTime  秒数 字符串
     * @param triggerIds  用来过滤依赖关系的 event
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefEventDTO> getAllEventsByTime(String beginTime, String endTime,List<String> triggerIds) throws ServiceException {
        EventGetRequest eventGetRequest = new EventGetRequest();
        List<String> sortOrder = new ArrayList<>();
        List<String> sortFilter = new ArrayList<>();
        sortFilter.add("clock");
        sortOrder.add("DESC");
        eventGetRequest.getParams()
                .setTimeFrom(beginTime)
                .setTimeTill(endTime)
                .setObjectIds(triggerIds)
                .setSelectHosts(BriefHostDTO.PROPERTY_NAMES)
                .setSelectRelatedObject(BriefTriggerDTO.PROPERTY_NAMES)
                .setSelectAlerts(BriefAlertDTO.PROPERTY_NAMES)
                .setOutput(BriefEventDTO.PROPERTY_NAMES)
                .setSortField(sortFilter)
                .setSortOrder(sortOrder);
        return listEvent(eventGetRequest);
    }

    /**
     * 获取指定时间区间内 的 恢复事件  recovery eventDTOS
     * @param beginTime  秒数 字符串
     * @param endTime  秒数 字符串
     * @param triggerIds  用来过滤依赖关系的 event
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefEventDTO> getRecoveryEventsByTime(String beginTime, String endTime, List<String> triggerIds) throws ServiceException {
        EventGetRequest eventGetRequest = new EventGetRequest();
        List<Integer> values = new ArrayList<>();
        values.add(ZApiParameter.EVENT_VALUE.OK.value);
        eventGetRequest.getParams()
                .setTimeFrom(beginTime)
                .setTimeTill(endTime)
                .setValue(values)
                .setObjectIds(triggerIds)
                .setSelectHosts(BriefHostDTO.PROPERTY_NAMES)
                .setSelectRelatedObject(BriefTriggerDTO.PROPERTY_NAMES)
                .setSelectAlerts(BriefAlertDTO.PROPERTY_NAMES)
                .setOutput(BriefEventDTO.PROPERTY_NAMES);
        return listEvent(eventGetRequest);

    }

    /**
     * 获取指定时间区间内 的 问题事件 problem eventDTOS
     * @param beginTime  秒数 字符串
     * @param endTime  秒数 字符串
     * @param triggerIds  用来过滤依赖关系的 event
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefEventDTO> getProblemEventsByTime(String beginTime, String endTime,List<String> triggerIds) throws ServiceException {
        EventGetRequest eventGetRequest = new EventGetRequest();
        List<Integer> values = new ArrayList<>();
        values.add(ZApiParameter.EVENT_VALUE.PROBLEM.value);
        eventGetRequest.getParams()
                .setTimeFrom(beginTime)
                .setTimeTill(endTime)
                .setValue(values)
                .setObjectIds(triggerIds)
                .setSelectHosts(BriefHostDTO.PROPERTY_NAMES)
                .setSelectRelatedObject(BriefTriggerDTO.PROPERTY_NAMES)
                .setSelectAlerts(BriefAlertDTO.PROPERTY_NAMES)
                .setOutput(BriefEventDTO.PROPERTY_NAMES);
        return listEvent(eventGetRequest);

    }

    /**
     * 根据时间区间 获取 历史记录  问题列表 List<ProblemListVO>
     * @param beginTime 时间格式：秒数
     * @param endTime  时间格式：秒数
     * @return
     * @throws ServiceException
     */
    @Override
    public List<ProblemListVO> getHistoryProblemsByTime(String beginTime, String endTime) throws ServiceException {
        //处理依赖关系
        List<BriefTriggerDTO> triggerDTOS = triggerService.listTriggersSkipDependent();
        List<String > triggerIds = new ArrayList<>();
        for(BriefTriggerDTO triggerDTO : triggerDTOS) {
            triggerIds.add(triggerDTO.getTriggerId());
        }
        List<BriefEventDTO> problemEventDTOS = getProblemEventsByTime(beginTime,endTime,triggerIds);
        List<BriefEventDTO> recoveryEventDTOS = getRecoveryEventsByTime(beginTime, String.valueOf(System.currentTimeMillis() / 1000),triggerIds);
        //用于获取总的告警信息
        List<String> allEventIds = new ArrayList<>();
        for(BriefEventDTO eventDTO : problemEventDTOS) {
            allEventIds.add(eventDTO.getEventId());
        }
        for(BriefEventDTO eventDTO : recoveryEventDTOS) {
            allEventIds.add(eventDTO.getEventId());
        }
        List<BriefAlertDTO> allAlertDTOS = alertService.getAlertDTOByEventIds(allEventIds);
        //将 BriefProblemDTO 转换成 ProblemListVO
        List<ProblemListVO> problemListVOS = ProblemListVO.transformVOSUseBriefEventDTO(problemEventDTOS,recoveryEventDTOS,allAlertDTOS);
        //时间排序
        return ProblemListVO.getSortListByProblemTime(problemListVOS);
    }

    /**
     * 根据指定的 eventid 获取 问题列表中 确认记录 pop
     * @param eventId
     * @return
     * @throws ServiceException
     */
    @Override
    public List<ProblemAcknowledgeVO> getAcknowledgeVOSByEventId(String eventId) throws ServiceException {
        List<String> eventIds = new ArrayList<>();
        eventIds.add(eventId);
        List<BriefEventDTO> eventDTOS = getEventByEventId(eventIds);
        List<ProblemAcknowledgeVO> acknowledgeVOS = new ArrayList<>();
        if(eventDTOS.size() != 0) {
            List<BriefAcknowledgeDTO> acknowledgeDTOS = eventDTOS.get(0).getAcknowledges();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
            for(BriefAcknowledgeDTO acknowledgeDTO : acknowledgeDTOS) {
                ProblemAcknowledgeVO acknowledgeVO = new ProblemAcknowledgeVO(
                        acknowledgeDTO.getAcknowledgeId(),
                        acknowledgeDTO.getClock().format(formatter),
                        acknowledgeDTO.getAlias(),
                        acknowledgeDTO.getMessage(),
                        "0".equals(acknowledgeDTO.getAction()) ? "否" : "是"
                );
                acknowledgeVOS.add(acknowledgeVO);

            }
        }
        return acknowledgeVOS;
    }

    /**
     * 根据指定的 triggerId 获取 问题详情中 详情事件列表
     * @param triggerId
     * @return
     * @throws ServiceException
     */
    @Override
    public List<ProblemDetailListVO> getDetailListVOSByTriggerId(String triggerId) throws ServiceException {
        List<ProblemDetailListVO> problemDetailListVOS = new ArrayList<>();
        List<String> triggerIds = new ArrayList<>();
        triggerIds.add(triggerId);
        String beginTime = "0";
        String endTime = String.valueOf(System.currentTimeMillis() / 1000);
        List<BriefEventDTO> eventDTOS = getAllEventsByTime(beginTime,endTime,triggerIds);
        if(eventDTOS.size() == 0) {
            return null;
        }
        //处理第一条 事件
        BriefEventDTO firstDTO = eventDTOS.get(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        ProblemDetailListVO firstVO = new ProblemDetailListVO();
        firstVO.setEventId(firstDTO.getEventId());
        firstVO.setBeginTime(firstDTO.getClock().format(formatter));
        firstVO.setDurationString(firstDTO.getClock(), LocalDateTime.now());
        if(firstDTO.getValue().equals(ZApiParameter.EVENT_VALUE.PROBLEM.value)) {
            //该触发器的最新状态为问题
            firstVO.setStatus("问题");
            //确认
            if(firstDTO.getAcknowledged().equals(ZApiParameter.ACKNOWLEDGE_ACTION.ACKNOWLEDGED.value)) {
                firstVO.setAcknowledged("是");
            }else {
                firstVO.setAcknowledged("否");
            }
            List<BriefAlertDTO> firstDTOAlerts = firstDTO.getAlerts();
            //问题和恢复的告警,告警数
            Map<String,Integer> alertMap = AlertStatusConverter.getMassageByAlertStatus(firstDTOAlerts);
            firstVO.setAlertNum(alertMap.entrySet().iterator().next().getValue());
            firstVO.setAlertState(alertMap.entrySet().iterator().next().getKey());
        }else {
            //该触发器的最新状态为正常
            firstVO.setStatus("正常");
            //确认
            firstVO.setAcknowledged("无");
            firstVO.setAlertNum(0);
            firstVO.setAlertState("无");
        }
        //添加第一个元素
        problemDetailListVOS.add(firstVO);
        //定义上一个 DetailTime 用于指定 结束时间和持续时间
        LocalDateTime beforeTimeDTO = null;
        LocalDateTime currentTimeDTO = firstDTO.getClock();
        //处理后面的events
        for(BriefEventDTO eventDTO : eventDTOS) {
            if(!eventDTO.equals(firstDTO)) {
                //保存前一个DTOTime 用于赋值持续时间和结束时间
                beforeTimeDTO = currentTimeDTO;
                currentTimeDTO = eventDTO.getClock();
                ProblemDetailListVO currentVO = new ProblemDetailListVO();
                //赋值
                currentVO.setEventId(eventDTO.getEventId());
                currentVO.setBeginTime(eventDTO.getClock().format(formatter));
                currentVO.setEndTime(beforeTimeDTO.format(formatter));
                currentVO.setDurationString(eventDTO.getClock(),beforeTimeDTO);
                if(eventDTO.getValue().equals(ZApiParameter.EVENT_VALUE.PROBLEM.value)) {
                    //recoveryEventid
                    currentVO.setRecoveryEventid(eventDTO.getrEventid());
                    // eventDTO 状态为问题
                    currentVO.setStatus("问题(已恢复)");
                    //确认
                    if(eventDTO.getAcknowledged().equals(ZApiParameter.ACKNOWLEDGE_ACTION.ACKNOWLEDGED.value)) {
                        currentVO.setAcknowledged("是");
                    }else {
                        currentVO.setAcknowledged("否");
                    }
                    List<BriefAlertDTO> alertDTOS = eventDTO.getAlerts();
                    //问题和恢复的告警,告警数
                    Map<String,Integer> alertMap = AlertStatusConverter.getMassageByAlertStatus(alertDTOS);
                    currentVO.setAlertNum(alertMap.entrySet().iterator().next().getValue());
                    currentVO.setAlertState(alertMap.entrySet().iterator().next().getKey());
                }else {
                    // eventDTO 状态为问题
                    currentVO.setStatus("正常");
                    //确认
                    currentVO.setAcknowledged("无");
                    currentVO.setAlertNum(0);
                    currentVO.setAlertState("无");
                }
                problemDetailListVOS.add(currentVO);
            }
        }
        return problemDetailListVOS;
    }


}
