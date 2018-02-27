package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.LLD.DiscoGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefDDLDTO;
import com.ws.stoner.model.dto.BriefItemProtoDTO;
import com.ws.stoner.model.dto.BriefTriggerProDTO;
import com.ws.stoner.service.DDLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ws.bix4j.exception.ZApiExceptionEnum.NO_AUTH_ASSIGN;
import static com.ws.bix4j.exception.ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE;

/**
 * Created by zhongkf on 2018/1/30
 */
@Service
public class DDLServiceImpl implements DDLService {

    private static final Logger logger = LoggerFactory.getLogger(DDLServiceImpl.class);
    @Autowired
    private ZApi zApi;

    private List<BriefDDLDTO> listDDL(DiscoGetRequest request) throws ServiceException {
        List<BriefDDLDTO> ddlDTOS;
        try {
            ddlDTOS = zApi.DiscoveryRule().get(request,BriefDDLDTO.class);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询事件event错误！{}", e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return ddlDTOS;
    }

    /**
     * 根据 ruleIds 获取 DDLDTOS select itemProtoDTO
     * @param ruleIds
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefDDLDTO> getDDLDTOSByRuleIds(List<String> ruleIds) throws ServiceException {
        DiscoGetRequest ruleRequest = new DiscoGetRequest();
        ruleRequest.getParams()
                .setItemIds(ruleIds)
                .setSelectItems(BriefItemProtoDTO.PROPERTY_NAMES)
                .setSelectTriggers(BriefTriggerProDTO.PROPERTY_NAMES)
                .setOutput(BriefDDLDTO.PROPERTY_NAMES);
        List<BriefDDLDTO> ddldtos = listDDL(ruleRequest);
        return ddldtos;
    }
}
