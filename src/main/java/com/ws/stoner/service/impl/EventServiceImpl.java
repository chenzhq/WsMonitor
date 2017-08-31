package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.ZApiParameter;
import com.ws.bix4j.access.event.EventAcknowledgeRequest;
import com.ws.bix4j.access.event.EventGetRequest;
import com.ws.bix4j.access.trigger.TriggerGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.*;
import com.ws.stoner.model.query.CalendarFormQuery;
import com.ws.stoner.model.view.*;
import com.ws.stoner.service.*;
import com.ws.stoner.utils.AlertStatusConverter;
import com.ws.stoner.utils.StatusConverter;
import com.ws.stoner.utils.ThresholdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
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
    private PointSerivce pointSerivce;

    @Autowired
    private UserService userService;

    @Autowired
    private UsergroupService usergroupService;

    private List<BriefEventDTO> listEvent(EventGetRequest request) throws ServiceException {
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
                .setSelectAlerts(BriefAlertDTO.PROPERTY_NAMES)
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
//        List<BriefEventDTO> recoveryEventDTOS = getRecoveryEventsByTime(beginTime, String.valueOf(System.currentTimeMillis() / 1000),triggerIds);
        List<String> recoveryEventIds = new ArrayList<>();
        for(BriefEventDTO eventDTO : problemEventDTOS) {
            if(!"0".equals(eventDTO.getrEventid())) {
                recoveryEventIds.add(eventDTO.getrEventid());
            }
        }
        //获取 恢复事件集合
        List<BriefEventDTO> recoveryEventDTOS = getEventByEventId(recoveryEventIds);
        //将 BriefProblemDTO 转换成 ProblemListVO
        List<ProblemListVO> problemListVOS = ProblemListVO.transformVOSUseBriefEventDTO(problemEventDTOS,recoveryEventDTOS);
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
     * 根据 eventid 获取判断 事件的确认操作是否可以 关闭问题
     * @param eventId
     * @param userId  用于判断 Super Admin OR 所属用户群组 对 Trigger所属主机的主机群组 有读写权限（3）
     * @return
     * @throws ServiceException
     */
    @Override
    public AcknowledgeCheckboxVO getCheckboxVOByEventId(String eventId, String userId) throws ServiceException {
        List<String> eventIds = new ArrayList<>();
        eventIds.add(eventId);
        AcknowledgeCheckboxVO checkboxVO = new AcknowledgeCheckboxVO();
        String triggerId = getEventByEventId(eventIds).get(0).getObjectId();
        List<String> triggerIds = new ArrayList<>();
        triggerIds.add(triggerId);
        BriefTriggerDTO triggerDTO = triggerService.getTriggersByTriggerIds(triggerIds).get(0);
        String groupId = triggerDTO.getGroups().get(0).getPlatformId();
        //step1:Trigger是否为问题状态？是
        if(triggerDTO.getValue().equals(ZApiParameter.TRIGGER_VALUE.OK.value)) {
            //如果不是问题状态
            checkboxVO.setCheckboxEnable(false);
            checkboxVO.setDisableMessage("问题已关闭");
            return checkboxVO;
        }
        //step2:Trigger是否可关闭？ 是
        if(triggerDTO.getManualClose().equals(ZApiParameter.TRIGGER_MANUAL_CLOSE.NO.value)) {
            //如果不可关闭
            checkboxVO.setCheckboxEnable(false);
            checkboxVO.setDisableMessage("问题不可关闭");
            return checkboxVO;
        }
        //step3:Login User类型：Super Admin OR 所属用户群组 对 Trigger所属主机的主机群组 有读写权限（3）
        List<String> userIds = new ArrayList<>();
        userIds.add(userId);
        UserInfoDTO userInfoDTO = userService.getUsersByUserIds(userIds).get(0);
        if(userInfoDTO.getType() != ZApiParameter.USER_TYPE.SUPER_ADMIN.value) {
            //所属用户群组 对 Trigger所属主机的主机群组 有读写权限（3）
            List<BriefPermissionDTO> permissionDTOS = usergroupService.getUsrgrpsByUserIds(userIds).get(0).getRights();
            for(BriefPermissionDTO permissionDTO : permissionDTOS) {
                if(permissionDTO.getId().equals(groupId) && permissionDTO.getPermission().equals(ZApiParameter.USERGROUP_PERMISSION.READ_WRITE)) {
                    checkboxVO.setCheckboxEnable(true);
                    checkboxVO.setDisableMessage("问题可关闭");
                    return checkboxVO;
                }
            }
            //循环结束还未返回方法，则无权限
            checkboxVO.setCheckboxEnable(false);
            checkboxVO.setDisableMessage("无权限");
            return checkboxVO;
        }else {
            //Super Admin
            checkboxVO.setCheckboxEnable(true);
            checkboxVO.setDisableMessage("问题可关闭");
            return checkboxVO;
        }

    }

    /**
     * 根据 acknowledgeDTO 执行确认动作
     * @param acknowledgeDTO
     * @return AcknowledgeVO  eventids[] 影响的事件 events
     * @throws ServiceException
     */
    @Override
    public AcknowledgeVO acknowledgeEvent(BriefAcknowledgeDTO acknowledgeDTO) throws ServiceException {
        List<String> eventIds = new ArrayList<>();
        eventIds.add(acknowledgeDTO.getEventId());
        EventAcknowledgeRequest acknowledgeRequest = new EventAcknowledgeRequest();
        acknowledgeRequest.getParams()
                .setEventIds(eventIds)
                .setMessage(acknowledgeDTO.getMessage())
                .setAction(Integer.parseInt(acknowledgeDTO.getAction()));
        AcknowledgeVO acknowledgeVO;
        try {
           acknowledgeVO = zApi.Event().acknowledge(acknowledgeRequest,AcknowledgeVO.class);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                throw new AuthExpireException(e.getMessage());
            }
            logger.error("执行确认操作失败 {}", e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return acknowledgeVO;
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

    /**
     * 根据 eventId 组装 事件详情弹出框 事件细节信息 EventDetailVO
     * @param eventId
     * @return
     * @throws ServiceException
     */
    @Override
    public EventDetailVO getEventDetailByEventId(String eventId) throws ServiceException {
        List<String> eventIds = new ArrayList<>();
        eventIds.add(eventId);
        BriefEventDTO eventDTO = getEventByEventId(eventIds).get(0);
        String triggerId = eventDTO.getObjectId();
        List<String> triggerIds = new ArrayList<>();
        triggerIds.add(triggerId);
        BriefTriggerDTO triggerDTO = triggerService.getTriggersByTriggerIds(triggerIds).get(0);
        String itemId = triggerDTO.getItems().get(0).getItemId();
        List<String> itemIds = new ArrayList<>();
        itemIds.add(itemId);
        String pointName = pointSerivce.getPointsByItemIds(itemIds).get(0).getName();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        //赋值
        EventDetailVO eventDetailVO = new EventDetailVO();
        eventDetailVO.setEventId(eventId);
        eventDetailVO.setHostName(eventDTO.getHosts().get(0).getName());
        eventDetailVO.setPointName(pointName);
        eventDetailVO.setItemName(triggerDTO.getItems().get(0).getName());
        eventDetailVO.setTriggerName(triggerDTO.getName());
        eventDetailVO.setThreshold(ThresholdUtils.getThresholdValueSymbol(triggerDTO.getExpression()));
        eventDetailVO.setLevel(StatusConverter.getStatusByTriggerPriority(triggerDTO.getPriority()));
        //状态还未映射值
        eventDetailVO.setState(eventDTO.getValue());
        eventDetailVO.setProblemTime(eventDTO.getClock().format(formatter));
        //recoveryTime
        if(!"0".equals(eventDTO.getrEventid())) {
            //有恢复事件
            List<String> recoveryIds = new ArrayList<>();
            recoveryIds.add(eventDTO.getrEventid());
            BriefEventDTO recoveryDTO = getEventByEventId(recoveryIds).get(0);
            eventDetailVO.setRecoveryTime(recoveryDTO.getClock().format(formatter));
        }
        //closed,closeduser
        List<BriefAcknowledgeDTO> acknowledgeDTOS = eventDTO.getAcknowledges();
        for(BriefAcknowledgeDTO acknowledgeDTO : acknowledgeDTOS) {
            if(Integer.parseInt(acknowledgeDTO.getAction()) == ZApiParameter.ACKNOWLEDGE_ACTION.ACKNOWLEDGED.value) {
                eventDetailVO.setClosed(true);
                eventDetailVO.setClosedUser(acknowledgeDTO.getAlias());
                break;
            }else {
                eventDetailVO.setClosed(false);
            }
        }

        return eventDetailVO;

    }

    /**
     * 组装当天一天的 问题事件数量 ProblemListVOS
     * @param formQuery
     * @return
     * @throws ServiceException
     */
    @Override
    public List<ProblemListVO> getOneDayProblemListVOS(CalendarFormQuery formQuery) throws ServiceException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime todayTime = LocalDate.parse(formQuery.getToday(),formatter).atStartOfDay();
        Long beginTime = todayTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() / 1000;
        Long endTime = beginTime + 24 * 3600;
        //处理依赖关系
        List<BriefTriggerDTO> triggerDTOS = triggerService.listTriggersSkipDependent();
        List<String > triggerIds = new ArrayList<>();
        for(BriefTriggerDTO triggerDTO : triggerDTOS) {
            triggerIds.add(triggerDTO.getTriggerId());
        }
        List<BriefEventDTO> problemEventDTOS = getProblemEventsByTime(String.valueOf(beginTime),String.valueOf(endTime),triggerIds);
        //用于接收查询条件后的结果DTO
        List<BriefEventDTO> resultEventDTOS = new ArrayList<>();
        //处理查询条件 resultEventDTOS
        for(BriefEventDTO eventDTO : problemEventDTOS) {
            boolean selectHost;
            boolean selectPrority ;
            boolean selectAcknowledge;
            //host查询
            if(formQuery.getHostId() == null) {
                selectHost = true;
            }else {
                selectHost = eventDTO.getHosts().get(0).getHostId().equals(formQuery.getHostId());
            }
            //严重性查询
            if(formQuery.getPriority() == null) {
                selectPrority = true;
            }else {
                selectPrority = eventDTO.getRelatedObject().getPriority().equals(formQuery.getPriority());
            }
            //确认查询
            if(formQuery.getAcknowledge() == null) {
                selectAcknowledge = true;
            }else {
                selectAcknowledge = eventDTO.getAcknowledged().equals(formQuery.getAcknowledge());
            }
            //执行过滤条件
            if(selectHost && selectPrority && selectAcknowledge) {
                resultEventDTOS.add(eventDTO);
            }
        }
        List<String> recoveryEventIds = new ArrayList<>();
        for(BriefEventDTO eventDTO : resultEventDTOS) {
            if(!"0".equals(eventDTO.getrEventid())) {
                recoveryEventIds.add(eventDTO.getrEventid());
            }
        }
        //获取 恢复事件集合
        List<BriefEventDTO> recoveryEventDTOS = getEventByEventId(recoveryEventIds);
        //将 BriefProblemDTO 转换成 ProblemListVO
        List<ProblemListVO> problemListVOS = ProblemListVO.transformVOSUseBriefEventDTO(resultEventDTOS,recoveryEventDTOS);
        //时间排序
        return ProblemListVO.getSortListByProblemTime(problemListVOS);
    }


}
