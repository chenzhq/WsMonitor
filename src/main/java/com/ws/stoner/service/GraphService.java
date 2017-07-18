package com.ws.stoner.service;

import com.ws.stoner.exception.ServiceException;

import java.util.List;

/**
 * Created by pc on 2017/7/18.
 */
public interface GraphService {

    /**
     * 根据 valueType 查询出对应支持的图形插件名称
     * @param valueType
     * @return
     * @throws ServiceException
     */
    List<String> getGraphTypeByValueType(String valueType) throws ServiceException;

}
