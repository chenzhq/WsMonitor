package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.TriggerPro.TriggerProGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefTriggerProDTO;
import com.ws.stoner.service.TriggerProService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ws.bix4j.exception.ZApiExceptionEnum.NO_AUTH_ASSIGN;
import static com.ws.bix4j.exception.ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE;

/**
 * Created by zhongkf on 2018/2/6
 */
@Service
public class TriggerProServiceImpl implements TriggerProService {

    private static final Logger logger = LoggerFactory.getLogger(TriggerProServiceImpl.class);
    @Autowired
    private ZApi zApi;

    private List<BriefTriggerProDTO> listTriggerPro(TriggerProGetRequest request) throws ServiceException {
        List<BriefTriggerProDTO> triggers;
        try {
            triggers = zApi.TriggerPro().get(request,BriefTriggerProDTO.class);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询触发器原型 {}", e.getMessage());
            throw new ServiceException(e.getMessage());
        }

        return triggers;
    }

    /**
     * 根据 hostIds 获取 相关的所有触发器原型
     * @param hostIds
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefTriggerProDTO> getTriProDTOSByHostIds(List<String> hostIds) throws ServiceException {
        TriggerProGetRequest triggerProGetRequest = new TriggerProGetRequest();
        triggerProGetRequest.getParams()
                .setHostIds(hostIds)
                .setExpandExpression(true)
                .setOutput(BriefTriggerProDTO.PROPERTY_NAMES);
        List<BriefTriggerProDTO> triggerProDTOS = listTriggerPro(triggerProGetRequest);
        return triggerProDTOS;
    }
}
