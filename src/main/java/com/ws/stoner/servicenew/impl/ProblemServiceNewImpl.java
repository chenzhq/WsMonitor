package com.ws.stoner.servicenew.impl;

import com.ws.bix4j.ZApiParameter;
import com.ws.stoner.constant.BaseConsts;
import com.ws.stoner.daonew.TriggerDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefEventDTO;
import com.ws.stoner.model.dto.BriefHostDTO;
import com.ws.stoner.model.dto.BriefItemDTO;
import com.ws.stoner.model.dto.BriefTriggerDTO;
import com.ws.stoner.model.vo.HostVO;
import com.ws.stoner.model.vo.alert.AlertStatVO;
import com.ws.stoner.model.vo.item.ItemVO;
import com.ws.stoner.model.vo.problem.ProblemVO;
import com.ws.stoner.servicenew.ProblemService;
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
public class ProblemServiceNewImpl implements ProblemService {
    private static final Logger logger = LoggerFactory.getLogger(ProblemServiceNewImpl.class);

    @Autowired
    private TriggerDAO triggerDAO;

    @Override
    public ProblemVO getProblemVOByTriggerDTO(BriefTriggerDTO problemDTO,List<AlertStatVO> alertStatVOS) throws ServiceException {
        BriefEventDTO lastEvent = problemDTO.getLastEvent();
        ProblemVO problemVO = new ProblemVO();
        problemVO.setEventId(lastEvent.getEventId());
        problemVO.setTriggerId(problemDTO.getTriggerId());
        problemVO.setDescription(problemDTO.getName());
        problemVO.setState("problem");
        problemVO.setTime(lastEvent.getClock().format(BaseConsts.TIME_FORMATTER));
        problemVO.setPriority(StatusConverter.getStatusTextByTriggerPriority(problemDTO.getPriority()));
        if(problemDTO.getHosts().size() > 0) {
            BriefHostDTO hostDTO = problemDTO.getHosts().get(0);
            problemVO.setHost(
                    new HostVO(
                            hostDTO.getHostId(),
                            hostDTO.getName(),
                            null,
                            StatusConverter.getTextStatusTransform(hostDTO.getCustomState(),hostDTO.getCustomAvailableState()),
                            null
                    )
            );
        }
        if(problemDTO.getItems().size() > 0) {
            BriefItemDTO itemDTO = problemDTO.getItems().get(0);
            problemVO.setItem(
                    new ItemVO(
                            itemDTO.getItemId(),
                            itemDTO.getName(),
                            StatusConverter.statusTransForItem(itemDTO.getCustomState(),itemDTO.getWeight()),
                            itemDTO.getWeight()!=0
                    )
            );
        }
        problemVO.setRecoveryEventId("");
        problemVO.setRecoveryTime("");
        problemVO.setAcknowledged(lastEvent.getAcknowledged() == ZApiParameter.ACKNOWLEDGE_ACTION.ACKNOWLEDGED.value);
        for(AlertStatVO alertStatVO : alertStatVOS) {
            if(lastEvent.getEventId().equals(alertStatVO.getEventId())) {
                problemVO.setAlertNum(alertStatVO.getAlertNum());
                problemVO.setAlertStatus(alertStatVO.getAlertState());
            }
        }

        return problemVO;

    }

    @Override
    public List<BriefTriggerDTO> listProblemVOS() throws ServiceException {
        List<BriefTriggerDTO> problemTris = null;
        try {
            problemTris = triggerDAO.listProblemTriggers();
        } catch (DAOException e) {
            logger.error("调用 triggerDAO listProblemTriggers 错误",e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return problemTris;
    }

    @Override
    public List<BriefTriggerDTO> getProblemVOSByHostId(String hostId) throws ServiceException {
        List<String> hostIds = new ArrayList<>();
        hostIds.add(hostId);
        List<BriefTriggerDTO> problemTris = null;
        try {
            problemTris = triggerDAO.getProblemTriggersByHostIds(hostIds);
        } catch (DAOException e) {
            logger.error("调用 triggerDAO getProblemTriggersByHostIds 错误",e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return problemTris;
    }

    @Override
    public List<BriefTriggerDTO> getProblemVOSByPlatId(String platformId) throws ServiceException {
        List<String> platformIds = new ArrayList<>();
        platformIds.add(platformId);
        List<BriefTriggerDTO> problemTris = null;
        try {
            problemTris = triggerDAO.getProblemTriggersByPlatIds(platformIds);
        } catch (DAOException e) {
            logger.error("调用 triggerDAO getProblemTriggersByPlatIds 错误",e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return problemTris;
    }

}
