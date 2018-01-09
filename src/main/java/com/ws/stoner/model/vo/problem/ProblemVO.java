package com.ws.stoner.model.vo.problem;

import com.ws.stoner.model.vo.HostVO;
import com.ws.stoner.model.vo.item.ItemVO;

/**
 * Created by zhongkf on 2017/12/8
 */
public class ProblemVO {
    private String eventId;
    private String triggerId;
    private String description;
    private String state;
    private String time;
    private String priority;
    private String recoveryEventId;
    private String recoveryTime;
    private boolean isAcknowledged;
    private String alertStatus;
    private Integer alertNum;

    private HostVO host;
    private ItemVO item;

    public ProblemVO() {
    }

    @Override
    public String toString() {
        return "ProblemVO{" +
                "eventId='" + eventId + '\'' +
                ", triggerId='" + triggerId + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                ", time='" + time + '\'' +
                ", priority='" + priority + '\'' +
                ", recoveryEventId='" + recoveryEventId + '\'' +
                ", recoveryTime='" + recoveryTime + '\'' +
                ", isAcknowledged=" + isAcknowledged +
                ", alertStatus='" + alertStatus + '\'' +
                ", alertNum=" + alertNum +
                ", host=" + host +
                ", item=" + item +
                '}';
    }

    public String getEventId() {
        return eventId;
    }

    public ProblemVO setEventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    public String getTriggerId() {
        return triggerId;
    }

    public ProblemVO setTriggerId(String triggerId) {
        this.triggerId = triggerId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProblemVO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getState() {
        return state;
    }

    public ProblemVO setState(String state) {
        this.state = state;
        return this;
    }

    public String getTime() {
        return time;
    }

    public ProblemVO setTime(String time) {
        this.time = time;
        return this;
    }


    public String getPriority() {
        return priority;
    }

    public ProblemVO setPriority(String priority) {
        this.priority = priority;
        return this;
    }

    public String getRecoveryEventId() {
        return recoveryEventId;
    }

    public ProblemVO setRecoveryEventId(String recoveryEventId) {
        this.recoveryEventId = recoveryEventId;
        return this;
    }

    public String getRecoveryTime() {
        return recoveryTime;
    }

    public ProblemVO setRecoveryTime(String recoveryTime) {
        this.recoveryTime = recoveryTime;
        return this;
    }

    public boolean isAcknowledged() {
        return isAcknowledged;
    }

    public ProblemVO setAcknowledged(boolean acknowledged) {
        isAcknowledged = acknowledged;
        return this;
    }

    public String getAlertStatus() {
        return alertStatus;
    }

    public ProblemVO setAlertStatus(String alertStatus) {
        this.alertStatus = alertStatus;
        return this;
    }

    public Integer getAlertNum() {
        return alertNum;
    }

    public ProblemVO setAlertNum(Integer alertNum) {
        this.alertNum = alertNum;
        return this;
    }

    public HostVO getHost() {
        return host;
    }

    public ProblemVO setHost(HostVO host) {
        this.host = host;
        return this;
    }

    public ItemVO getItem() {
        return item;
    }

    public ProblemVO setItem(ItemVO item) {
        this.item = item;
        return this;
    }


}
