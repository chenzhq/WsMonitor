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

}
