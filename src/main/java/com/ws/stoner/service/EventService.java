package com.ws.stoner.service;

import com.ws.bix4j.access.event.EventGetRequest;
import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.BriefEventDTO;

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


}
