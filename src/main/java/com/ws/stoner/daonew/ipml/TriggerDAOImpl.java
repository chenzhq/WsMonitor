package com.ws.stoner.daonew.ipml;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.ZApiParameter;
import com.ws.bix4j.access.trigger.TriggerGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.daonew.TriggerDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.dto.BriefEventDTO;
import com.ws.stoner.model.dto.BriefHostDTO;
import com.ws.stoner.model.dto.BriefItemDTO;
import com.ws.stoner.model.dto.BriefTriggerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ws.bix4j.exception.ZApiExceptionEnum.NO_AUTH_ASSIGN;
import static com.ws.bix4j.exception.ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE;

/**
 * Created by zhongkf on 2017/12/25
 */
@Repository
public class TriggerDAOImpl implements TriggerDAO {

    private static final Logger logger = LoggerFactory.getLogger(TriggerDAOImpl.class);
    @Autowired
    private ZApi zApi;
    private List<BriefTriggerDTO> listTrigger(TriggerGetRequest request) throws DAOException {
        List<BriefTriggerDTO> triggers;
        try {
            triggers = zApi.Trigger().get(request,BriefTriggerDTO.class);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                //throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询触发器 {}", e.getMessage());
            throw new DAOException(e.getMessage());
        }

        return triggers;
    }


    @Override
    public List<BriefTriggerDTO> listProblemTriggers() throws DAOException {
        TriggerGetRequest request = new TriggerGetRequest();
        Map<String, Integer> triggerFilter = new HashMap<>();
        triggerFilter.put("state", ZApiParameter.TRIGGER_STATE.UP_TO_DATE.value);
        triggerFilter.put("value", ZApiParameter.TRIGGER_VALUE.PROBLEM.value);
        request.getParams()
                .setMonitored(true)
//                .setOnlyTrue(true)
                .setExpandDescription(true)
                .setExpandExpression(true)
                .setSkipDependent(true)
                .setSelectHosts(BriefHostDTO.PROPERTY_NAMES)
                .setSelectLastEvent(BriefEventDTO.PROPERTY_NAMES)
                .setSelectItems(BriefItemDTO.PROPERTY_NAMES)
                .setOutput(BriefTriggerDTO.PROPERTY_NAMES)
                .setFilter(triggerFilter);
        return listTrigger(request);
    }

    @Override
    public List<BriefTriggerDTO> getProblemTriggersByHostIds(List<String> hostIds) throws DAOException {
        TriggerGetRequest request = new TriggerGetRequest();
        Map<String, Integer> triggerFilter = new HashMap<>();
        triggerFilter.put("state", ZApiParameter.TRIGGER_STATE.UP_TO_DATE.value);
        triggerFilter.put("value", ZApiParameter.TRIGGER_VALUE.PROBLEM.value);
        request.getParams()
                .setMonitored(true)
//                .setOnlyTrue(true)
                .setHostIds(hostIds)
                .setExpandDescription(true)
                .setExpandExpression(true)
                .setSkipDependent(true)
                .setSelectHosts(BriefHostDTO.PROPERTY_NAMES)
                .setSelectLastEvent(BriefEventDTO.PROPERTY_NAMES)
                .setSelectItems(BriefItemDTO.PROPERTY_NAMES)
                .setOutput(BriefTriggerDTO.PROPERTY_NAMES)
                .setFilter(triggerFilter);
        return listTrigger(request);
    }

    @Override
    public List<BriefTriggerDTO> getProblemTriggersByPlatIds(List<String> platformIds) throws DAOException {
        TriggerGetRequest request = new TriggerGetRequest();
        Map<String, Integer> triggerFilter = new HashMap<>();
        triggerFilter.put("state", ZApiParameter.TRIGGER_STATE.UP_TO_DATE.value);
        triggerFilter.put("value", ZApiParameter.TRIGGER_VALUE.PROBLEM.value);
        request.getParams()
                .setMonitored(true)
//                .setOnlyTrue(true)
                .setGroupIds(platformIds)
                .setExpandDescription(true)
                .setExpandExpression(true)
                .setSkipDependent(true)
                .setSelectHosts(BriefHostDTO.PROPERTY_NAMES)
                .setSelectLastEvent(BriefEventDTO.PROPERTY_NAMES)
                .setSelectItems(BriefItemDTO.PROPERTY_NAMES)
                .setOutput(BriefTriggerDTO.PROPERTY_NAMES)
                .setFilter(triggerFilter);
        return listTrigger(request);
    }

    /**
     * 根据 triggerIds 查询对应的 triggerDTOS  selectItems
     * @param triggerIds
     * @return
     * @throws DAOException
     */
    @Override
    public List<BriefTriggerDTO> getTriggersByTriggerIds(List<String> triggerIds) throws DAOException {
        TriggerGetRequest request = new TriggerGetRequest();
        request.getParams()
                .setTriggerIds(triggerIds)
                .setExpandDescription(true)
                .setExpandExpression(true)
                .setSelectItems(BriefItemDTO.PROPERTY_NAMES)
                .setSelectHosts(BriefHostDTO.PROPERTY_NAMES)
                .setOutput(BriefTriggerDTO.PROPERTY_NAMES);
        return listTrigger(request);
    }


}
