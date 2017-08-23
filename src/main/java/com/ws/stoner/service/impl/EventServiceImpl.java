package com.ws.stoner.service.impl;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.event.EventGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.exception.AuthExpireException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefAcknowledgeDTO;
import com.ws.stoner.model.dto.BriefEventDTO;
import com.ws.stoner.model.view.ProblemAcknowledgeVO;
import com.ws.stoner.service.EventService;
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
public class EventServiceImpl implements EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);
    @Autowired
    private ZApi zApi;

    @Override
    public List<BriefEventDTO> listEvent(EventGetRequest request) throws ServiceException {
        List<BriefEventDTO> events;
        try {
            events = zApi.Event().get(request,BriefEventDTO.class);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询事件event错误！{}", e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return events;
    }

    /**
     * 根据 eventIds 查询所有指定的事件 BriefEventDTO
     * @param eventIds
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BriefEventDTO> getEventByEventId(List<String> eventIds) throws ServiceException {
        EventGetRequest eventGetRequest = new EventGetRequest();
        eventGetRequest.getParams()
                .setEventIds(eventIds)
                .setSelectAcknowledges(BriefAcknowledgeDTO.PROPERTY_NAMES);
        return listEvent(eventGetRequest);
    }

    /**
     * 根据指定的 eventid 获取 问题列表中 确认记录 pop
     * @param eventId
     * @return
     * @throws ServiceException
     */
    @Override
    public List<ProblemAcknowledgeVO> getAcknowledgeVOSByEventId(String eventId) throws ServiceException {
        List<String> eventIds = new ArrayList<>();
        eventIds.add(eventId);
        List<BriefEventDTO> eventDTOS = getEventByEventId(eventIds);
        List<ProblemAcknowledgeVO> acknowledgeVOS = new ArrayList<>();
        if(eventDTOS.size() != 0) {
            List<BriefAcknowledgeDTO> acknowledgeDTOS = eventDTOS.get(0).getAcknowledges();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
            for(BriefAcknowledgeDTO acknowledgeDTO : acknowledgeDTOS) {
                ProblemAcknowledgeVO acknowledgeVO = new ProblemAcknowledgeVO(
                        acknowledgeDTO.getAcknowledgeId(),
                        acknowledgeDTO.getClock().format(formatter),
                        acknowledgeDTO.getAlias(),
                        acknowledgeDTO.getMessage(),
                        "0".equals(acknowledgeDTO.getAction()) ? "否" : "是"
                );
                acknowledgeVOS.add(acknowledgeVO);

            }
        }
        return acknowledgeVOS;
    }
}
