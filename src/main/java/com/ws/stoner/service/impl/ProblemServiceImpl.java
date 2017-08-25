package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.ZApiParameter;
import com.ws.bix4j.access.problem.ProblemGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefAlertDTO;
import com.ws.stoner.model.dto.BriefEventDTO;
import com.ws.stoner.model.dto.BriefProblemDTO;
import com.ws.stoner.model.dto.BriefTriggerDTO;
import com.ws.stoner.model.view.ProblemListVO;
import com.ws.stoner.service.AlertService;
import com.ws.stoner.service.EventService;
import com.ws.stoner.service.ProblemService;
import com.ws.stoner.service.TriggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ws.bix4j.exception.ZApiExceptionEnum.NO_AUTH_ASSIGN;
import static com.ws.bix4j.exception.ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE;

/**
 * Created by zkf on 2017/8/25.
 */
@Service
public class ProblemServiceImpl implements ProblemService {

    private static final Logger logger = LoggerFactory.getLogger(ProblemServiceImpl.class);
    @Autowired
    private ZApi zApi;
    @Autowired
    private TriggerService triggerService;
    @Autowired
    private EventService eventService;
    @Autowired
    private AlertService alertService;

/*
 *基础方法
 */
    /**
     * List problem list.
     *
     * @return the list
     * @throws ServiceException the auth expire exception
     */
    @Override
    public List<BriefProblemDTO> listProblem(ProblemGetRequest request) throws ServiceException {
        List<BriefProblemDTO> problems;
        try {
            problems = zApi.Problem().get(request,BriefProblemDTO.class);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询问题problem错误！{}", e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return problems;
    }
/*
 *zabbix 方法
 */
    /**
     * 获取最近的 问题和恢复 problem
     * List<String> triggerIds 过滤依赖关系
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefProblemDTO> getRecentProblem(List<String> triggerIds) throws ServiceException {
        ProblemGetRequest problemGetRequest = new ProblemGetRequest();
        List<Integer> values = new ArrayList<>();
        values.add(ZApiParameter.EVENT_VALUE.PROBLEM.value);
        problemGetRequest.getParams()
                .setSource(ZApiParameter.SOURCE.TRIGGER.value)
                .setObject(ZApiParameter.OBJECT.TRIGGER.value)
                .setRecent("true")
                .setObjectIds(triggerIds)
                .setOutput(BriefProblemDTO.PROPERTY_NAMES);
        return listProblem(problemGetRequest);
    }

    /**
     * 获取 30分钟内 的已恢复的问题 和所有未恢复的问题
     * @return
     * @throws ServiceException
     */
    @Override
    public List<ProblemListVO> getLastProblems() throws ServiceException {
        //处理依赖关系
        List<BriefTriggerDTO> triggerDTOS = triggerService.listTriggersSkipDependent();
        List<String > triggerIds = new ArrayList<>();
        for(BriefTriggerDTO triggerDTO : triggerDTOS) {
            triggerIds.add(triggerDTO.getTriggerId());
        }
        List<BriefProblemDTO> problemDTOS = getRecentProblem(triggerIds);
        List<String> eventIds = new ArrayList<>();
        for(BriefProblemDTO problemDTO : problemDTOS) {
            eventIds.add(problemDTO.getEventId());
        }
        //获取对应的事件对象
        List<BriefEventDTO> eventDTOS = eventService.getEventByEventId(eventIds);
        //获取半个小时内的恢复事件
        String beginTime = String.valueOf(System.currentTimeMillis() / 1000 - 30 * 60);
        String endTime = String.valueOf(System.currentTimeMillis() / 1000 );
        List<BriefEventDTO> recoveryEventDTOS = eventService.getRecoveryEventsByTime(beginTime, endTime,triggerIds);
        //获取总的告警信息
        List<BriefAlertDTO> allAlertDTOS = alertService.getAlertDTOByEventIds(eventIds);
        //将 BriefProblemDTO 转换成 ProblemListVO
        List<ProblemListVO> problemListVOS = ProblemListVO.transformVOSUseBriefEventDTO(eventDTOS,recoveryEventDTOS,allAlertDTOS);
        //时间排序
        return ProblemListVO.getSortListByProblemTime(problemListVOS);
    }
}
