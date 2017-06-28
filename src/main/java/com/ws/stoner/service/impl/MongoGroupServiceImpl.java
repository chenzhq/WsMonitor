package com.ws.stoner.service.impl;


import com.ws.stoner.dao.MongoGroupRepository;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.mongoDO.MongoGroupDO;
import com.ws.stoner.service.MongoGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pc on 2017/6/28.
 */
@Service
public class MongoGroupServiceImpl implements MongoGroupService {

    @Autowired
    private MongoGroupRepository mongoGroupRepository;

    @Override
    public List<MongoGroupDO> findAll() throws ServiceException {
       return mongoGroupRepository.findAll();
    }

    @Override
    public void save() throws ServiceException {


    }
}
