package com.ws.stoner.manager;


import com.ws.bix4j.access.trigger.TriggerGetRequest;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.model.brief.TriggerBrief;
import com.ws.stoner.model.dto.BriefTriggerDTO;

import java.util.List;

/**
 * Created by zkf on 2017/6/8.
 */
public interface TriggerManager {
    /**
     * 根据 request 获取触发器 list
     *
     * @return the list
     * @throws AuthExpireException the auth expire exception
     */
    List<BriefTriggerDTO> listTrigger(TriggerGetRequest request) throws AuthExpireException;

    /**
     * 获取所有的问题触发器ids
     * @return
     * @throws ManagerException
     */
    List<String> getProblemTriggerIds() throws ManagerException;

    <T> List<T> listTrigger(TriggerGetRequest triggerGetRequest, Class<T> clazz);

    /**
     * 根据 request 获取触发器数量
     * @return
     * @throws ManagerException
     */
    int countTrigger(TriggerGetRequest request) throws ManagerException;


}
