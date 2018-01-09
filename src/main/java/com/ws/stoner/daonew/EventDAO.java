package com.ws.stoner.daonew;

import com.ws.stoner.exception.DAOException;
import com.ws.stoner.model.dto.BriefEventDTO;

import java.util.List;

/**
 * Created by zhongkf on 2018/1/3
 */
public interface EventDAO {

    /**
     * 根据 eventIds 查询所有指定的事件 BriefEventDTO
     * @param eventIds
     * @return
     * @throws DAOException
     */
    List<BriefEventDTO> getEventsByEventIds(List<String> eventIds) throws DAOException;

}
