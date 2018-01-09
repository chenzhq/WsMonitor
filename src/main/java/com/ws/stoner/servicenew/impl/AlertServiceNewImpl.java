package com.ws.stoner.servicenew.impl;

import com.ws.bix4j.ZApiParameter;
import com.ws.stoner.constant.BaseConsts;
import com.ws.stoner.daonew.AlertDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefAlertDTO;
import com.ws.stoner.model.dto.BriefEventDTO;
import com.ws.stoner.model.dto.UserInfoDTO;
import com.ws.stoner.model.vo.alert.AlertStatVO;
import com.ws.stoner.model.vo.alert.AlertVO;
import com.ws.stoner.servicenew.AlertService;
import com.ws.stoner.utils.StatusConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongkf on 2017/12/28
 */
@Service
public class AlertServiceNewImpl implements AlertService {

    private static final Logger logger = LoggerFactory.getLogger(AlertServiceNewImpl.class);

    @Autowired
    private AlertDAO alertDAO;

    @Override
    public List<AlertStatVO> getAlertVOByEventIds(List<String> eventIds) throws ServiceException {
        List<AlertStatVO> alertStatVOS = new ArrayList<>();
        List<BriefAlertDTO> allAlertDTOS = null;
        try {
            allAlertDTOS = alertDAO.getAlertDTOSByEventIds(eventIds);
        } catch (DAOException e) {
            logger.error("调用 alertDAO getAlertDTOSByEventIds 出错",e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        for(String eventId : eventIds) {
            AlertStatVO alertStatVO = new AlertStatVO();
            alertStatVO.setEventId(eventId);
            //默认值
            String alertStat = "none";
            int alertNum = 0;
            for(BriefAlertDTO alertDTO : allAlertDTOS) {
                if(eventId.equals(alertDTO.getEventId())) {
                    alertNum++;
                    if (alertDTO.getStatus().equals(ZApiParameter.ALERT_MESSAGE_STATUS.MESSAGE_SENDING.value)) {
                        alertStat = "sending";
                        break;
                    } else if (alertDTO.getStatus().equals(ZApiParameter.ALERT_MESSAGE_STATUS.MESSAGE_FAILED.value)) {
                        alertStat = "failed";
                        break;
                    } else {
                        alertStat = "success";
                    }
                }

            }
            alertStatVO.setAlertNum(alertNum);
            alertStatVO.setAlertState(alertStat);
            alertStatVOS.add(alertStatVO);
        }

        return alertStatVOS;
    }


    /**
     * 获取指定问题 eventId 的 问题和恢复告警
     * @param eventDTO
     * @return
     */
    @Override
    public List<AlertVO> getAlertVOSByEventDTO(BriefEventDTO eventDTO) {
        List<String> eventIds = new ArrayList<>();
        eventIds.add(eventDTO.getEventId());
        List<BriefAlertDTO> proAlertDTOS = null;
        List<BriefAlertDTO> recAlertDTOS = null;
        try {
            proAlertDTOS = alertDAO.getAlertDTOSByEventIds(eventIds);
            if(eventDTO.getrEventid() != null && eventDTO.getrEventid() != "" ) {
                List<String> rEventIds = new ArrayList<>();
                rEventIds.add(eventDTO.getEventId());
                recAlertDTOS = alertDAO.getAlertDTOSByEventIds(rEventIds);
            }
        } catch (DAOException e) {
            logger.error("调用 alertDAO getAlertDTOSByEventIds 错误",e.getMessage());
            new ServiceException(e.getMessage());
        }
        List<AlertVO> alertVOS = new ArrayList<>();
        if(proAlertDTOS != null) {
            for(BriefAlertDTO alertDTO : proAlertDTOS) {
                List<UserInfoDTO> userInfoDTOS = alertDTO.getUsers();
                String userName = "";
                for(UserInfoDTO user : userInfoDTOS) {
                    if(userInfoDTOS.get(0) == user) {
                        userName = userInfoDTOS.get(0).getName();
                    }else {
                        userName += "," + user.getName();
                    }
                }
                String mediatypeName = alertDTO.getMediatypes().get(0).getDescription();
                AlertVO alertVO = new AlertVO(
                        alertDTO.getEscStep(),
                        userName,
                        alertDTO.getClock().format(BaseConsts.TIME_FORMATTER),
                        alertDTO.getSendto(),
                        mediatypeName,
                        alertDTO.getSubject(),
                        alertDTO.getMessage(),
                        StatusConverter.transAlertState(alertDTO.getStatus()),
                        alertDTO.getRetries(),
                        false
                );
                alertVOS.add(alertVO);
            }
        }
        if(recAlertDTOS != null) {
            for(BriefAlertDTO alertDTO : recAlertDTOS) {
                List<UserInfoDTO> userInfoDTOS = alertDTO.getUsers();
                String userName = "";
                for(UserInfoDTO user : userInfoDTOS) {
                    if(userInfoDTOS.get(0) == user) {
                        userName = userInfoDTOS.get(0).getName();
                    }else {
                        userName += "," + user.getName();
                    }
                }
                String mediatypeName = alertDTO.getMediatypes().get(0).getDescription();
                AlertVO alertVO = new AlertVO(
                        alertDTO.getEscStep(),
                        userName,
                        alertDTO.getClock().format(BaseConsts.TIME_FORMATTER),
                        alertDTO.getSendto(),
                        mediatypeName,
                        alertDTO.getSubject(),
                        alertDTO.getMessage(),
                        StatusConverter.transAlertState(alertDTO.getStatus()),
                        alertDTO.getRetries(),
                        true
                );
                alertVOS.add(alertVO);
            }
        }
        return alertVOS;
    }
}
