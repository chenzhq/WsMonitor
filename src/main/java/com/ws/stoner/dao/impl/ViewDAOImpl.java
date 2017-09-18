package com.ws.stoner.dao.impl;

import com.ws.stoner.dao.ViewDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.DO.mongo.carousel.CarouselType;
import com.ws.stoner.model.DO.mongo.carousel.ChartType;
import com.ws.stoner.model.DO.mongo.carousel.ViewPage;
import com.ws.stoner.model.DO.mongo.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zkf on 2017/9/12.
 */
@Repository
public class ViewDAOImpl implements ViewDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 查询所有的视图类型对象
     * @return
     * @throws DAOException
     */
    @Override
    public List<ViewType> findAllType() throws DAOException {
        return mongoTemplate.findAll(ViewType.class);
    }

    /**
     * 查询所有的轮播视图对象
     * @return
     * @throws DAOException
     */
    @Override
    public List<CarouselType> findAllCarouselType() throws DAOException {
        return mongoTemplate.findAll(CarouselType.class);
    }

    /**
     * 查询所有的 控件类型
     * @return
     * @throws DAOException
     */
    @Override
    public List<ChartType> findAllChartType() throws DAOException {
        return mongoTemplate.findAll(ChartType.class);
    }

    /**
     * 保存一个视图类型
     * @param viewType
     * @throws DAOException
     */
    @Override
    public void save(ViewType viewType) throws DAOException {
        mongoTemplate.save(viewType);
    }

    /**
     * 保存 CarouselType
     * @param carouselType
     * @throws DAOException
     */
    @Override
    public void saveCarourselType(CarouselType carouselType) throws DAOException {
        mongoTemplate.save(carouselType);
    }

    /**
     * 保存 ChartType
     * @param chartType
     * @throws DAOException
     */
    @Override
    public void saveChartType(ChartType chartType) throws DAOException {
        mongoTemplate.save(chartType);
    }

    /**
     * 保存一个视图配置 通用
     * @param graphView
     * @throws DAOException
     */
    @Override
    public <T> void save(T graphView) throws DAOException {
        mongoTemplate.save(graphView);
    }

    /**
     * 根据视图类型 查询所有的视图配置
     * @param viewType
     * @return
     * @throws DAOException
     */
    @Override
    public List<GraphView> findViewsByType(String viewType) throws DAOException {
        Query query=new Query(Criteria
                .where("type").is(viewType));
        List<GraphView> graphViews = mongoTemplate.find(query, GraphView.class);
        return graphViews;
    }

    /**
     * 根据视图名称 查询 graphView 通用
     * @param name
     * @return
     * @throws DAOException
     */
    @Override
    public <T> T getViewByName(String name,String type,Class<T> clazz) throws DAOException {
        Query query=new Query(Criteria
                .where("name").is(name)
                .and("type").is(type));
        T graphView = mongoTemplate.findOne(query, clazz);
        return graphView;
    }

    /**
     * 状态视图 修改保存 stateview
     * @param stateview
     * @throws DAOException
     */
    @Override
    public void updateStateView(StateView stateview, String oldName) throws DAOException {
        Query query = Query.query(Criteria
                .where("name").is(oldName)
                .and("type").is(stateview.getType()));
        Update update = Update
                .update("name",stateview.getName())
                .set("hostids",stateview.getHostIds());
        mongoTemplate.updateFirst(query, update, StateView.class);
    }

    /**
     * 问题视图 修改保存 problemsview
     * @param problemsview
     * @throws DAOException
     */
    @Override
    public void updateProblemsView(ProblemsView problemsview, String oldName) throws DAOException {
        Query query = Query.query(Criteria
                .where("name").is(oldName)
                .and("type").is(problemsview.getType()));
        Update update = Update.update("name",problemsview.getName())
                .set("hostids",problemsview.getHostIds())
                .set("max_num",problemsview.getMaxNum());
        mongoTemplate.updateFirst(query, update, ProblemsView.class);
    }

    /**
     * 视图 删除 graphview
     * @param name,type
     * @throws DAOException
     */
    @Override
    public void deleteGraphView(String name,String type) throws DAOException {
        Query query=new Query(Criteria
                .where("name").is(name)
                .and("type").is(type));
        mongoTemplate.remove(query,GraphView.class);
    }

    /**
     * 获取指定类型 type 的第一个 graphview
     * @param clazz 指定对象类型接收
     * @return
     * @throws DAOException
     */
    @Override
    public <T> T getFirstGraphView(String type,Class<T> clazz) throws DAOException {
        Query query=new Query(Criteria.where("type").is(type));
        return mongoTemplate.findOne(query,clazz);
    }

    /**
     * 保存一个轮播页面
     * @param viewPage
     * @throws DAOException
     */
    @Override
    public void saveViewPage(ViewPage viewPage) throws DAOException {
        mongoTemplate.save(viewPage);
    }

    /**
     * 获取所有的 viewPage
     * @return
     * @throws DAOException
     */
    @Override
    public List<ViewPage> findAllViewPage() throws DAOException {
        return mongoTemplate.findAll(ViewPage.class);
    }

    /**
     * 根据 groupname 指定展示组 获取 所有的展示页
     * @param groupName
     * @return
     * @throws DAOException
     */
    @Override
    public List<ViewPage> getAllPageByGroupName(String groupName) throws DAOException {
        Query query=new Query(Criteria.where("group_name").is(groupName));
        return mongoTemplate.find(query,ViewPage.class);
    }

    /**
     * 查询 指定的 pagename 的 viewpage
     * @param pageName
     * @return
     * @throws DAOException
     */
    @Override
    public ViewPage getPageByPageName(String pageName) throws DAOException {
        Query query=new Query(Criteria.where("page_name").is(pageName));
        return mongoTemplate.findOne(query,ViewPage.class);
    }

    /**
     * 删除 viewpage
     * @param pageName
     * @throws DAOException
     */
    @Override
    public void deletePageView(String pageName) throws DAOException {
        Query query=new Query(Criteria
                .where("page_name").is(pageName));
        mongoTemplate.remove(query,ViewPage.class);
    }


}
