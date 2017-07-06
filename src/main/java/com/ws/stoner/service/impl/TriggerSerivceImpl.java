package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.ZApiParameter;
import com.ws.bix4j.access.trigger.TriggerGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.model.dto.BriefHostDTO;
import com.ws.stoner.model.view.BriefProblemVO;
import com.ws.stoner.service.TriggerSerivce;
import com.ws.stoner.model.dto.BriefTriggerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pc on 2017/6/8.
 */
@Service
public class TriggerSerivceImpl implements TriggerSerivce {

    private static final Logger logger = LoggerFactory.getLogger(HostSerivceImpl.class);
    @Autowired
    private ZApi zApi;

    @Override
    public List<BriefTriggerDTO> listTrigger(TriggerGetRequest request) throws ManagerException {
        List<BriefTriggerDTO> triggers;
        try {
            triggers = zApi.Trigger().get(request,BriefTriggerDTO.class);
        } catch (ZApiException e) {
            e.printStackTrace();
            return null;
        }

        return triggers;
    }

    @Override
    public List<String> getProblemTriggerIds() throws ManagerException {
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
        try {
            triggers1 = listTrigger(triggerGetRequest1);
        } catch (ManagerException e) {
            e.printStackTrace();
            return null;
        }
        //step2:获取state:unknown 触发器list
        TriggerGetRequest triggerGetRequest2 = new TriggerGetRequest();
        Map<String, Object> triggerFilter2 = new HashMap<>();
        triggerFilter2.put("state", ZApiParameter.TRIGGER_STATE.UNKNOWN.value);
        triggerGetRequest2.getParams()
                .setOutput(BriefTriggerDTO.PROPERTY_NAMES)
                .setFilter(triggerFilter2);
        List<BriefTriggerDTO> triggers2 ;
        try {
            triggers2 = listTrigger(triggerGetRequest2);
        } catch (AuthExpireException e) {
            e.printStackTrace();
            return null;
        }
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
    public int countTrigger(TriggerGetRequest request) throws ManagerException {
        int triggerNum;
        try {
            triggerNum = zApi.Trigger().count(request);
        } catch (ZApiException e) {
            if (e.getCode().equals(ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE)) {
                throw new ManagerException("");
            }
            e.printStackTrace();
            logger.error("查询触发器错误！{}", e.getMessage());
            return 0;
        }
        return triggerNum;
    }

    @Override
    public List<BriefProblemVO> listBriefProblems() {
        TriggerGetRequest request = new TriggerGetRequest();
        Map<String, Integer> triggerFilter = new HashMap<>();
        triggerFilter.put("state", ZApiParameter.TRIGGER_STATE.UP_TO_DATE.value);
        request.getParams()
                .setMonitored(true)
                .setOnlyTrue(true)
                .setExpandDescription(true)
                .setSelectHosts(BriefHostDTO.PROPERTY_NAMES)
                .setOutput(BriefProblemVO.PROPERTY_NAMES)
                .setFilter(triggerFilter);
        return listTrigger(request, BriefProblemVO.class);
    }
}
