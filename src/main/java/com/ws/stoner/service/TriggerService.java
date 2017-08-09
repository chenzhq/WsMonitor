package com.ws.stoner.service;


import com.ws.bix4j.access.trigger.TriggerGetRequest;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefTriggerDTO;
import com.ws.stoner.model.view.DashboardProblemVO;

import java.util.List;

/**
 * Created by zkf on 2017/6/8.
 */
public interface TriggerService {
    /**
     * 根据 request 获取触发器 list
     *
     * @return the list
     * @throws ServiceException the auth expire exception
     */
    List<BriefTriggerDTO> listTrigger(TriggerGetRequest request) throws ServiceException;

    /**
     * 获取所有的问题触发器ids
     * @return
     * @throws ServiceException
     */
    List<String> getProblemTriggerIds() throws ServiceException;

    <T> List<T> listTrigger(TriggerGetRequest triggerGetRequest, Class<T> clazz);

    /**
     * 根据 request 获取触发器数量
     * @return
     * @throws ServiceException
     */
    int countTrigger(TriggerGetRequest request) throws ServiceException;

    /**
     *
     * @return
     * @throws ServiceException
     */
    List<DashboardProblemVO> listBriefProblems() throws ServiceException;

    /**
     * 根据itemIds获取监控中的 trggierDTO list
     * @param itemIds
     * @return
     * @throws ServiceException
     */
    List<BriefTriggerDTO> getTriggersByItemIds(List<String> itemIds) throws ServiceException;


}
