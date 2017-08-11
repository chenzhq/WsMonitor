package com.ws.stoner.dao.impl;

import com.ws.stoner.dao.MongoPlatformGraphDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.DO.mongo.PlatformGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
        Query query = Query.query(Criteria.where("host_id").in(hostIds));
        List<PlatformGraph> platformGraphs = mongoTemplate.find(query, PlatformGraph.class);
        return platformGraphs;
    }
}
