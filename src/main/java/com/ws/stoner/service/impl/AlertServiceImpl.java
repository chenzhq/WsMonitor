package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.alert.AlertGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefAlertDTO;
import com.ws.stoner.model.dto.UserInfoDTO;
import com.ws.stoner.model.view.ProblemAlertVO;
import com.ws.stoner.service.AlertService;
import com.ws.stoner.service.EventService;
import com.ws.stoner.utils.AlertStatusConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    @Autowired
    private EventService eventService;

    private List<BriefAlertDTO> listAlert(AlertGetRequest request) throws ServiceException {
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
                .setSelectUsers(UserInfoDTO.PROPERTY_NAMES)
                .setOutput(BriefAlertDTO.PROPERTY_NAMES);
        List<BriefAlertDTO> alertDTOS = listAlert(alertGetRequest);
        return alertDTOS;
    }

    /**
     * 根据 eventId   查询 AlertDTO ,组装 ProblemAlertVO，问题列表中 告警记录 pop
     * @param eventId
     * @return
     * @throws ServiceException
     */
    @Override
    public List<ProblemAlertVO> getAlertVOByEventId(String eventId) throws ServiceException {
        List<String> eventIds = new ArrayList<>();
        eventIds.add(eventId);
        List<BriefAlertDTO> problemAlertDTOS = getAlertDTOByEventIds(eventIds);
        List<BriefAlertDTO> recoveryAlertDTOS ;
        String recoveryEventId = eventService.getEventByEventId(eventIds).get(0).getrEventid();
        List<ProblemAlertVO> alertVOS = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        for(BriefAlertDTO alertDTO : problemAlertDTOS) {
            List<UserInfoDTO> userInfoDTOS = alertDTO.getUsers();
            String alias = "";
            if(userInfoDTOS.size() != 0) {
                alias = userInfoDTOS.get(0).getName();
            }
            ProblemAlertVO alertVO = new ProblemAlertVO(
                    alertDTO.getAlertId(),
                    alertDTO.getEscStep(),
                    alertDTO.getClock().format(formatter),
                    AlertStatusConverter.getDiscriptionByStatus(alertDTO.getStatus()),
                    alertDTO.getRetries(),
                    alias,
                    alertDTO.getSendto(),
                    false
            );
            alertVOS.add(alertVO);
        }
        if(recoveryEventId != null) {
            List<String> recoveryEventIds = new ArrayList<>();
            recoveryEventIds.add(recoveryEventId);
            recoveryAlertDTOS = getAlertDTOByEventIds(recoveryEventIds);
            for(BriefAlertDTO recoveryAlertDTO : recoveryAlertDTOS) {
                List<UserInfoDTO> userInfoDTOS = recoveryAlertDTO.getUsers();
                String alias = "";
                if(userInfoDTOS.size() != 0) {
                    alias = userInfoDTOS.get(0).getName();
                }
                ProblemAlertVO alertVO = new ProblemAlertVO(
                        recoveryAlertDTO.getAlertId(),
                        recoveryAlertDTO.getEscStep(),
                        recoveryAlertDTO.getClock().format(formatter),
                        AlertStatusConverter.getDiscriptionByStatus(recoveryAlertDTO.getStatus()),
                        recoveryAlertDTO.getRetries(),
                        alias,
                        recoveryAlertDTO.getSendto(),
                        true
                );
                alertVOS.add(alertVO);
            }
        }
        return alertVOS;
    }
}
