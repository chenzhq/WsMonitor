package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApiParameter;
import com.ws.bix4j.access.trigger.TriggerGetRequest;
import com.ws.stoner.manager.TriggerManager;
import com.ws.stoner.model.dto.BriefHostDTO;
import com.ws.stoner.model.view.BriefProblemVO;
import com.ws.stoner.service.FetchBriefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pc on 2017/6/12.
 */
@Service
public class FetchBriefServiceImpl implements FetchBriefService {

    @Autowired
    private TriggerManager triggerManager;

    @Override
    public List<BriefProblemVO> listBriefProblems() {
        TriggerGetRequest request = new TriggerGetRequest();
        Map<String, Integer> triggerFilter = new HashMap<>();
        triggerFilter.put("state", ZApiParameter.TRIGGER_STATE.UP_TO_DATE.value);
        request.getParams()
                .setMonitored(true)
                .setOnlyTrue(true)
                .setListHosts(BriefHostDTO.PROPERTY_NAMES)
                .setOutput(BriefProblemVO.PROPERTY_NAMES)
                .setFilter(triggerFilter);
        return triggerManager.listTrigger(request, BriefProblemVO.class);
    }
}
