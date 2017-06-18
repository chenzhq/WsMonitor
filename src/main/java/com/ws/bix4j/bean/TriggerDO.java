package com.ws.bix4j.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.time.Instant;

/**
 * Created by zkf on 2017/6/8.
 */
public class TriggerDO {
    @JSONField(name = "triggerid")
    private String triggerId;
    private String description ;
    private String expression ;
    private String comments;
    private String error;
    /**
     * 默认为0，表示自建的触发器
     * 4：自动发现的触发器
     */
    private Integer flags;
    private Instant lastchange;
    private Integer priority;
    private Integer state;
    private Integer status;
    @JSONField(name = "templateid")
    private String templateId;
    private Integer type;
    private String url;
    private Integer value;
    @JSONField(name = "recovery_mode")
    private Integer recoveryMode;
    @JSONField(name = "recovery_expression")
    private String recoveryExpression;
    @JSONField(name = "correlation_mode")
    private Integer correlationMode;
    @JSONField(name = "correlation_tag")
    private String correlationTag;
    @JSONField(name = "manual_close")
    private Integer manualClose;

    @Override
    public String toString() {
        return "TriggerDO{" +
                "triggerId='" + triggerId + '\'' +
                ", description='" + description + '\'' +
                ", expression='" + expression + '\'' +
                ", comments='" + comments + '\'' +
                ", error='" + error + '\'' +
                ", flags=" + flags +
                ", lastchange=" + lastchange +
                ", priority=" + priority +
                ", state=" + state +
                ", status=" + status +
                ", templateId='" + templateId + '\'' +
                ", type=" + type +
                ", url='" + url + '\'' +
                ", value=" + value +
                ", recoveryMode=" + recoveryMode +
                ", recoveryExpression='" + recoveryExpression + '\'' +
                ", correlationMode=" + correlationMode +
                ", correlationTag='" + correlationTag + '\'' +
                ", manualClose=" + manualClose +
                '}';
    }

    public String getTriggerId() {
        return triggerId;
    }

    public TriggerDO setTriggerId(String triggerId) {
        this.triggerId = triggerId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TriggerDO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getExpression() {
        return expression;
    }

    public TriggerDO setExpression(String expression) {
        this.expression = expression;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public TriggerDO setComments(String comments) {
        this.comments = comments;
        return this;
    }

    public String getError() {
        return error;
    }

    public TriggerDO setError(String error) {
        this.error = error;
        return this;
    }

    public Integer getFlags() {
        return flags;
    }

    public TriggerDO setFlags(Integer flags) {
        this.flags = flags;
        return this;
    }

    public Instant getLastchange() {
        return lastchange;
    }

    public TriggerDO setLastchange(int lastchange) {
        this.lastchange = Instant.ofEpochSecond(lastchange);
        return this;
    }

    public Integer getPriority() {
        return priority;
    }

    public TriggerDO setPriority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public TriggerDO setState(Integer state) {
        this.state = state;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public TriggerDO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getTemplateId() {
        return templateId;
    }

    public TriggerDO setTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public TriggerDO setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public TriggerDO setUrl(String url) {
        this.url = url;
        return this;
    }

    public Integer getValue() {
        return value;
    }

    public TriggerDO setValue(Integer value) {
        this.value = value;
        return this;
    }

    public Integer getRecoveryMode() {
        return recoveryMode;
    }

    public TriggerDO setRecoveryMode(Integer recoveryMode) {
        this.recoveryMode = recoveryMode;
        return this;
    }

    public String getRecoveryExpression() {
        return recoveryExpression;
    }

    public TriggerDO setRecoveryExpression(String recoveryExpression) {
        this.recoveryExpression = recoveryExpression;
        return this;
    }

    public Integer getCorrelationMode() {
        return correlationMode;
    }

    public TriggerDO setCorrelationMode(Integer correlationMode) {
        this.correlationMode = correlationMode;
        return this;
    }

    public String getCorrelationTag() {
        return correlationTag;
    }

    public TriggerDO setCorrelationTag(String correlationTag) {
        this.correlationTag = correlationTag;
        return this;
    }

    public Integer getManualClose() {
        return manualClose;
    }

    public TriggerDO setManualClose(Integer manualClose) {
        this.manualClose = manualClose;
        return this;
    }
}
