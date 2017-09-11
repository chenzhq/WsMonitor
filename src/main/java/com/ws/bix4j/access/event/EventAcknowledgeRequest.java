package com.ws.bix4j.access.event;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.access.ZRequest;

import java.util.List;

/**
 * Created by zkf on 2017/8/29.
 */
public class EventAcknowledgeRequest extends ZRequest<EventAcknowledgeRequest.Params> {

    public EventAcknowledgeRequest() {
        this.setMethod("event.acknowledge");
    }

    private Params params =new Params();

    @Override
    public Params getParams() {
        return params;
    }

    public EventAcknowledgeRequest setParams(Params params) {
        this.params = params;
        return this;
    }

    public class Params {

        @JSONField(name = "eventids")
        private List<String> eventIds;
        private String message;
        private Integer action;

        public List<String> getEventIds() {
            return eventIds;
        }

        public Params setEventIds(List<String> eventIds) {
            this.eventIds = eventIds;
            return this;
        }

        public String getMessage() {
            return message;
        }

        public Params setMessage(String message) {
            this.message = message;
            return this;
        }

        public Integer getAction() {
            return action;
        }

        public Params setAction(Integer action) {
            this.action = action;
            return this;
        }
    }

}
