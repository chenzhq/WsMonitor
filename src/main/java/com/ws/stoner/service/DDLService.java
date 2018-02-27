package com.ws.stoner.service;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefDDLDTO;

import java.util.List;

/**
 * Created by zhongkf on 2018/1/30
 */
public interface DDLService {


    /**
     * 根据 ruleIds 获取 DDLDTOS select itemProtoDTO
     * @param ruleIds
     * @return
     * @throws ServiceException
     */
    List<BriefDDLDTO> getDDLDTOSByRuleIds(List<String> ruleIds) throws ServiceException;

}
