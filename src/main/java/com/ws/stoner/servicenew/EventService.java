package com.ws.stoner.servicenew;

import com.ws.stoner.model.dto.BriefEventDTO;

/**
 * Created by zhongkf on 2018/1/3
 */
public interface EventService {

    /**
     * 获取单个 eventDTO
     * @param eventId
     * @return
     */
    BriefEventDTO getDTOByEventId(String eventId);


}
