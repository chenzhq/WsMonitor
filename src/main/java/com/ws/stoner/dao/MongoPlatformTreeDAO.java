package com.ws.stoner.dao;

import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.DO.mongo.PlatformTree;

import java.util.List;

/**
 * Created by pc on 2017/8/10.
 */
public interface MongoPlatformTreeDAO {

    void save(PlatformTree platformTree) throws DAOException;

    /**
     * 根据 id 查找
     * @return
     * @throws DAOException
     */
    PlatformTree findById(String id) throws DAOException;

    List<PlatformTree> findAll() throws DAOException;

}
