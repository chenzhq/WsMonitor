package com.ws.stoner.dao;

import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.DO.DOMongo.Group;

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

}
