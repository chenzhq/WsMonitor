package com.ws.stoner.service;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.DO.mongo.GraphView;
import com.ws.stoner.model.DO.mongo.ViewType;
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
     * 根据 视图名称 获取指定 视图配置信息
     * @param name
     * @return
     * @throws ServiceException
     */
    GraphView getGraphViewByName(String name) throws ServiceException;

    /**
     * 根据视图名称 获取 状态统计 视图信息
     * @param name
     * @return
     * @throws ServiceException
     */
    StateViewVO getStateViewByName(String name) throws ServiceException;

    /**
     * 根据视图名称 获取 问题视图 信息
     * @param name
     * @return
     * @throws ServiceException
     */
    List<ProblemListVO> getProblemViewByName(String name) throws ServiceException;

    /**
     * 保存 graphView
     * @param graphView
     * @return
     * @throws ServiceException
     */
    boolean saveGraphView(GraphView graphView) throws ServiceException;

    /**
     * 修改 graphview
     * @param graphView
     * @param oldName
     * @return
     * @throws ServiceException
     */
    boolean updateGraphView(GraphView graphView,String oldName) throws ServiceException;

    /**
     * 删除 graphview
     * @param name
     * @return
     * @throws ServiceException
     */
    boolean deleteGraphView(String name) throws ServiceException;

    /**
     * 获取第一个graphview  返回值可以为空
     * @return
     * @throws ServiceException
     */
    GraphView getFirstGraphView(String type) throws ServiceException;

}
