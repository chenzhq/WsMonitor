package com.ws.stoner.dao.impl;

import com.ws.stoner.dao.OverviewDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.DO.mongo.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


/**
 * Created by pc on 2017/6/30.
 */
@Repository
public class OverviewDAOImpl implements OverviewDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Group findMaxGroupCId() throws DAOException {
        Query query = new Query();
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"cId")));
       return this.mongoTemplate.findOne(query,Group.class,"group");

    }

    /**
     * 根据cid，查询 group
     * @return
     * @throws DAOException
     */
    @Override
    public Group findGroupByCId(String cId) throws DAOException {
        Query query=new Query(Criteria.where("cid").is(cId));
        Group group = mongoTemplate.findOne(query, Group.class);
        return group;
    }




}
