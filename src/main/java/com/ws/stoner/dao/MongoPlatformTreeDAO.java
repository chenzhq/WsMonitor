package com.ws.stoner.dao;

import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.DO.mongo.platform.PlatformTree;
import com.ws.stoner.model.DO.mongo.platform.PlatformTreeManager;

import java.util.List;

/**
 * Created by pc on 2017/8/10.
 */
public interface MongoPlatformTreeDAO {

    //更新树
    void save(PlatformTreeManager platformTreeManager) throws DAOException;

    //保存树
    void save(PlatformTree platformTree) throws DAOException;
    /**
     * 根据 id 查找  用于显示
     * @return
     * @throws DAOException
     */
    PlatformTree findById(String id) throws DAOException;

    List<PlatformTree> findAll() throws DAOException;

    /**
     * 用于 获取修改的业务树
     * @param id
     * @return
     * @throws DAOException
     */
    PlatformTreeManager findUseManagerById(String id) throws DAOException;

}
