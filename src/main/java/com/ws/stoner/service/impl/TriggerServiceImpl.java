package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.ZApiParameter;
import com.ws.bix4j.access.trigger.TriggerGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefHostDTO;
import com.ws.stoner.model.dto.BriefItemDTO;
import com.ws.stoner.model.dto.BriefTriggerDTO;
import com.ws.stoner.model.view.DashboardProblemVO;
import com.ws.stoner.service.TriggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<BriefTriggerDTO> listTrigger(TriggerGetRequest request) throws ServiceException {
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
}
