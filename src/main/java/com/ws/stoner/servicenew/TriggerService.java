package com.ws.stoner.servicenew;

import com.ws.stoner.model.dto.BriefTriggerDTO;

/**
 * Created by zhongkf on 2017/12/25
 */
public interface TriggerService {

    /**
     * 获取单个触发器对象 DTO
     * @param triggerId
     * @return
     */
    BriefTriggerDTO getDTOByTriId(String triggerId);



    
}
