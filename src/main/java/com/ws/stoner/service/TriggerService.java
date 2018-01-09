package com.ws.stoner.service;


import com.ws.bix4j.access.trigger.TriggerGetRequest;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefTriggerDTO;
import com.ws.stoner.model.view.dashboard.DashboardProblemVO;
import com.ws.stoner.model.view.problem.ProblemDetailVO;
import com.ws.stoner.model.view.problem.ProblemListVO;

import java.util.List;

/**
 * Created by zkf on 2017/6/8.
 */
public interface TriggerService {

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
/*
zabbix 方法
 */
    /**
     *
     * @return
     * @throws ServiceException
     */
    List<DashboardProblemVO> listBriefProblems() throws ServiceException;

    /**
     * 根据 triggerIds 查询对应的 triggerDTOS selectHost
     * @param triggerIds
     * @return
     * @throws ServiceException
     */
    List<BriefTriggerDTO> getTriggersByTriggerIds(List<String> triggerIds ) throws ServiceException;
    /**
     * 根据itemIds获取监控中的 trggierDTO list
     * @param itemIds
     * @return
     * @throws ServiceException
     */
    List<BriefTriggerDTO> getTriggersByItemIds(List<String> itemIds) throws ServiceException;

    /**
     * 列出所有触发器 排除依赖 触发器 BriefTriggerDTOS
     * @return
     * @throws ServiceException
     */
    List<BriefTriggerDTO> listTriggersSkipDependent() throws ServiceException;

    /**
     * 列出问题触发器 BriefTriggerDTOS
     * @return
     * @throws ServiceException
     */
    List<BriefTriggerDTO> listProblemTriggers() throws ServiceException;

    /**
     * 问题管理 问题列表 ProblemListVO
     * @return
     * @throws ServiceException
     */
    List<ProblemListVO> listProblemListVO() throws ServiceException;

    /**
     * 问题管理 问题详情 基础静态信息 ProblemVO
     * @return
     * @throws ServiceException
     */
    ProblemDetailVO getProblemDetailVOByTriggerId(String triggerId) throws ServiceException;

}
