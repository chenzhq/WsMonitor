package com.ws.stoner.service;

import com.ws.bix4j.access.valuemap.ValuemapGetRequest;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefValuemapDTO;

import java.util.List;

/**
 * Created by zkf on 2017/8/2.
 */
public interface ValuemapService {

/*
 *基础方法
 */

    /**
     * List valuemap list.
     *
     * @return the list
     * @throws ServiceException the auth expire exception
     */
    List<BriefValuemapDTO> listValuemap(ValuemapGetRequest request) throws ServiceException;

    /**
     * 根据 valuemapIds 查询出指定的 值映射  BriefValuemapDTO
     * @param valuemapIds
     * @return
     * @throws ServiceException
     */
    List<BriefValuemapDTO> getValuemapByIds(List<String> valuemapIds) throws ServiceException;

    /**
     *
     * 根据 valuemapid 将 value 转换成 newvalue
     * @param valuemapId 值映射 id
     * @param value  映射 key
     * @return
     * @throws ServiceException
     */
    String getNewValueById(String valuemapId,String value) throws ServiceException;

    /**
     * 根据 接收到的 valueInfo 转换成可显示的数值
     * @param valuemapId
     * @param valueInfo
     * @param unitsInfo
     * @return
     */
    String getTransformValue(String valuemapId,String valueInfo , String unitsInfo) throws ServiceException;

}
