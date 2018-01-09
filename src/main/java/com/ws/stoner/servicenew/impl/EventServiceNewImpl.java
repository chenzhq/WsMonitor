package com.ws.stoner.servicenew.impl;

import com.ws.stoner.daonew.EventDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefEventDTO;
import com.ws.stoner.servicenew.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongkf on 2018/1/3
 */
@Service
public class EventServiceNewImpl implements EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventServiceNewImpl.class);

    @Autowired
    private EventDAO eventDAO;

    @Override
    public BriefEventDTO getDTOByEventId(String eventId) {
        List<String> eventIds = new ArrayList<>();
        BriefEventDTO proEvent = null;
        eventIds.add(eventId);
        try {
            List<BriefEventDTO> proEvents = eventDAO.getEventsByEventIds(eventIds);
            if(proEvents.size() > 0) {
                proEvent = proEvents.get(0);
            }
        } catch (DAOException e) {
            logger.error("调用 eventDAO getEventsByEventIds错误！" ,e.getMessage());
            new ServiceException(e.getMessage());
        }
        return proEvent;
    }


}
