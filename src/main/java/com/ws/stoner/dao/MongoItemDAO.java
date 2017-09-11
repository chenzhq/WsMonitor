package com.ws.stoner.dao;

import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.DO.mongo.Item;

import java.util.List;

/**
 * Created by pc on 2017/7/13.
 */
public interface MongoItemDAO {

    /**
     * 根据hostId，查询 items
     */
    List<Item> findItemByHostId(String hostId) throws DAOException;

    /**
     * 根据 itemId ，查询 item
     * @param itemId
     * @return
     * @throws DAOException
     */
    Item getItemByItemId(String itemId) throws DAOException;


    /**
     * 插入保存 item
     */
    void save(Item item) throws DAOException;

    /**
     * 更新保存 item
     */
    void update(Item item) throws DAOException;

    /**
     * 根据 itemId 删除指定 item
     * @param itemId
     * @throws DAOException
     */
    void delete(String itemId) throws DAOException;


}
