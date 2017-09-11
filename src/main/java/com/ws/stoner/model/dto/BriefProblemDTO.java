package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by zkf on 2017/8/25.
 */
public class BriefProblemDTO {

    @JSONField(name = "eventid")
    private String eventId;
    private Integer source;
    private Integer object;
    @JSONField(name = "objectid")
    private String objectId;
    @JSONField(name = "clock")
    private LocalDateTime problemTime;
    @JSONField(name = "r_eventid")
    private String rEventid;
    @JSONField(name = "r_clock")
    private LocalDateTime recoveryTime;
    @JSONField(name = "userid")
    private String userId;

    public static final String[] PROPERTY_NAMES = {"eventid","objectid","source","object","clock","r_eventid","r_clock","userid"};

    public String getEventId() {
        return eventId;
    }

    public BriefProblemDTO setEventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    public Integer getSource() {
        return source;
    }

    public BriefProblemDTO setSource(Integer source) {
        this.source = source;
        return this;
    }

    public Integer getObject() {
        return object;
    }

    public BriefProblemDTO setObject(Integer object) {
        this.object = object;
        return this;
    }

    public String getObjectId() {
        return objectId;
    }

    public BriefProblemDTO setObjectId(String objectId) {
        this.objectId = objectId;
        return this;
    }

    public LocalDateTime getProblemTime() {
        return problemTime;
    }

    public BriefProblemDTO setProblemTime(int time) {
        Instant instant = Instant.ofEpochSecond(time);
        this.problemTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return this;
    }

    public String getrEventid() {
        return rEventid;
    }

    public BriefProblemDTO setrEventid(String rEventid) {
        this.rEventid = rEventid;
        return this;
    }

    public LocalDateTime getRecoveryTime() {
        return recoveryTime;
    }

    public BriefProblemDTO setRecoveryTime(int time) {
        if(time == 0) {
            this.recoveryTime = null;
        }else {
            Instant instant = Instant.ofEpochSecond(time);
            this.recoveryTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        }
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public BriefProblemDTO setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public String toString() {
        return "BriefProblemDTO{" +
                "eventId='" + eventId + '\'' +
                ", source=" + source +
                ", object=" + object +
                ", objectId='" + objectId + '\'' +
                ", problemTime=" + problemTime +
                ", rEventid='" + rEventid + '\'' +
                ", recoveryTime=" + recoveryTime +
                ", userId='" + userId + '\'' +
                '}';
    }
}
