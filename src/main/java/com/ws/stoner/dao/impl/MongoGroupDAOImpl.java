package com.ws.stoner.dao.impl;

import com.ws.stoner.dao.MongoGroupDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.DO.mongo.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by pc on 2017/6/30.
 */
@Repository
public class MongoGroupDAOImpl implements MongoGroupDAO {

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
    //更新name
    @Override
    public void updateGroupFirst(Group group) throws DAOException {
        String name = group.getName();
        mongoTemplate.updateFirst(new Query(Criteria.where("id").is(group.getId())),Update.update("name",name), Group.class);
    }

    /**
     * 批量更新组 group   测试未实现批量插入 还在研究中
     * @param groups
     * @throws DAOException
     */
    @Override
    public void bathUpdateGroups(List<Group> groups) throws DAOException {
        BulkOperations ops = mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED,"group");
        for(Group group : groups) {
            ops.updateOne(Query.query(Criteria.where("id").is(group.getId())),Update.update("host_children",group.getHostChildren()));
        }
        ops.execute();
    }


}
