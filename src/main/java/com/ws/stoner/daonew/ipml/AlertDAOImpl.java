package com.ws.stoner.daonew.ipml;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.alert.AlertGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.daonew.AlertDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.dto.BriefAlertDTO;
import com.ws.stoner.model.dto.BriefMediatypeDTO;
import com.ws.stoner.model.dto.UserInfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.ws.bix4j.exception.ZApiExceptionEnum.NO_AUTH_ASSIGN;
import static com.ws.bix4j.exception.ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE;

/**
 * Created by zhongkf on 2017/12/28
 */
@Repository

public class AlertDAOImpl implements AlertDAO {
    private static final Logger logger = LoggerFactory.getLogger(AlertDAOImpl.class);
    @Autowired
    private ZApi zApi;
    private List<BriefAlertDTO> listAlert(AlertGetRequest request) throws DAOException {
        List<BriefAlertDTO> alerts;
        try {
            alerts = zApi.Alert().get(request,BriefAlertDTO.class);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
               // throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询告警 alert 错误！{}", e.getMessage());
            throw new DAOException(e.getMessage());
        }
        return alerts;
    }


    @Override
    public List<BriefAlertDTO> getAlertDTOSByEventIds(List<String> eventIds) throws DAOException {
        AlertGetRequest alertGetRequest = new AlertGetRequest();
        List<String> sort = new ArrayList<>();
        sort.add("eventid");
        alertGetRequest.getParams()
                .setEventIds(eventIds)
                .setSelectUsers(UserInfoDTO.PROPERTY_NAMES)
                .setSelectMediatypes(BriefMediatypeDTO.PROPERTY_NAMES)
                .setOutput(BriefAlertDTO.PROPERTY_NAMES)
                .setSortField(sort);
        List<BriefAlertDTO> alertDTOS = listAlert(alertGetRequest);
        return alertDTOS;
    }
}
