package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by pc on 2017/8/22.
 */
public class BriefAlertDTO {

    @JSONField(name = "alertid")
    private String alertId;
    @JSONField(name = "alerttype")
    private String alertType;
    private LocalDateTime clock;
    @JSONField(name = "esc_step")
    private String escStep;
    @JSONField(name = "eventid")
    private String eventId;
    private String message;
    private String retries;
    private String sendto;
    @JSONField(name = "userid")
    private String userId;

    public static final String[] PROPERTY_NAMES = {"alertid", "alerttype","clock","esc_step","eventid","message","retries","sendto","status","userid"};

    public String getAlertId() {
        return alertId;
    }

    public BriefAlertDTO setAlertId(String alertId) {
        this.alertId = alertId;
        return this;
    }

    public String getAlertType() {
        return alertType;
    }

    public BriefAlertDTO setAlertType(String alertType) {
        this.alertType = alertType;
        return this;
    }

    public LocalDateTime getClock() {
        return clock;
    }

    public BriefAlertDTO setClock(int time) {
        Instant instant = Instant.ofEpochSecond(time);
        this.clock = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return this;
    }

    public String getEscStep() {
        return escStep;
    }

    public BriefAlertDTO setEscStep(String escStep) {
        this.escStep = escStep;
        return this;
    }

    public String getEventId() {
        return eventId;
    }

    public BriefAlertDTO setEventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public BriefAlertDTO setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getRetries() {
        return retries;
    }

    public BriefAlertDTO setRetries(String retries) {
        this.retries = retries;
        return this;
    }

    public String getSendto() {
        return sendto;
    }

    public BriefAlertDTO setSendto(String sendto) {
        this.sendto = sendto;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public BriefAlertDTO setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public String toString() {
        return "BriefAlertDTO{" +
                "alertId='" + alertId + '\'' +
                ", alertType='" + alertType + '\'' +
                ", clock='" + clock + '\'' +
                ", escStep='" + escStep + '\'' +
                ", eventId='" + eventId + '\'' +
                ", message='" + message + '\'' +
                ", retries='" + retries + '\'' +
                ", sendto='" + sendto + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
