package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApiParameter;
import com.ws.stoner.constant.StatusEnum;
import com.ws.stoner.dao.ViewDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.DO.mongo.GraphView;
import com.ws.stoner.model.DO.mongo.ViewType;
import com.ws.stoner.model.dto.BriefAlertDTO;
import com.ws.stoner.model.dto.BriefTriggerDTO;
import com.ws.stoner.model.view.problem.ProblemListVO;
import com.ws.stoner.model.view.statepie.StateNumVO;
import com.ws.stoner.model.view.statepie.StateViewVO;
import com.ws.stoner.service.*;
import com.ws.stoner.utils.AlertStatusConverter;
import com.ws.stoner.utils.StatusConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zkf on 2017/9/12.
 */
@Service
public class ViewServiceImpl implements ViewService {

    private static final Logger logger = LoggerFactory.getLogger(ViewServiceImpl.class);

    @Autowired
    private HostService hostService;

    @Autowired
    private PointSerivce pointSerivce;

    @Autowired
    private TriggerService triggerService;

    @Autowired
    private AlertService alertService;

    @Autowired
    private ViewDAO viewDAO;
    /**
     * 获取所有视图类型列表
     * @return
     * @throws ServiceException
     */
    @Override
    public List<ViewType> listViewType() throws ServiceException {
        List<ViewType> viewTypes = null;
        try {
            viewTypes = viewDAO.findAllType();
        } catch (DAOException e) {
            logger.error("查询所有 viewtype 错误！{}", e.getMessage());
            new ServiceException(e.getMessage());
        }
        return viewTypes;
    }

    /**
     * 根据 视图类型 获取指定所有的 视图配置信息
     * @param viewType
     * @return
     * @throws ServiceException
     */
    @Override
    public List<GraphView> listGraphViewsByType(String viewType) throws ServiceException {
        List<GraphView> graphViews = null;
        try {
            graphViews = viewDAO.findViewsByType(viewType);
        } catch (DAOException e) {
            logger.error("查询 graphview 错误！{}", e.getMessage());
            new ServiceException(e.getMessage());
        }
        return graphViews;
    }

    /**
     * 根据视图名称 获取 状态统计 视图信息
     * @param name
     * @return
     * @throws ServiceException
     */
    @Override
    public GraphView getGraphViewByName(String name) throws ServiceException {
        GraphView graphView = null;
        try {
            graphView = viewDAO.findViewByName(name);
        } catch (DAOException e) {
            logger.error("根据名称 查询单个 graphview 错误！{}", e.getMessage());
            new ServiceException(e.getMessage());
        }
        return graphView;
    }

    /**
     * 根据视图名称 获取 状态统计 视图信息
     * @param name
     * @return
     * @throws ServiceException
     */
    @Override
    public StateViewVO getStateViewByName(String name) throws ServiceException {
        GraphView graphView = getGraphViewByName(name);
        List<String> hostIds = graphView.getHostIds();
        //主机状态统计
        int allHostNum = hostIds.size();
        int warningHostNum = hostService.countWarningHostByHostIds(hostIds);
        int highHostNum = hostService.countHighHostByHostIds(hostIds);
        StateNumVO hostState = new StateNumVO();
        List<StateNumVO.StateNum> hostStateNums = new ArrayList<>();
        StateNumVO.StateNum warningStateNum = new StateNumVO.StateNum(StatusEnum.WARNING,warningHostNum);
        StateNumVO.StateNum highStateNum = new StateNumVO.StateNum(StatusEnum.HIGH, highHostNum);
        StateNumVO.StateNum okStateNum = new StateNumVO.StateNum(StatusEnum.OK,allHostNum - warningHostNum - highHostNum);
        hostStateNums.add(okStateNum);
        hostStateNums.add(warningStateNum);
        hostStateNums.add(highStateNum);
        hostState.setTotalNum(allHostNum).setStateNum(hostStateNums);
        //监控点状态统计
        int allPointNum = pointSerivce.countAllPointByHostIds(hostIds);
        int warningPointNum = pointSerivce.countWarningPointByHostIds(hostIds);
        int highPointNum = pointSerivce.countHighPointByHostIds(hostIds);
        StateNumVO pointState = new StateNumVO();
        List<StateNumVO.StateNum> pointStateNums = new ArrayList<>();
        StateNumVO.StateNum warningNum = new StateNumVO.StateNum(StatusEnum.WARNING,warningPointNum);
        StateNumVO.StateNum highNum = new StateNumVO.StateNum(StatusEnum.HIGH, highPointNum);
        StateNumVO.StateNum okNum = new StateNumVO.StateNum(StatusEnum.OK,allPointNum - warningPointNum - highPointNum);
        pointStateNums.add(okNum);
        pointStateNums.add(warningNum);
        pointStateNums.add(highNum);
        pointState.setTotalNum(allPointNum).setStateNum(pointStateNums);
        return new StateViewVO(hostState,pointState);
    }

    /**
     * 根据视图名称 获取 问题视图 信息
     * @param name
     * @return
     * @throws ServiceException
     */
    @Override
    public List<ProblemListVO> getProblemViewByName(String name) throws ServiceException {
        GraphView graphView = getGraphViewByName(name);
        List<String> hostIds = graphView.getHostIds();
        Integer limit = graphView.getMaxNum() == null ? 0 : graphView.getMaxNum();
        //获取问题触发器
        List<BriefTriggerDTO> triggerDTOS = triggerService.listProblemTriggers();
        List<ProblemListVO> problemListVOS = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        for(BriefTriggerDTO triggerDTO : triggerDTOS) {
            if(triggerDTO.getHosts().size() != 0 && hostIds.contains(triggerDTO.getHosts().get(0).getHostId())) {
                ProblemListVO problemListVO = new ProblemListVO();
                problemListVO.setTriggerId(triggerDTO.getTriggerId());
                problemListVO.setHostName(triggerDTO.getHosts().get(0).getName());
                if(triggerDTO.getLastEvent() != null) {
                    problemListVO.setProblemTime(triggerDTO.getLastEvent().getClock().format(formatter));
                    problemListVO.setProblemEventid(triggerDTO.getLastEvent().getEventId());
                    //durationString
                    problemListVO.setDurationString(triggerDTO.getLastEvent().getClock(), LocalDateTime.now());
                    if(triggerDTO.getLastEvent().getAcknowledged() == ZApiParameter.ACKNOWLEDGE_ACTION.ACKNOWLEDGED.value) {
                        problemListVO.setAcknowledged("是");
                    }else {
                        problemListVO.setAcknowledged("否");
                    }
                    //alertState
                    List<String> eventIds = new ArrayList<>();
                    eventIds.add(triggerDTO.getLastEvent().getEventId());
                    List<BriefAlertDTO> alertDTOS = alertService.getAlertDTOByEventIds(eventIds);
                    //问题和恢复的告警,告警数
                    Map<String,Integer> alertMap = AlertStatusConverter.getMassageByAlertStatus(alertDTOS);
                    problemListVO.setAlertNum(alertMap.entrySet().iterator().next().getValue());
                    problemListVO.setAlertState(alertMap.entrySet().iterator().next().getKey());
                }
                problemListVO.setProblemState("问题");
                problemListVO.setDescription(triggerDTO.getName());
                //PriorityState
                problemListVO.setPriorityState(StatusConverter.getStatusByTriggerPriority(triggerDTO.getPriority()));
                problemListVOS.add(problemListVO);
            }
        }
        //时间排序
        ProblemListVO.getSortListByProblemTime(problemListVOS);
        if(problemListVOS.size() > limit) {
            problemListVOS = problemListVOS.subList(0,limit);
        }
        return problemListVOS;
    }

    /**
     * 保存 graphView
     * @param graphView
     * @return
     * @throws ServiceException
     */
    @Override
    public boolean saveGraphView(GraphView graphView) throws ServiceException {
        try {
            viewDAO.save(graphView);
        } catch (DAOException e) {
            logger.error("保存 graphview 错误！{}", e.getMessage());
            new ServiceException(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 修改 graphview
     * @param graphView
     * @param oldName
     * @return
     * @throws ServiceException
     */
    @Override
    public boolean updateGraphView(GraphView graphView, String oldName) throws ServiceException {
        try {
            viewDAO.updateGraphView(graphView,oldName);
        } catch (DAOException e) {
            logger.error("修改 graphview 错误！{}", e.getMessage());
            new ServiceException(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 删除 graphview
     * @param name
     * @return
     * @throws ServiceException
     */
    @Override
    public boolean deleteGraphView(String name) throws ServiceException {
        try {
            viewDAO.deleteGraphView(name);
        } catch (DAOException e) {
            logger.error("删除 graphview 错误！{}", e.getMessage());
            new ServiceException(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 获取第一个graphview  返回值可以为空
     * @return
     * @throws ServiceException
     */
    @Override
    public GraphView getFirstGraphView(String type) throws ServiceException {
        GraphView graphView = null;
        try {
            graphView = viewDAO.getFirstGraphView(type);
        } catch (DAOException e) {
            logger.error("获取第一个 graphview 错误！{}", e.getMessage());
            new ServiceException(e.getMessage());
        }
        return graphView;
    }

}
