package com.ws.bix4j.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.time.Instant;

/**
 * Created by chenzheqi on 2017/5/22.
 */
public class ProblemDO {

    @JSONField(name = "eventid")
    private String eventId;
    private int source;
    private int object;
    @JSONField(name = "objectid")
    private String objectId;
    private Instant clock;
    private int ns;
    @JSONField(name = "r_eventid")
    private String rEventId;
    @JSONField(name = "r_clock")
    private Instant rClock;
    @JSONField(name = "r_ns")
    private int rNs;
    @JSONField(name = "correlationid")
    private String correlationId;
    @JSONField(name = "userid")
    private String userId;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getObject() {
        return object;
    }

    public void setObject(int object) {
        this.object = object;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Instant getClock() {
        return clock;
    }

    public void setClock(int clock) {
        this.clock = Instant.ofEpochSecond(clock);
    }

    public int getNs() {
        return ns;
    }

    public void setNs(int ns) {
        this.ns = ns;
    }

    public String getREventid() {
        return rEventId;
    }

    public void setREventid(String rEventid) {
        this.rEventId = rEventid;
    }

    public Instant getRClock() {
        return rClock;
    }

    public void setRClock(int rClock) {
        this.rClock = Instant.ofEpochSecond(rClock);
    }

    public int getRNs() {
        return rNs;
    }

    public void setRNs(int rNs) {
        this.rNs = rNs;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
