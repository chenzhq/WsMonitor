package com.ws.stoner.dao.impl;

import com.ws.stoner.dao.MongoItemDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.DO.mongo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by pc on 2017/7/13.
 */
@Repository
public class MongoItemDAOImpl implements MongoItemDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 根据hostId，查询 items
     */
    @Override
    public List<Item> findItemByHostId(String hostId) throws DAOException {
        Query query=new Query(Criteria.where("hostId").is(hostId));
        List<Item> item = mongoTemplate.find(query, Item.class);
        return item;
    }
}
