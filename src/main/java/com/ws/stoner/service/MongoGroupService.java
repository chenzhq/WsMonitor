package com.ws.stoner.service;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.mongoDO.MongoGroupDO;

import java.util.List;

/**
 * Created by pc on 2017/6/28.
 */
public interface MongoGroupService {

    List<MongoGroupDO> findAll() throws ServiceException;

    void save() throws ServiceException;

}
