package com.ws.stoner.model.view;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by zkf on 2017/8/29.
 */
public class AcknowledgeVO {

    @JSONField(name = "eventids")
    private List<String> eventIds;

    public List<String> getEventIds() {
        return eventIds;
    }

    public AcknowledgeVO setEventIds(List<String> eventIds) {
        this.eventIds = eventIds;
        return this;
    }
}
