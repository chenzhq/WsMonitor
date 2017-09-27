package com.ws.stoner.dao;

import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.DO.mongo.carousel.CarouselType;
import com.ws.stoner.model.DO.mongo.carousel.ChartType;
import com.ws.stoner.model.DO.mongo.carousel.ViewPage;
import com.ws.stoner.model.DO.mongo.view.*;

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
     * 查询所有的轮播视图对象
     * @return
     * @throws DAOException
     */
    List<CarouselType> findAllCarouselType() throws DAOException;

    /**
     * 查询所有的 控件类型
     * @return
     * @throws DAOException
     */
    List<ChartType> findAllChartType() throws DAOException;

    /**
     * 保存一个视图类型
     * @param viewType
     * @throws DAOException
     */
    void save(ViewType viewType) throws DAOException;

    /**
     * 保存一个视图配置 通用
     * @param graphView
     * @throws DAOException
     */
    <T> void save(T graphView) throws DAOException;

    /**
     * 保存 CarouselType
     * @param carouselType
     * @throws DAOException
     */
    void saveCarourselType(CarouselType carouselType) throws DAOException;

    /**
     * 保存 ChartType
     * @param chartType
     * @throws DAOException
     */
    void saveChartType(ChartType chartType) throws DAOException;

    /**
     * 根据视图类型 查询所有的视图配置 用父类就满足了，主要用于下拉框列表
     * @param viewType
     * @return
     * @throws DAOException
     */
    List<GraphView> findViewsByType(String viewType) throws DAOException;


    /**
     * 根据视图名称 查询 graphView 通用
     * @param name
     * @return
     * @throws DAOException
     */
    <T> T getViewByName(String name,String type,Class<T> clazz) throws DAOException;

    /**
     * 状态视图 修改保存 stateview
     * @param stateview
     * @throws DAOException
     */
    void updateStateView(StateView stateview, String oldName) throws DAOException;

    /**
     * 问题视图 修改保存 problemsview
     * @param problemsview
     * @throws DAOException
     */
    void updateProblemsView(ProblemsView problemsview, String oldName) throws DAOException;

    /**
     * 状态视图 删除 stateview 通用
     * @param name,type
     * @throws DAOException
     */
    void deleteGraphView(String name,String type) throws DAOException;


    /**
     * 获取指定类型 type 的第一个 graphview
     * @return
     * @throws DAOException
     */
    <T> T getFirstGraphView(String type,Class<T> clazz) throws DAOException;


    /**
     * 保存一个轮播页面
     * @param viewPage
     * @throws DAOException
     */
    void saveViewPage(ViewPage viewPage) throws DAOException;

    /**
     * 获取所有的 viewPage
     * @return
     * @throws DAOException
     */
    List<ViewPage> findAllViewPage() throws DAOException;

    /**
     * 根据 groupname 指定展示组 获取 所有的展示页
     * @param groupName
     * @return
     * @throws DAOException
     */
    List<ViewPage> getAllPageByGroupName(String groupName) throws DAOException;

    /**
     * 查询 指定的 pagename 的 viewpage
     * @param pageName groupName
     * @return
     * @throws DAOException
     */
    ViewPage getPageByPageName(String pageName,String groupName) throws DAOException;

    /**
     * 删除 viewpage groupName
     * @param pageName
     * @throws DAOException
     */
    void deletePageView(String pageName,String groupName) throws DAOException;

    /**
     *  修改页面名称
     * @param oldName
     * @param newName
     * @param groupName
     * @throws DAOException
     */
    void updateViewPageByName(String oldName,String newName,String groupName) throws DAOException;

    /**
     * 更新 viewpage 的layout 和 config
     * @param viewPage
     * @throws DAOException
     */
    void updatePageDataByViewPage(ViewPage viewPage) throws DAOException;


}
