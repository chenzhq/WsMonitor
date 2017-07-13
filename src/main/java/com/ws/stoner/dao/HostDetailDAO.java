package com.ws.stoner.dao;

import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.DO.mongo.Item;

import java.util.List;

/**
 * Created by pc on 2017/7/13.
 */
public interface HostDetailDAO {

    /**
     * 根据hostId，查询 items
     */
    List<Item> findItemByHostId(String hostId) throws DAOException;
}
