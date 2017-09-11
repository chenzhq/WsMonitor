package com.ws.stoner.dao.impl;

import com.ws.stoner.dao.MongoGraphDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.DO.mongo.GraphType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by pc on 2017/7/18.
 *
 */
@Repository
public class MongoGraphDAOImpl implements MongoGraphDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 根据 valueType，查询 graphType
     */
    @Override
    public GraphType findGraphTypeByValueType(String valueType) throws DAOException {
        Query query=new Query(Criteria.where("value_type").is(valueType));
        GraphType graphType = mongoTemplate.findOne(query, GraphType.class);
        return graphType;
    }
}
