package com.ws.stoner.dao.impl;

import com.ws.stoner.dao.MongoPlatformGraphDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.DO.mongo.PlatformGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zkf on 2017/8/11.
 */
@Repository
public class MongoPlatformGraphDAOImpl implements MongoPlatformGraphDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(PlatformGraph platformGraph) throws DAOException {
        mongoTemplate.save(platformGraph);
    }

    /**
     * 根据 hostIds，查询 PlatformGraph list
     */
    @Override
    public List<PlatformGraph> findGraphsByHostIds(List<String> hostIds) throws DAOException {
        Query query = Query.query(Criteria.where("hostId").in(hostIds));
        List<PlatformGraph> platformGraphs = mongoTemplate.find(query, PlatformGraph.class);
        return platformGraphs;
    }

    /**
     * 根据 platformId 查询platformGraph list
     * @param platformId
     * @return
     * @throws DAOException
     */
    @Override
    public List<PlatformGraph> findGraphsByPlatformId(String platformId) throws DAOException {
        Query query = Query.query(Criteria.where("platformId").is(platformId));
        List<PlatformGraph> platformGraphs = mongoTemplate.find(query, PlatformGraph.class);
        return platformGraphs;
    }

    /**
     * 根据 itemId 查询 PlatformGraph
     * @param itemId
     * @return
     * @throws DAOException
     */
    @Override
    public PlatformGraph findGraphByItemId(String itemId) throws DAOException {
        Query query=new Query(Criteria.where("itemId").is(itemId));
        PlatformGraph platformGraph = mongoTemplate.findOne(query, PlatformGraph.class);
        return platformGraph;
    }

    /**
     * 更新保存 platformGraph
     */
    @Override
    public void update(PlatformGraph platformGraph) throws DAOException {
        Query query = Query.query(Criteria.where("itemId").is(platformGraph.getItemId()));
        Update update = Update
                .update("graphName",platformGraph.getGraphName())
                .set("graphType",platformGraph.getGraphType());
        mongoTemplate.updateFirst(query, update, PlatformGraph.class);
    }

    /**
     * 根据 itemId 删除指定 platformGraph
     * @param itemId
     * @throws DAOException
     */
    @Override
    public void delete(String itemId) throws DAOException {
        Query query=new Query(Criteria.where("itemId").is(itemId));
        mongoTemplate.remove(query,PlatformGraph.class);
    }
}
