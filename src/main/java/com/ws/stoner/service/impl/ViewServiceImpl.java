package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApiParameter;
import com.ws.stoner.constant.CarouselTypeEnum;
import com.ws.stoner.constant.ChartTypeEnum;
import com.ws.stoner.constant.StatusEnum;
import com.ws.stoner.constant.ViewTypeEnum;
import com.ws.stoner.dao.ViewDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.DO.mongo.carousel.ConfigData;
import com.ws.stoner.model.DO.mongo.carousel.ViewPage;
import com.ws.stoner.model.DO.mongo.view.*;
import com.ws.stoner.model.dto.BriefAlertDTO;
import com.ws.stoner.model.dto.BriefHistoryDTO;
import com.ws.stoner.model.dto.BriefItemDTO;
import com.ws.stoner.model.dto.BriefTriggerDTO;
import com.ws.stoner.model.view.carousel.*;
import com.ws.stoner.model.view.itemvalue.ItemTimeData;
import com.ws.stoner.model.view.problem.AlertBriefVO;
import com.ws.stoner.model.view.problem.ProblemListVO;
import com.ws.stoner.model.view.statepie.StateNumVO;
import com.ws.stoner.model.view.statepie.StateViewVO;
import com.ws.stoner.service.*;
import com.ws.stoner.utils.StatusConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
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
    private ItemService itemService;

    @Autowired
    private HistoryService historyService;

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
     * 根据 视图名称 获取 指定视图类型对象
     * @param name
     * @param type
     * @param clazz
     * @param <T>
     * @return
     * @throws ServiceException
     */
    @Override
    public <T> T getGraphViewByName(String name, String type, Class<T> clazz) throws ServiceException {
        T graphView = null;
        try {
            graphView = viewDAO.getViewByName(name,type,clazz);
        } catch (DAOException e) {
            logger.error("根据名称 查询单个 graphView 错误！{}", e.getMessage());
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
    public StateViewVO getStateViewByName(String name,String type) throws ServiceException {
        StateView stateView = getGraphViewByName(name,type,StateView.class);
        if(stateView == null) {
            return null;
        }
        List<String> hostIds = stateView.getHostIds();
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
    public List<ProblemListVO> getProblemViewByName(String name,String type) throws ServiceException {
        ProblemsView problemsView = getGraphViewByName(name,type,ProblemsView.class);
        List<String> hostIds = problemsView.getHostIds();
        Integer limit = problemsView.getMaxNum() == null ? 0 : problemsView.getMaxNum();
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
                    AlertBriefVO alertBriefVO = AlertBriefVO.transformByAlertDTOS(alertDTOS);
                    problemListVO.setAlertNum(alertBriefVO.getAlertNum());
                    problemListVO.setAlertState(alertBriefVO.getAlertState());
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
     * @param graphView 所有的视图类型对象
     * @return
     * @throws ServiceException
     */
    @Override
    public <T> boolean saveGraphView(T graphView) throws ServiceException {
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
     * 状态视图 修改 stateView
     * @param stateView
     * @param oldName
     * @return
     * @throws ServiceException
     */
    @Override
    public boolean updateStateView(StateView stateView,String oldName) throws ServiceException {
        try {
            viewDAO.updateStateView(stateView,oldName);
        } catch (DAOException e) {
            logger.error("修改 stateView 错误！{}", e.getMessage());
            new ServiceException(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 问题视图 修改 problemsView
     * @param problemsView
     * @param oldName
     * @return
     * @throws ServiceException
     */
    @Override
    public boolean updateProblemsView(ProblemsView problemsView,String oldName) throws ServiceException {
        try {
            viewDAO.updateProblemsView(problemsView,oldName);
        } catch (DAOException e) {
            logger.error("修改 problemsView 错误！{}", e.getMessage());
            new ServiceException(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 删除 graphview
     * @param name,type
     * @return
     * @throws ServiceException
     */
    @Override
    public boolean deleteGraphView(String name,String type) throws ServiceException {
        try {
            viewDAO.deleteGraphView(name,type);
        } catch (DAOException e) {
            logger.error("ViewDAO 错误！{}", e.getMessage());
            new ServiceException(e.getMessage());
            return false;
        }
      return true;
    }

    /**
     * 获取所有 展示组名称 去重
     * @return
     * @throws ServiceException
     */
    @Override
    public List<String> getAllGroupNames() throws ServiceException {
        List<ViewPage> viewPages = null;
        List<String> groupNames = new ArrayList<>();
        try {
            viewPages = viewDAO.findAllViewPage();
        } catch (DAOException e) {
            logger.error("获取所有 viewPage 错误！{}", e.getMessage());
            new ServiceException(e.getMessage());
        }
        if(viewPages != null) {
            for(ViewPage viewPage : viewPages) {
                if(!groupNames.contains(viewPage.getGroupName())) {
                    groupNames.add(viewPage.getGroupName());
                }
            }
        }
        return groupNames;
    }

    /**
     * 根据 指定组名称 获取 所有的页面名称
     * @param groupName
     * @return
     * @throws ServiceException
     */
    @Override
    public List<String> getPageNamesByGroupNames(String groupName) throws ServiceException {
        List<ViewPage> viewPages = null;
        List<String> pageNames = new ArrayList<>();
        try {
            viewPages = viewDAO.getAllPageByGroupName(groupName);
        } catch (DAOException e) {
            logger.error("获取指定组的所有 viewPage 错误！{}", e.getMessage());
            new ServiceException(e.getMessage());
        }
        if(viewPages != null) {
            for(ViewPage viewPage :viewPages) {
                pageNames.add(viewPage.getPageName());
            }
        }
        return pageNames;
    }

    /**
     * 根据 指定页面名称 获取 展示页对象 pageVO
     * @param pageName
     * @return
     * @throws ServiceException
     */
    @Override
    public PageVO getPageVOByPageName(String pageName) throws ServiceException {
        ViewPage viewPage = null;
        try {
            viewPage = viewDAO.getPageByPageName(pageName);
        } catch (DAOException e) {
            logger.error("获取指定name的 viewPage 错误！{}", e.getMessage());
            new ServiceException(e.getMessage());
        }
        if(viewPage == null) {
            return null;
        }
        PageVO pageVO = new PageVO(
                viewPage.getPageName(),
                viewPage.getLayoutDataList(),
                viewPage.getConfigDataList()
        );
        List<ConfigData> configData = viewPage.getConfigDataList();
        List<BlockVO> blockVOS = new ArrayList<>();
        for(ConfigData config : configData) {
            if(CarouselTypeEnum.VIEW.type.equals(config.getBlockType())) {
                //view
                if(ViewTypeEnum.STATEPIE.type.equals(config.getGraphType())) {
                    //statepie
                    StateViewVO stateViewVO = getStateViewByName(config.getContents(),config.getGraphType());
                    blockVOS.add(stateViewVO);
                }else if(ViewTypeEnum.PROBLEMS.type.equals(config.getGraphType())) {
                    //problems
                    List<ProblemListVO> problemListVO = getProblemViewByName(config.getContents(),config.getGraphType());
                    ProblemsVO problemsVO = new ProblemsVO(problemListVO);
                    blockVOS.add(problemsVO);
                }else if(ViewTypeEnum.APPLETREE.type.equals(config.getGraphType())) {
                    //appletree
                    //待完成
                }else {

                }
            }else if(CarouselTypeEnum.GRAPH.type.equals(config.getBlockType())) {
                //graph
                ItemTimeData timeData = getItemTimeDataByItemId(config.getContents());//contents为itemId
                blockVOS.add(timeData);
            }else if(CarouselTypeEnum.CHART.type.equals(config.getBlockType())) {
                //chart
                if(ChartTypeEnum.CLOCK.type.equals(config.getGraphType())) {
                    //clock 待完成
                    ClockVO clockVO = new ClockVO();
                    blockVOS.add(clockVO);
                }else if(ChartTypeEnum.TABLE.type.equals(config.getGraphType())) {
                    //table 待完成
                    TableVO tableVO = new TableVO();
                    blockVOS.add(tableVO);
                }else {

                }
            }else {

            }
        }
        //给页面填充动态数据
        pageVO.setBlockData(blockVOS);
        return pageVO;

    }

    /**
     * 根据itemid  获取item图形数据
     * @param itemId
     * @return
     * @throws ServiceException
     */
    @Override
    public ItemTimeData getItemTimeDataByItemId(String itemId) throws ServiceException {
        //获取briefItemDTO
        List<String> itemIds = new ArrayList<>();
        itemIds.add(itemId);
        BriefItemDTO itemDTO = itemService.getItemsByItemIds(itemIds).get(0);
        List<BriefHistoryDTO> historyDTOS = historyService.getHistoryByItemId(itemId,itemDTO.getValueType(),1);
        //降序改升序
        Collections.reverse(historyDTOS);
        //获取对象
        ItemTimeData timeData = ItemTimeData.transformByHistoryDTOS(historyDTOS,itemDTO.getUnits());
        return timeData;
    }

}
