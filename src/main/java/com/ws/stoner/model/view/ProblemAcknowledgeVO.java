package com.ws.stoner.model.view;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zkf on 2017/8/23.
 */
public class ProblemAcknowledgeVO {

    @JSONField(name = "acknowledge_id")
    private String acknowledgeId;
    @JSONField(name = "clock")
    private String lastTime;
    private String alias;
    private String message;
    private String action;

    @Override
    public String toString() {
        return "ProblemAcknowledgeVO{" +
                "acknowledgeId='" + acknowledgeId + '\'' +
                ", lastTime='" + lastTime + '\'' +
                ", alias='" + alias + '\'' +
                ", message='" + message + '\'' +
                ", action='" + action + '\'' +
                '}';
    }

    public ProblemAcknowledgeVO() {
    }

    public ProblemAcknowledgeVO(String acknowledgeId, String lastTime, String alias, String message, String action) {
        this.acknowledgeId = acknowledgeId;
        this.lastTime = lastTime;
        this.alias = alias;
        this.message = message;
        this.action = action;
    }

    public String getAcknowledgeId() {
        return acknowledgeId;
    }

    public ProblemAcknowledgeVO setAcknowledgeId(String acknowledgeId) {
        this.acknowledgeId = acknowledgeId;
        return this;
    }

    public String getLastTime() {
        return lastTime;
    }

    public ProblemAcknowledgeVO setLastTime(String lastTime) {
        this.lastTime = lastTime;
        return this;
    }

    public String getAlias() {
        return alias;
    }

    public ProblemAcknowledgeVO setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ProblemAcknowledgeVO setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getAction() {
        return action;
    }

    public ProblemAcknowledgeVO setAction(String action) {
        this.action = action;
        return this;
    }
}

