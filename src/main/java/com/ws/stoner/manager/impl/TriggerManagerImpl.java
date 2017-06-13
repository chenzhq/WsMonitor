package com.ws.stoner.manager.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.ZApiParameter;
import com.ws.bix4j.access.trigger.TriggerGetRequest;
import com.ws.bix4j.bean.TriggerDO;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.manager.TriggerManager;
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
public class TriggerManagerImpl implements TriggerManager {

    private static final Logger logger = LoggerFactory.getLogger(HostManagerImpl.class);
    @Autowired
    private ZApi zApi;

    @Override
    public <T> List<T> listTrigger(TriggerGetRequest triggerGetRequest, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        try {
            result = zApi.Trigger().get(triggerGetRequest, clazz);
        } catch (ZApiException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<TriggerDO> listTrigger(TriggerGetRequest request) throws AuthExpireException {
        List<TriggerDO> triggers;
        try {
            triggers = zApi.Trigger().get(request);
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

    @Override
    public int countTrigger(TriggerGetRequest request) throws ManagerException {
        int triggerNum;
        try {
            triggerNum = zApi.Trigger().count(request);
        } catch (ZApiException e) {
            if (e.getCode().equals(ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE)) {
                throw new AuthExpireException("");
            }
            e.printStackTrace();
            logger.error("查询触发器错误！{}", e.getMessage());
            return 0;
        }
        return triggerNum;
    }
}
