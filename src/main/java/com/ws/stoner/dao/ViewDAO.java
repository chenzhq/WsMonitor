package com.ws.stoner.dao;

import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.DO.mongo.GraphView;
import com.ws.stoner.model.DO.mongo.ViewType;

import java.util.List;

/**
 * Created by zkf on 2017/9/12.
 */
public interface ViewDAO {

    /**
     * 查询所有的视图类型对象
     * @return
     * @throws DAOException
     */
    List<ViewType> findAllType() throws DAOException;

    /**
     * 保存一个视图类型
     * @param viewType
     * @throws DAOException
     */
    void save(ViewType viewType) throws DAOException;

    /**
     * 保存一个视图配置
     * @param graphView
     * @throws DAOException
     */
    void save(GraphView graphView) throws DAOException;

    /**
     * 根据视图类型 查询所有的视图配置
     * @param viewType
     * @return
     * @throws DAOException
     */
    List<GraphView> findViewsByType(String viewType) throws DAOException;

    /**
     * 根据视图名称 查询 graphView
     * @param name
     * @return
     * @throws DAOException
     */
    GraphView findViewByName(String name) throws DAOException;

    /**
     * 修改保存 graphview
     * @param graphView
     * @throws DAOException
     */
    void updateGraphView(GraphView graphView,String oldName) throws DAOException;

    /**
     * 删除 graphview
     * @param name
     * @throws DAOException
     */
    void deleteGraphView(String name) throws DAOException;

    /**
     * 获取指定类型 type 的第一个 graphview
     * @return
     * @throws DAOException
     */
    GraphView getFirstGraphView(String type) throws DAOException;

}
