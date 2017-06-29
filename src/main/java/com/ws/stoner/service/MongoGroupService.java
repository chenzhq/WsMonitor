package com.ws.stoner.service;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.view.MongoGroupVO;

import java.util.List;

/**
 * Created by pc on 2017/6/28.
 */
public interface MongoGroupService {


    /**
     * 组装自定义分组 mongoGroup list
     * @return
     * @throws ServiceException
     */
    List<MongoGroupVO> listMongoGroup() throws ServiceException;

    /**
     * 递归实现组装自定义分组 mongoGroup list
     * @return
     * @throws ServiceException
     */
    List<MongoGroupVO> listMongo() throws ServiceException;

    List<MongoGroupVO> getGroupTree(String name,List<MongoGroupVO> mongoGroupVOS);


}
