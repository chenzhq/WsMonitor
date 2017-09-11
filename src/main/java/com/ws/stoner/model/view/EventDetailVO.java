package com.ws.stoner.model.view;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Created by zkf on 2017/8/29.
 */
public class EventDetailVO {

    @JSONField(name = "event_id")
    private String eventId;
    @JSONField(name = "host_name")
    private String hostName;
    @JSONField(name = "point_name")
    private String pointName;
    @JSONField(name = "item_name")
    private String itemName;
    @JSONField(name = "trigger_name")
    private String triggerName;
    private String threshold ;
    private String level;
    private Integer state;
    @JSONField(name = "problem_time")
    private String problemTime;
    @JSONField(name = "recovery_time",serialzeFeatures = SerializerFeature.WriteNullStringAsEmpty)
    private String recoveryTime;
    private boolean closed;
    @JSONField(name = "closed_user",serialzeFeatures = SerializerFeature.WriteNullStringAsEmpty)
    private String closedUser;

    @Override
    public String toString() {
        return "EventDetailVO{" +
                "eventId='" + eventId + '\'' +
                ", hostName='" + hostName + '\'' +
                ", pointName='" + pointName + '\'' +
                ", itemName='" + itemName + '\'' +
                ", triggerName='" + triggerName + '\'' +
                ", threshold='" + threshold + '\'' +
                ", level='" + level + '\'' +
                ", state=" + state +
                ", problemTime='" + problemTime + '\'' +
                ", recoveryTime='" + recoveryTime + '\'' +
                ", closed=" + closed +
                ", closedUser='" + closedUser + '\'' +
                '}';
    }

    public String getEventId() {
        return eventId;
    }

    public EventDetailVO setEventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    public String getHostName() {
        return hostName;
    }

    public EventDetailVO setHostName(String hostName) {
        this.hostName = hostName;
        return this;
    }

    public String getPointName() {
        return pointName;
    }

    public EventDetailVO setPointName(String pointName) {
        this.pointName = pointName;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public EventDetailVO setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public EventDetailVO setTriggerName(String triggerName) {
        this.triggerName = triggerName;
        return this;
    }

    public String getThreshold() {
        return threshold;
    }

    public EventDetailVO setThreshold(String threshold) {
        this.threshold = threshold;
        return this;
    }

    public String getLevel() {
        return level;
    }

    public EventDetailVO setLevel(String level) {
        this.level = level;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public EventDetailVO setState(Integer state) {
        this.state = state;
        return this;
    }

    public String getProblemTime() {
        return problemTime;
    }

    public EventDetailVO setProblemTime(String problemTime) {
        this.problemTime = problemTime;
        return this;
    }

    public String getRecoveryTime() {
        return recoveryTime;
    }

    public EventDetailVO setRecoveryTime(String recoveryTime) {
        this.recoveryTime = recoveryTime;
        return this;
    }

    public boolean isClosed() {
        return closed;
    }

    public EventDetailVO setClosed(boolean closed) {
        this.closed = closed;
        return this;
    }

    public String getClosedUser() {
        return closedUser;
    }

    public EventDetailVO setClosedUser(String closedUser) {
        this.closedUser = closedUser;
        return this;
    }
}
