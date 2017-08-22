package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by pc on 2017/8/22.
 */
public class BriefEventDTO {

    @JSONField(name = "eventid")
    private String eventId;
    private Integer source;
    private Integer object;
    @JSONField(name = "objectid")
    private String objectId;
    private Integer acknowledged;
    private LocalDateTime clock;
    private Integer value;
    @JSONField(name = "r_eventid")
    private String rEventid;
    @JSONField(name = "userid")
    private String userId;

    public static final String[] PROPERTY_NAMES = {"eventid", "source","object","objectid","acknowledged","clock","value","r_eventid","userid"};

    public String getEventId() {
        return eventId;
    }

    public BriefEventDTO setEventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    public Integer getSource() {
        return source;
    }

    public BriefEventDTO setSource(Integer source) {
        this.source = source;
        return this;
    }

    public Integer getObject() {
        return object;
    }

    public BriefEventDTO setObject(Integer object) {
        this.object = object;
        return this;
    }

    public String getObjectId() {
        return objectId;
    }

    public BriefEventDTO setObjectId(String objectId) {
        this.objectId = objectId;
        return this;
    }

    public Integer getAcknowledged() {
        return acknowledged;
    }

    public BriefEventDTO setAcknowledged(Integer acknowledged) {
        this.acknowledged = acknowledged;
        return this;
    }

    public LocalDateTime getClock() {
        return clock;
    }

    public BriefEventDTO setClock(int time) {
        Instant instant = Instant.ofEpochSecond(time);
        this.clock = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return this;
    }

    public Integer getValue() {
        return value;
    }

    public BriefEventDTO setValue(Integer value) {
        this.value = value;
        return this;
    }

    public String getrEventid() {
        return rEventid;
    }

    public BriefEventDTO setrEventid(String rEventid) {
        this.rEventid = rEventid;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public BriefEventDTO setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public String toString() {
        return "BriefEventDTO{" +
                "eventId='" + eventId + '\'' +
                ", source=" + source +
                ", object=" + object +
                ", objectId='" + objectId + '\'' +
                ", acknowledged=" + acknowledged +
                ", clock='" + clock + '\'' +
                ", value=" + value +
                ", rEventid='" + rEventid + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
