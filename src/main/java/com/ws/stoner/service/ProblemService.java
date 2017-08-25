package com.ws.stoner.service;

import com.ws.bix4j.access.problem.ProblemGetRequest;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefProblemDTO;
import com.ws.stoner.model.view.ProblemListVO;

import java.util.List;

/**
 * Created by zkf on 2017/8/25.
 */
public interface ProblemService {

/*
 *基础方法
 */
    /**
     * List problem list.
     *
     * @return the list
     * @throws ServiceException the auth expire exception
     */
    List<BriefProblemDTO> listProblem(ProblemGetRequest request) throws ServiceException;

/*
 *zabbix 方法
 */

    /**
     * 获取最近的 问题和恢复 problem
     * List<String> triggerIds 过滤依赖关系
     * @return
     * @throws ServiceException
     */
    List<BriefProblemDTO> getRecentProblem(List<String> triggerIds) throws ServiceException;

/*
 *业务方法
 */
    /**
     * 获取 30分钟内 的已恢复的问题 和所有未恢复的问题
     * @return
     * @throws ServiceException
     */
    List<ProblemListVO> getLastProblems() throws ServiceException;

}
