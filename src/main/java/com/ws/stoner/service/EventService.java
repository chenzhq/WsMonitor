package com.ws.stoner.service;

import com.ws.bix4j.access.event.EventGetRequest;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefEventDTO;
import com.ws.stoner.model.view.ProblemAcknowledgeVO;

import java.util.List;

/**
 * Created by pc on 2017/8/22.
 */
public interface EventService {

    /**
     * List event list.
     *
     * @return the list
     * @throws ServiceException the auth expire exception
     */
    List<BriefEventDTO> listEvent(EventGetRequest request) throws ServiceException;

    /**
     * 根据 eventIds 查询所有指定的事件 BriefEventDTO
     * @param eventIds
     * @return
     * @throws ServiceException
     */
    List<BriefEventDTO> getEventByEventId(List<String> eventIds) throws ServiceException;

    /**
     * 根据指定的 eventid 获取 问题列表中 确认记录 pop
     * @param eventId
     * @return
     * @throws ServiceException
     */
    List<ProblemAcknowledgeVO> getAcknowledgeVOSByEventId(String eventId) throws ServiceException;


}
