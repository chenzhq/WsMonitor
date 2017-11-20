package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by pc on 2017/8/22.
 */
public class BriefAlertDTO {

    @JSONField(name = "alertid")
    private String alertId;
    @JSONField(name = "mediatypeid")
    private String mediatypeId;
    private LocalDateTime clock;
    @JSONField(name = "esc_step")
    private Integer escStep;
    @JSONField(name = "eventid")
    private String eventId;
    private String message;
    private String subject;
    private Integer status;
    private Integer retries;
    private String sendto;
    @JSONField(name = "userid")
    private String userId;

    private List<UserInfoDTO> users;
    private List<BriefMediatypeDTO> mediatypes;

    public static final String[] PROPERTY_NAMES = {"alertid", "mediatypeid","clock","esc_step","eventid","message","subject","retries","sendto","status","userid"};

    public String getAlertId() {
        return alertId;
    }

    public BriefAlertDTO setAlertId(String alertId) {
        this.alertId = alertId;
        return this;
    }

    public String getMediatypeId() {
        return mediatypeId;
    }

    public BriefAlertDTO setMediatypeId(String mediatypeId) {
        this.mediatypeId = mediatypeId;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public BriefAlertDTO setSubject(String subject) {
        this.subject = subject;
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

    public Integer getEscStep() {
        return escStep;
    }

    public BriefAlertDTO setEscStep(Integer escStep) {
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

    public Integer getRetries() {
        return retries;
    }

    public BriefAlertDTO setRetries(Integer retries) {
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

    public Integer getStatus() {
        return status;
    }

    public BriefAlertDTO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public List<UserInfoDTO> getUsers() {
        return users;
    }

    public BriefAlertDTO setUsers(List<UserInfoDTO> users) {
        this.users = users;
        return this;
    }

    public List<BriefMediatypeDTO> getMediatypes() {
        return mediatypes;
    }

    public BriefAlertDTO setMediatypes(List<BriefMediatypeDTO> mediatypes) {
        this.mediatypes = mediatypes;
        return this;
    }

    @Override
    public String toString() {
        return "BriefAlertDTO{" +
                "alertId='" + alertId + '\'' +
                ", mediatypeId='" + mediatypeId + '\'' +
                ", clock=" + clock +
                ", escStep=" + escStep +
                ", eventId='" + eventId + '\'' +
                ", message='" + message + '\'' +
                ", subject='" + subject + '\'' +
                ", status=" + status +
                ", retries=" + retries +
                ", sendto='" + sendto + '\'' +
                ", userId='" + userId + '\'' +
                ", users=" + users +
                ", mediatypes=" + mediatypes +
                '}';
    }

    //alertDTO 步骤 排序
    public static void sortListByEscStep(List<BriefAlertDTO> list){
        Collections.sort(list, new Comparator<BriefAlertDTO>() {
            @Override
            public int compare(BriefAlertDTO o1, BriefAlertDTO o2) {
                return o2.getEscStep().compareTo(o1.getEscStep());
            }
        });
    }
}
