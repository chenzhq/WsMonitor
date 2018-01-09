package com.ws.stoner.daonew.ipml;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.access.event.EventGetRequest;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.exception.ZApiExceptionEnum;
import com.ws.stoner.daonew.EventDAO;
import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.dto.BriefAcknowledgeDTO;
import com.ws.stoner.model.dto.BriefEventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ws.bix4j.exception.ZApiExceptionEnum.NO_AUTH_ASSIGN;
import static com.ws.bix4j.exception.ZApiExceptionEnum.ZBX_API_AUTH_EXPIRE;

/**
 * Created by zhongkf on 2018/1/3
 */
@Repository
public class EventDAOImpl implements EventDAO {

    private static final Logger logger = LoggerFactory.getLogger(EventDAOImpl.class);
    @Autowired
    private ZApi zApi;

    private List<BriefEventDTO> listEvent(EventGetRequest request) throws DAOException {
        List<BriefEventDTO> events;
        try {
            events = zApi.Event().get(request,BriefEventDTO.class);
        } catch (ZApiException e) {
            ZApiExceptionEnum zeEnum = e.getCode();
            if (zeEnum.equals(ZBX_API_AUTH_EXPIRE) || zeEnum.equals(NO_AUTH_ASSIGN)) {
                //throw new AuthExpireException(e.getMessage());
            }
            logger.error("查询事件event错误！{}", e.getMessage());
            throw new DAOException(e.getMessage());
        }
        return events;
    }

    /**
     * 根据 eventIds 查询所有指定的事件 BriefEventDTO
     * @param eventIds
     * @return
     * @throws DAOException
     */
    @Override
    public List<BriefEventDTO> getEventsByEventIds(List<String> eventIds) throws DAOException {
        EventGetRequest eventGetRequest = new EventGetRequest();
        eventGetRequest.getParams()
                .setEventIds(eventIds)
                .setSelectAcknowledges(BriefAcknowledgeDTO.PROPERTY_NAMES)
                .setOutput(BriefEventDTO.PROPERTY_NAMES);
        return listEvent(eventGetRequest);
    }
}
