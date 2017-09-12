package com.ws.stoner.dao.impl;

import com.ws.stoner.dao.ViewDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.DO.mongo.GraphView;
import com.ws.stoner.model.DO.mongo.ViewType;
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
     * 保存一个视图类型
     * @param viewType
     * @throws DAOException
     */
    @Override
    public void save(ViewType viewType) throws DAOException {
        mongoTemplate.save(viewType);
    }

    /**
     * 保存一个视图配置
     * @param GraphView
     * @throws DAOException
     */
    @Override
    public void save(GraphView GraphView) throws DAOException {
        mongoTemplate.save(GraphView);
    }

    /**
     * 根据视图类型 查询所有的视图配置
     * @param viewType
     * @return
     * @throws DAOException
     */
    @Override
    public List<GraphView> findViewsByType(String viewType) throws DAOException {
        Query query=new Query(Criteria.where("type").is(viewType));
        List<GraphView> graphViews = mongoTemplate.find(query, GraphView.class);
        return graphViews;
    }

    /**
     * 根据视图名称 查询 graphView
     * @param name
     * @return
     * @throws DAOException
     */
    @Override
    public GraphView findViewByName(String name) throws DAOException {
        Query query=new Query(Criteria.where("name").is(name));
        GraphView graphView = mongoTemplate.findOne(query, GraphView.class);
        return graphView;
    }

    /**
     * 修改保存 graphview
     * @param graphView
     * @throws DAOException
     */
    @Override
    public void updateGraphView(GraphView graphView,String oldName) throws DAOException {
        Query query = Query.query(Criteria.where("name").is(oldName));
        Update update = Update.update("name",graphView.getName())
                .set("hostids",graphView.getHostIds());
        mongoTemplate.updateFirst(query, update, GraphView.class);
    }

    /**
     * 删除 graphview
     * @param name
     * @throws DAOException
     */
    @Override
    public void deleteGraphView(String name) throws DAOException {
        Query query=new Query(Criteria.where("name").is(name));
        mongoTemplate.remove(query,GraphView.class);
    }

    /**
     * 获取指定类型 type 的第一个 graphview
     * @return
     * @throws DAOException
     */
    @Override
    public GraphView getFirstGraphView(String type) throws DAOException {
        Query query=new Query(Criteria.where("type").is(type));
        return mongoTemplate.findOne(query,GraphView.class);
    }


}
