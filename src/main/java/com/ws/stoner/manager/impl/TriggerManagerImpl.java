package com.ws.stoner.manager.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.ZApiParameter;
import com.ws.bix4j.access.trigger.TriggerGetRequest;
import com.ws.bix4j.bean.TriggerDO;
import com.ws.bix4j.exception.ZApiException;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.manager.TriggerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pc on 2017/6/8.
 */
@Service
public class TriggerManagerImpl implements TriggerManager {

    private static final Logger logger = LoggerFactory.getLogger(HostManagerImpl.class);
    @Autowired
    private ZApi zApi;

    @Override
    public List<TriggerDO> listTrigger() throws AuthExpireException {
        TriggerGetRequest triggerGetRequest = new TriggerGetRequest();
        List<TriggerDO> triggers;
        try {
            triggers = zApi.Trigger().get(triggerGetRequest);
        } catch (ZApiException e) {
            e.printStackTrace();
            return null;
        }

        return triggers;
    }

    /**
     * 获取监控中monitored，非维护maintenance，状态为unknown的触发器
     * @return
     * @throws ManagerException
     */
    @Override
    public List<TriggerDO> listUnknownTrigger() throws ManagerException {
       TriggerGetRequest triggerGetRequest = new TriggerGetRequest();
        Map<String, Integer> statusFilter = new HashMap<>();
        statusFilter.put("state", ZApiParameter.TRIGGER_STATE.UNKNOWN.value);
        triggerGetRequest.getParams().setFilter(statusFilter);
        triggerGetRequest.getParams().setMonitored(true).setMaintenance(false);

        List<TriggerDO> unknownTriggers;
        try {
            unknownTriggers = zApi.Trigger().get(triggerGetRequest);
        } catch (ZApiException e) {
            e.printStackTrace();
            return null;
        }
        return unknownTriggers;
    }
}
