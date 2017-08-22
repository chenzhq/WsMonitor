package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.alert.AlertGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefAlertDTO;
import com.ws.stoner.service.AlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import static com.ws.bix4j.exception.ZApiExceptionEnum.NO_AUTH_ASSIGN;
import static com.ws.bix4j.exception.ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE;

/**
 * Created by zkf on 2017/8/22.
 */
@Service
public class AlertServiceImpl implements AlertService {

    private static final Logger logger = LoggerFactory.getLogger(AlertServiceImpl.class);
    @Autowired
    private ZApi zApi;

    @Override
    public List<BriefAlertDTO> listAlert(AlertGetRequest request) throws ServiceException {
        List<BriefAlertDTO> alerts;
        try {
            alerts = zApi.Alert().get(request,BriefAlertDTO.class);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询告警 alert 错误！{}", e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return alerts;
    }

    /**
     * 根据 eventIds 查询所属的 告警 AlertDTO
     * @param eventIds
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefAlertDTO> getAlertDTOByEventIds(List<String> eventIds) throws ServiceException {
        AlertGetRequest alertGetRequest = new AlertGetRequest();
        alertGetRequest.getParams()
                .setEventIds(eventIds)
                .setOutput(BriefAlertDTO.PROPERTY_NAMES);
        List<BriefAlertDTO> alertDTOS = listAlert(alertGetRequest);
        return alertDTOS;
    }
}
