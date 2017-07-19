package com.ws.stoner.service;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.view.HostDetailItemVO;

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
    List<String> getGraphTypeByValueTypeFromMongo(String valueType) throws ServiceException;

    /**
     * 根据 hostIds 查询出指定设备的 图形监控项 graph item
     * @param hostId
     * @return
     * @throws ServiceException
     */
    List<HostDetailItemVO> getGraphItemByHostId(String hostId) throws ServiceException;

}
