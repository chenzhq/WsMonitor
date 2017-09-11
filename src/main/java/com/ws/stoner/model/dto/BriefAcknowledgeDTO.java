package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by zkf on 2017/8/22.
 */
public class BriefAcknowledgeDTO {

    @JSONField(name = "acknowledgeid")
    private String acknowledgeId;
    @JSONField(name = "userid")
    private String userId;
    @JSONField(name = "eventid")
    private String eventId;
    private LocalDateTime clock;
    private String message;
    private String alias;
    private String action;

    public static final String[] PROPERTY_NAMES = {"acknowledgeid", "userid","clock","eventid","message","alias","action"};

    public String getAcknowledgeId() {
        return acknowledgeId;
    }

    public BriefAcknowledgeDTO setAcknowledgeId(String acknowledgeId) {
        this.acknowledgeId = acknowledgeId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public BriefAcknowledgeDTO setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getEventId() {
        return eventId;
    }

    public BriefAcknowledgeDTO setEventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    public LocalDateTime getClock() {
        return clock;
    }

    public BriefAcknowledgeDTO setClock(int time) {
        Instant instant = Instant.ofEpochSecond(time);
        this.clock = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return this;
    }

    public String getMessage() {
        return message;
    }

    public BriefAcknowledgeDTO setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getAlias() {
        return alias;
    }

    public BriefAcknowledgeDTO setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public String getAction() {
        return action;
    }

    public BriefAcknowledgeDTO setAction(String action) {
        this.action = action;
        return this;
    }

    @Override
    public String toString() {
        return "BriefAcknowledgeDTO{" +
                "acknowledgeId='" + acknowledgeId + '\'' +
                ", userId='" + userId + '\'' +
                ", eventId='" + eventId + '\'' +
                ", clock=" + clock +
                ", message='" + message + '\'' +
                ", alias='" + alias + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
