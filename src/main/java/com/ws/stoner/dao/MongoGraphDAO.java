package com.ws.stoner.dao;

import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.DO.mongo.GraphType;

/**
 * Created by zkf on 2017/7/18.
 */
public interface MongoGraphDAO {

    /**
     * 根据 valueType，查询 graphType
     */
    GraphType findGraphTypeByValueType(String valueType) throws DAOException;
}
