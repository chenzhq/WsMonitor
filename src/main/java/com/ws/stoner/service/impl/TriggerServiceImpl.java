package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.ZApiParameter;
import com.ws.bix4j.access.trigger.TriggerGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.constant.StatusEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.*;
import com.ws.stoner.model.view.DashboardProblemVO;
import com.ws.stoner.model.view.ProblemDetailVO;
import com.ws.stoner.model.view.ProblemListVO;
import com.ws.stoner.service.AlertService;
import com.ws.stoner.service.PointSerivce;
import com.ws.stoner.service.TriggerService;
import com.ws.stoner.utils.AlertStatusConverter;
import com.ws.stoner.utils.StatusConverter;
import com.ws.stoner.utils.ThresholdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ws.bix4j.exception.ZApiExceptionEnum.NO_AUTH_ASSIGN;
import static com.ws.bix4j.exception.ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE;

/**
 * Created by pc on 2017/6/8.
 */
@Service
public class TriggerServiceImpl implements TriggerService {

    private static final Logger logger = LoggerFactory.getLogger(HostServiceImpl.class);
    @Autowired
    private ZApi zApi;

    @Autowired
    private AlertService alertService;

    @Autowired
    private PointSerivce pointSerivce;

    private List<BriefTriggerDTO> listTrigger(TriggerGetRequest request) throws ServiceException {
        List<BriefTriggerDTO> triggers;
        try {
            triggers = zApi.Trigger().get(request,BriefTriggerDTO.class);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询触发器 {}", e.getMessage());
            throw new ServiceException(e.getMessage());
        }

        return triggers;
    }

    @Override
    public List<String> getProblemTriggerIds() throws ServiceException {
        //step1:获取state:up to date 触发器list
        TriggerGetRequest triggerGetRequest1 = new TriggerGetRequest();
        Map<String, Object> triggerFilter1 = new HashMap<>();
        triggerFilter1.put("state", ZApiParameter.TRIGGER_STATE.UP_TO_DATE.value);
        triggerFilter1.put("value",ZApiParameter.TRIGGER_VALUE.PROBLEM.value);
        triggerFilter1.put("only_true",true);
        triggerGetRequest1.getParams()
                .setOutput(BriefTriggerDTO.PROPERTY_NAMES)
                .setFilter(triggerFilter1);
        List<BriefTriggerDTO> triggers1 ;
        triggers1 = listTrigger(triggerGetRequest1);
        //step2:获取state:unknown 触发器list
        TriggerGetRequest triggerGetRequest2 = new TriggerGetRequest();
        Map<String, Object> triggerFilter2 = new HashMap<>();
        triggerFilter2.put("state", ZApiParameter.TRIGGER_STATE.UNKNOWN.value);
        triggerGetRequest2.getParams()
                .setOutput(BriefTriggerDTO.PROPERTY_NAMES)
                .setFilter(triggerFilter2);
        List<BriefTriggerDTO> triggers2 ;
        triggers2 = listTrigger(triggerGetRequest2);
        //step3:组装两类触发器得到 triggerIds
        List<String> triggerIds = new ArrayList<>();
        for(BriefTriggerDTO trigger : triggers1) {
            triggerIds.add(trigger.getTriggerId());
        }
        for(BriefTriggerDTO trigger : triggers2) {
            triggerIds.add(trigger.getTriggerId());
        }
        return triggerIds;
    }

    @Override
    public <T> List<T> listTrigger(TriggerGetRequest triggerGetRequest, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        try {
            result = zApi.Trigger().get(triggerGetRequest, clazz);
        } catch (ZApiException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public int countTrigger(TriggerGetRequest request) throws ServiceException {
        int triggerNum;
        try {
            triggerNum = zApi.Trigger().count(request);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询触发器 {}", e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return triggerNum;
    }

    @Override
    public List<DashboardProblemVO> listBriefProblems() {
        TriggerGetRequest request = new TriggerGetRequest();
        Map<String, Integer> triggerFilter = new HashMap<>();
        triggerFilter.put("state", ZApiParameter.TRIGGER_STATE.UP_TO_DATE.value);
        request.getParams()
                .setMonitored(true)
                .setOnlyTrue(true)
                .setExpandDescription(true)
                .setExpandExpression(true)
                .setSelectHosts(BriefHostDTO.PROPERTY_NAMES)
                .setOutput(DashboardProblemVO.PROPERTY_NAMES)
                .setFilter(triggerFilter);
        return listTrigger(request, DashboardProblemVO.class);
    }


    /**
     * 根据 triggerIds 查询对应的 triggerDTOS selectHosts selectItems
     * @param triggerIds
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefTriggerDTO> getTriggersByTriggerIds(List<String> triggerIds) throws ServiceException {
        TriggerGetRequest request = new TriggerGetRequest();
        request.getParams()
                .setTriggerIds(triggerIds)
                .setExpandDescription(true)
                .setExpandExpression(true)
                .setSelectHosts(BriefHostDTO.PROPERTY_NAMES)
                .setSelectItems(BriefItemDTO.PROPERTY_NAMES)
                .setOutput(BriefTriggerDTO.PROPERTY_NAMES);
        return listTrigger(request, BriefTriggerDTO.class);
    }

    /**
     * 根据itemIds获取监控中的 trggierDTO list
     * @param itemIds
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefTriggerDTO> getTriggersByItemIds(List<String> itemIds) throws ServiceException {
        TriggerGetRequest triggerGetRequest = new TriggerGetRequest();
        triggerGetRequest.getParams()
                .setMonitored(true)
                .setItemIds(itemIds)
                .setExpandExpression(true)
                .setSelectItems(BriefItemDTO.PROPERTY_NAMES)
                .setOutput(BriefTriggerDTO.PROPERTY_NAMES);
        return listTrigger(triggerGetRequest,BriefTriggerDTO.class);
    }

    /**
     * 列出所有触发器 排除依赖 触发器 BriefTriggerDTOS
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefTriggerDTO> listTriggersSkipDependent() throws ServiceException {
        TriggerGetRequest request = new TriggerGetRequest();
        Map<String, Integer> triggerFilter = new HashMap<>();
        triggerFilter.put("state", ZApiParameter.TRIGGER_STATE.UP_TO_DATE.value);
        request.getParams()
                .setMonitored(true)
                .setExpandDescription(true)
                .setExpandExpression(true)
                .setSkipDependent(true)
                .setOutput(BriefTriggerDTO.PROPERTY_NAMES)
                .setFilter(triggerFilter);
        return listTrigger(request, BriefTriggerDTO.class);
    }

    /**
     * 列出问题触发器 BriefTriggerDTOS
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefTriggerDTO> listProblemTriggers() throws ServiceException {
        TriggerGetRequest request = new TriggerGetRequest();
        Map<String, Integer> triggerFilter = new HashMap<>();
        triggerFilter.put("state", ZApiParameter.TRIGGER_STATE.UP_TO_DATE.value);
        request.getParams()
                .setMonitored(true)
                .setOnlyTrue(true)
                .setExpandDescription(true)
                .setExpandExpression(true)
                .setSkipDependent(true)
                .setSelectHosts(BriefHostDTO.PROPERTY_NAMES)
                .setSelectLastEvent(BriefEventDTO.PROPERTY_NAMES)
                .setOutput(BriefTriggerDTO.PROPERTY_NAMES)
                .setFilter(triggerFilter);
        return listTrigger(request, BriefTriggerDTO.class);
    }

    /**
     * 问题管理 问题列表  当前问题 ProblemListVO
     * @return
     * @throws ServiceException
     */
    @Override
    public List<ProblemListVO> listProblemListVO() throws ServiceException {
        //获取问题触发器
        List<BriefTriggerDTO> triggerDTOS = listProblemTriggers();
        List<ProblemListVO> problemListVOS = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        for(BriefTriggerDTO triggerDTO : triggerDTOS) {
            ProblemListVO problemListVO = new ProblemListVO();
            problemListVO.setTriggerId(triggerDTO.getTriggerId());
            if(triggerDTO.getHosts().size() != 0) {
                problemListVO.setHostName(triggerDTO.getHosts().get(0).getName());
            }
            if(triggerDTO.getLastEvent() != null) {
                problemListVO.setProblemEventid(triggerDTO.getLastEvent().getEventId());
                problemListVO.setProblemTime(triggerDTO.getLastEvent().getClock().format(formatter));
                //durationString
                problemListVO.setDurationString(triggerDTO.getLastEvent().getClock(), LocalDateTime.now());
                if(triggerDTO.getLastEvent().getAcknowledged() == ZApiParameter.ACKNOWLEDGE_ACTION.ACKNOWLEDGED.value) {
                    problemListVO.setAcknowledged("是");
                }else {
                    problemListVO.setAcknowledged("否");
                }
                //alertState
                List<String> eventIds = new ArrayList<>();
                eventIds.add(triggerDTO.getLastEvent().getEventId());
                List<BriefAlertDTO> alertDTOS = alertService.getAlertDTOByEventIds(eventIds);
                //问题和恢复的告警,告警数
                Map<String,Integer> alertMap = AlertStatusConverter.getMassageByAlertStatus(alertDTOS);
                problemListVO.setAlertNum(alertMap.entrySet().iterator().next().getValue());
                problemListVO.setAlertState(alertMap.entrySet().iterator().next().getKey());
            }
            problemListVO.setProblemState("问题");
            problemListVO.setDescription(triggerDTO.getName());
            //PriorityState
            problemListVO.setPriorityState(StatusConverter.getStatusByTriggerPriority(triggerDTO.getPriority()));
            problemListVOS.add(problemListVO);


        }
        //时间排序
        return ProblemListVO.getSortListByProblemTime(problemListVOS);
    }


    /**
     * 问题管理 问题详情 基础静态信息 ProblemDetailVO
     * @return
     * @throws ServiceException
     */
    @Override
    public ProblemDetailVO getProblemDetailVOByTriggerId(String triggerId) throws ServiceException {
        List<String> triggerIds = new ArrayList<>();
        triggerIds.add(triggerId);
        BriefTriggerDTO triggerDTO = getTriggersByTriggerIds(triggerIds).get(0);
        String itemId = triggerDTO.getItems().get(0).getItemId();
        List<String> itemIds = new ArrayList<>();
        itemIds.add(itemId);
        ProblemDetailVO problemDetailVO = new ProblemDetailVO();
        problemDetailVO.setTriggerId(triggerId);
        problemDetailVO.setPointName(pointSerivce.getPointsByItemIds(itemIds).get(0).getName());
        problemDetailVO.setHostName(triggerDTO.getHosts().get(0).getName());
        problemDetailVO.setTriggerName(triggerDTO.getName());
        String level = StatusConverter.getStatusByTriggerPriority(triggerDTO.getPriority());
        problemDetailVO.setLevel(level);
        problemDetailVO.setState(triggerDTO.getValue());
        problemDetailVO.setItemName(triggerDTO.getItems().get(0).getName());
        if(level.equals(StatusEnum.WARNING.getName())) {
            problemDetailVO.setWarningPoint(ThresholdUtils.getThresholdValueSymbol(triggerDTO.getExpression()));
        }else if(level.equals(StatusEnum.HIGH.getName())) {
            problemDetailVO.setHighPoint(ThresholdUtils.getThresholdValueSymbol(triggerDTO.getExpression()));
        }
        return problemDetailVO;
    }
}
