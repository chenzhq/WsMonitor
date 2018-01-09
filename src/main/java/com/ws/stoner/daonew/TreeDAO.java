package com.ws.stoner.daonew;

import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.mongo.hosttree.HostNode;
import com.ws.stoner.model.mongo.plattree.PlatNode;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/18
 */
public interface TreeDAO {
    /*
设备树
 */

    /**
     * 获取所有的设备树 节点
     * @return
     * @throws DAOException
     */
    List<HostNode> listHostTreeFromMongo() throws DAOException;

    /**
     * 根据 nodeId 获取 hostNode 树节点
     * @param nodeId
     * @return
     * @throws DAOException
     */
    HostNode getHostNodeByNodeId(String nodeId) throws DAOException;

    /**
     * 根据 nodeIds 获取 hostNodes 树节点
     * @param nodeIds
     * @return
     * @throws DAOException
     */
    List<HostNode> getHostNodesByNodeIds(List<String> nodeIds) throws DAOException;

    /**
     * 插入一个 hostnode
     * @param hostNode
     * @return
     * @throws DAOException
     */
    void insertHostNode(HostNode hostNode) throws DAOException;

    /**
     * 批量保存 allHostNodes
     * @param allHostNodes
     * @throws DAOException
     */
    void saveAllHostNodes(List<HostNode> allHostNodes) throws DAOException;

    /**
     * 删除所有 node
     * @throws DAOException
     */
    void deleteAllHostNodes() throws DAOException;

/*
业务树
 */
    /**
     * 获取所有的业务树 节点
     * @return
     * @throws DAOException
     */
    List<PlatNode> listPlatTreeFromMongo() throws DAOException;

    /**
     * 根据 nodeId 获取 hostNode 树节点
     * @param nodeId
     * @return
     * @throws DAOException
     */
    PlatNode getPlatNodeByNodeId(String nodeId) throws DAOException;

    /**
     * 根据 nodeIds 获取 hostNodes 树节点
     * @param nodeIds
     * @return
     * @throws DAOException
     */
    List<PlatNode> getPlatNodesByNodeIds(List<String> nodeIds) throws DAOException;

    /**
     * 获取相同 parentId 的业务节点
     * @param parentId
     * @return
     * @throws DAOException
     */
    List<PlatNode> getPlatNodesByParentId(String parentId) throws DAOException;

    /**
     * 批量获取相同 parentIds 的业务节点
     * @param parentIds
     * @return
     * @throws DAOException
     */
    List<PlatNode> getPlatNodesByParentIds(List<String> parentIds) throws DAOException;

    /**
     * 插入一个 hostnode
     * @param platNode
     * @return
     * @throws DAOException
     */
    void insertPlatNode(PlatNode platNode) throws DAOException;

    /**
     * 批量保存 allHostNodes
     * @param allPlatNodes
     * @throws DAOException
     */
    void saveAllPlatNodes(List<PlatNode> allPlatNodes) throws DAOException;

    /**
     * 删除所有 node
     * @throws DAOException
     */
    void deleteAllPlatNodes() throws DAOException;

}
