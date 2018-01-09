package com.ws.stoner.daonew.ipml;

import com.ws.stoner.daonew.TreeDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.mongo.hosttree.HostNode;
import com.ws.stoner.model.mongo.plattree.PlatNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/18
 */
@Repository
public class TreeDAOImpl implements TreeDAO {

    private static final Logger logger = LoggerFactory.getLogger(TreeDAOImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;
/*
设备树
 */
    @Override
    public List<HostNode> listHostTreeFromMongo() {
        return mongoTemplate.findAll(HostNode.class);
    }

    @Override
    public HostNode getHostNodeByNodeId(String nodeId) throws DAOException {
        Query query=new Query(Criteria.where("nodeId").is(nodeId));
        HostNode hostNode = mongoTemplate.findOne(query,HostNode.class);
        return hostNode;
    }

    @Override
    public List<HostNode> getHostNodesByNodeIds(List<String> nodeIds) throws DAOException {
        Query query=new Query(Criteria.where("nodeId").in(nodeIds));
        List<HostNode> hostNodes = mongoTemplate.find(query,HostNode.class);
        return hostNodes;
    }

    @Override
    public void insertHostNode(HostNode hostNode) throws DAOException {
        mongoTemplate.insert(hostNode);
    }

    @Override
    public void saveAllHostNodes(List<HostNode> allHostNodes) throws DAOException {
        mongoTemplate.insertAll(allHostNodes);
    }

    @Override
    public void deleteAllHostNodes() throws DAOException {
        Query query=new Query();
        mongoTemplate.remove(query,HostNode.class);
    }


/*
业务树
 */
    @Override
    public List<PlatNode> listPlatTreeFromMongo() throws DAOException {
        return mongoTemplate.findAll(PlatNode.class);
    }

    @Override
    public PlatNode getPlatNodeByNodeId(String nodeId) throws DAOException {
        Query query=new Query(Criteria.where("nodeId").is(nodeId));
        PlatNode platNode = mongoTemplate.findOne(query,PlatNode.class);
        return platNode;
    }

    @Override
    public List<PlatNode> getPlatNodesByNodeIds(List<String> nodeIds) throws DAOException {
        Query query = new Query(Criteria.where("nodeId").in(nodeIds));
        List<PlatNode> platNodes = mongoTemplate.find(query,PlatNode.class);
        return platNodes;
    }

    @Override
    public List<PlatNode> getPlatNodesByParentId(String parentId) throws DAOException {
        Query query=new Query(Criteria.where("parentId").is(parentId));
        List<PlatNode> platNodes = mongoTemplate.find(query,PlatNode.class);
        return platNodes;
    }

    @Override
    public List<PlatNode> getPlatNodesByParentIds(List<String> parentIds) throws DAOException {
        Query query=new Query(Criteria.where("parentId").in(parentIds));
        List<PlatNode> platNodes = mongoTemplate.find(query,PlatNode.class);
        return platNodes;
    }

    @Override
    public void insertPlatNode(PlatNode platNode) throws DAOException {
        mongoTemplate.insert(platNode);
    }

    @Override
    public void saveAllPlatNodes(List<PlatNode> allPlatNodes) throws DAOException {
        mongoTemplate.insertAll(allPlatNodes);
    }

    @Override
    public void deleteAllPlatNodes() throws DAOException {
        Query query=new Query();
        mongoTemplate.remove(query,PlatNode.class);
    }


}
