package com.ws.stoner.service;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefItemProtoDTO;

import java.util.List;

/**
 * Created by zhongkf on 2018/1/30
 */
public interface ItemProtoService {

    /**
     * 根据 hostIds 查询监控项原型 DTOS
     * @param hostIds
     * @return
     * @throws ServiceException
     */
    List<BriefItemProtoDTO> getItemProtoDTOSByHostIds(List<String> hostIds) throws ServiceException;
}
