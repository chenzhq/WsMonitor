package com.ws.stoner.dao;

import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.DO.mongo.Group;

import java.util.List;

/**
 * Created by pc on 2017/6/30.
 */
public interface OverviewDAO {

    /**
     * 查询group 中 cid最大的 group
     * @return
     * @throws DAOException
     */
    Group findMaxGroupCId() throws DAOException;

    /**
     * 根据cid，查询 group
     */
    Group findGroupByCId(String cId) throws DAOException;

    /**
     * 根据 group update name
     * @param group
     * @throws DAOException
     */
    void updateGroupFirst(Group group) throws DAOException;

    /**
     * 批量更新组 group
     * @param groups
     * @throws DAOException
     */
    void bathUpdateGroups(List<Group>groups) throws DAOException;


}
