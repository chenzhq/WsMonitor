package com.ws.stoner.model.view;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Created by zkf on 2017/8/22.
 */
public class ProblemListVO {

    private String eventId;
    private String lastTime;
    private String hostName;
    private String description;
    private String state;
    //持续时间的拼接字符串，格式为 **天**小时*分钟
    private String durationString;
    private String alertState;
    private String acknowledged;

    public String getEventId() {
        return eventId;
    }

    public ProblemListVO setEventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    public String getLastTime() {
        return lastTime;
    }

    public ProblemListVO setLastTime(String lastTime) {
        this.lastTime = lastTime;
        return this;
    }

    public String getHostName() {
        return hostName;
    }

    public ProblemListVO setHostName(String hostName) {
        this.hostName = hostName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProblemListVO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getState() {
        return state;
    }

    public ProblemListVO setState(String state) {
        this.state = state;
        return this;
    }

    public String getDurationString() {
        return durationString;
    }

    public ProblemListVO setDurationString(LocalDateTime time) {
        Duration duration = Duration.between(time, LocalDateTime.now());
        Long sec = duration.getSeconds();

        int days = (int) (sec / (24 * 3600));
        int hours = (int) (sec / 3600 % 24);
        int minute = (int) (sec / 60 % 60);
        StringBuilder timeStringBuilder = new StringBuilder();
        timeStringBuilder.append(days == 0 ? "" : days + "天");
        timeStringBuilder.append(hours == 0 ? "" : hours + "小时");
        timeStringBuilder.append(minute == 0 ? "" : minute + "分钟");
        this.durationString = timeStringBuilder.toString();
        return this;
    }

    public String getAlertState() {
        return alertState;
    }

    public ProblemListVO setAlertState(String alertState) {
        this.alertState = alertState;
        return this;
    }

    public String getAcknowledged() {
        return acknowledged;
    }

    public ProblemListVO setAcknowledged(String acknowledged) {
        this.acknowledged = acknowledged;
        return this;
    }

    @Override
    public String toString() {
        return "ProblemListVO{" +
                "eventId='" + eventId + '\'' +
                ", lastTime='" + lastTime + '\'' +
                ", hostName='" + hostName + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                ", durationString='" + durationString + '\'' +
                ", alertState='" + alertState + '\'' +
                ", acknowledged='" + acknowledged + '\'' +
                '}';
    }
}
