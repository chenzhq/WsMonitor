package com.ws.stoner.dao;

import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.DO.mongo.PlatformGraph;

import java.util.List;

/**
 * Created by zkf on 2017/8/11.
 */
public interface MongoPlatformGraphDAO {

    /**
     * 插入保存
     */
    void save(PlatformGraph platformGraph) throws DAOException;

    /**
     * 根据 hostIds，查询 PlatformGraph list
     */
    List<PlatformGraph> findGraphsByHostIds(List<String> hostIds) throws DAOException;

    /**
     * 根据 platformId 查询 platformGraph list
     * @param platformId
     * @return
     * @throws DAOException
     */
    List<PlatformGraph> findGraphsByPlatformId(String platformId) throws DAOException;

    /**
     * 根据 itemId 查询 PlatformGraph
     * @param itemId
     * @return
     * @throws DAOException
     */
    PlatformGraph findGraphByItemId(String itemId) throws DAOException;

    /**
     * 更新保存 platformGraph
     */
    void update(PlatformGraph platformGraph) throws DAOException;

    /**
     * 根据 itemId 删除指定 platformGraph
     * @param itemId
     * @throws DAOException
     */
    void delete(String itemId) throws DAOException;

}
