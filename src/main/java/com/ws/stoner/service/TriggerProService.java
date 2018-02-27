package com.ws.stoner.service;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefTriggerProDTO;

import java.util.List;

/**
 * Created by zhongkf on 2018/2/6
 */
public interface TriggerProService {

    /**
     * 根据 hostIds 获取 相关的所有触发器原型
     * @param hostIds
     * @return
     * @throws ServiceException
     */
    List<BriefTriggerProDTO> getTriProDTOSByHostIds(List<String> hostIds) throws ServiceException;
}
