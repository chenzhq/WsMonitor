package com.ws.stoner.model.view;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zkf on 2017/8/23.
 */
public class ProblemAlertVO {

    @JSONField(name = "alert_id")
    private String alertId;
    @JSONField(name = "esc_step")
    private Integer escStep;
    @JSONField(name = "last_time")
    private String lastTime;
    private String status;
    private Integer retries;
    private String alias;
    private String sendto;
    private boolean recovery;

    public ProblemAlertVO() {
    }

    public ProblemAlertVO(String alertId, Integer escStep, String lastTime, String status, Integer retries, String alias, String sendto, boolean recovery) {
        this.alertId = alertId;
        this.escStep = escStep;
        this.lastTime = lastTime;
        this.status = status;
        this.retries = retries;
        this.alias = alias;
        this.sendto = sendto;
        this.recovery = recovery;
    }

    public String getAlertId() {
        return alertId;
    }

    public ProblemAlertVO setAlertId(String alertId) {
        this.alertId = alertId;
        return this;
    }

    public String getLastTime() {
        return lastTime;
    }

    public ProblemAlertVO setLastTime(String lastTime) {
        this.lastTime = lastTime;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ProblemAlertVO setStatus(String status) {
        this.status = status;
        return this;
    }

    public Integer getRetries() {
        return retries;
    }

    public ProblemAlertVO setRetries(Integer retries) {
        this.retries = retries;
        return this;
    }

    public String getAlias() {
        return alias;
    }

    public ProblemAlertVO setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public Integer getEscStep() {
        return escStep;
    }

    public ProblemAlertVO setEscStep(Integer escStep) {
        this.escStep = escStep;
        return this;
    }

    public String getSendto() {
        return sendto;
    }

    public ProblemAlertVO setSendto(String sendto) {
        this.sendto = sendto;
        return this;
    }

    public boolean isRecovery() {
        return recovery;
    }

    public ProblemAlertVO setRecovery(boolean recovery) {
        this.recovery = recovery;
        return this;
    }

    @Override
    public String toString() {
        return "ProblemAlertVO{" +
                "alertId='" + alertId + '\'' +
                ", escStep=" + escStep +
                ", lastTime='" + lastTime + '\'' +
                ", status='" + status + '\'' +
                ", retries=" + retries +
                ", alias='" + alias + '\'' +
                ", sendto='" + sendto + '\'' +
                ", recovery=" + recovery +
                '}';
    }
}
