package com.ws.stoner.service;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.DO.mongo.view.GraphView;
import com.ws.stoner.model.DO.mongo.view.ProblemsView;
import com.ws.stoner.model.DO.mongo.view.StateView;
import com.ws.stoner.model.DO.mongo.view.ViewType;
import com.ws.stoner.model.view.carousel.PageVO;
import com.ws.stoner.model.view.problem.ProblemListVO;
import com.ws.stoner.model.view.statepie.StateViewVO;

import java.util.List;

/**
 * Created by zkf on 2017/9/12.
 */
public interface ViewService {


    /**
     * 获取所有视图类型列表
     * @return
     * @throws ServiceException
     */
    List<ViewType> listViewType() throws ServiceException;

    /**
     * 根据 视图类型 获取指定所有的 视图配置信息
     * @param viewType
     * @return
     * @throws ServiceException
     */
    List<GraphView> listGraphViewsByType(String viewType) throws ServiceException;

    /**
     * 根据 视图名称 获取 指定视图类型对象
     * @param name
     * @param type
     * @param clazz
     * @param <T>
     * @return
     * @throws ServiceException
     */
    <T> T getGraphViewByName(String name,String type,Class<T> clazz) throws ServiceException;

    /**
     * 根据视图名称 获取 状态统计 视图信息
     * @param name
     * @return
     * @throws ServiceException
     */
    StateViewVO getStateViewByName(String name,String type) throws ServiceException;

    /**
     * 根据视图名称 获取 问题视图 信息
     * @param name
     * @return
     * @throws ServiceException
     */
    List<ProblemListVO> getProblemViewByName(String name,String type) throws ServiceException;

    /**
     *  视图保存 graphView  通用
     * @param graphView 所有的视图类型对象
     * @return
     * @throws ServiceException
     */
    <T> boolean saveGraphView(T graphView) throws ServiceException;

    /**
     * 状态视图 修改 stateView
     * @param stateView
     * @param oldName
     * @return
     * @throws ServiceException
     */
    boolean updateStateView(StateView stateView,String oldName) throws ServiceException;

    /**
     * 问题视图 修改 problemsView
     * @param problemsView
     * @param oldName
     * @return
     * @throws ServiceException
     */
    boolean updateProblemsView(ProblemsView problemsView,String oldName) throws ServiceException;
    /**
     * 删除 graphview 返回的是同一类型的第一个视图对象
     * @param name,type,clazz 指定返回的视图类型对象
     * @return
     * @throws ServiceException
     */
    <T> T deleteGraphView(String name,String type,Class<T> clazz) throws ServiceException;

    /**
     * 获取所有 展示组名称 去重
     * @return
     * @throws ServiceException
     */
    List<String> getAllGroupNames() throws ServiceException;

    /**
     * 根据 指定组名称 获取 所有的页面名称
     * @param groupName
     * @return
     * @throws ServiceException
     */
    List<String> getPageNamesByGroupNames(String groupName) throws ServiceException;

    /**
     * 根据 指定页面名称 获取 展示页对象 pageVO
     * @param pageName
     * @return
     * @throws ServiceException
     */
    PageVO getPageVOByPageName(String pageName) throws ServiceException;
}
