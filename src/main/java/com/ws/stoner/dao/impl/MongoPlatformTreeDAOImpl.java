package com.ws.stoner.dao.impl;

import com.ws.stoner.dao.MongoPlatformTreeDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.DO.mongo.Item;
import com.ws.stoner.model.DO.mongo.PlatformTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pc on 2017/8/10.
 */
@Repository
public class MongoPlatformTreeDAOImpl implements MongoPlatformTreeDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(PlatformTree platformTree) throws DAOException{
        mongoTemplate.save(platformTree);
    }

    /**
     * 根据 id 查找
     * @return
     * @throws DAOException
     */
    @Override
    public PlatformTree findById(String id) throws DAOException {

        return  mongoTemplate.findById(id,PlatformTree.class);

    }

    @Override
    public List<PlatformTree> findAll() throws DAOException {
        return mongoTemplate.findAll(PlatformTree.class);

    }
}
