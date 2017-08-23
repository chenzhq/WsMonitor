package com.ws.stoner.service;

import com.ws.bix4j.access.alert.AlertGetRequest;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefAlertDTO;
import com.ws.stoner.model.view.ProblemAlertVO;


import java.util.List;

/**
 * Created by zkf on 2017/8/22.
 */
public interface AlertService {

    /**
     * List alert list.
     *
     * @return the list
     * @throws ServiceException the auth expire exception
     */
    List<BriefAlertDTO> listAlert(AlertGetRequest request) throws ServiceException;

    /**
     * 根据 eventIds 查询所属的 告警 AlertDTO
     * @param eventIds
     * @return
     * @throws ServiceException
     */
    List<BriefAlertDTO> getAlertDTOByEventIds(List<String> eventIds) throws ServiceException;

    /**
     * 根据 eventId   查询 AlertDTO ,组装 ProblemAlertVO，问题列表中 告警记录 pop
     * @param eventId
     * @return
     * @throws ServiceException
     */
    List<ProblemAlertVO> getAlertVOByEventId(String eventId) throws ServiceException;
}
