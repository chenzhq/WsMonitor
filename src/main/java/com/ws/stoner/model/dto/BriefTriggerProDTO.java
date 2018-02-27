package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zhongkf on 2018/2/6
 */
public class BriefTriggerProDTO {

    @JSONField(name = "triggerid")
    private String triggerId;
    private String description;
    private String expression;
    private String comments;
    private Integer priority;
    private Integer status;
    @JSONField(name = "templateid")
    private String templateId;
    private Integer type;
    private String url;
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

    public static final String[] PROPERTY_NAMES = {"triggerid", "description","expression","priority","manual_close","comments"};

    public String getTriggerId() {
        return triggerId;
    }

    public BriefTriggerProDTO setTriggerId(String triggerId) {
        this.triggerId = triggerId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BriefTriggerProDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getExpression() {
        return expression;
    }

    public BriefTriggerProDTO setExpression(String expression) {
        this.expression = expression;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public BriefTriggerProDTO setComments(String comments) {
        this.comments = comments;
        return this;
    }

    public Integer getPriority() {
        return priority;
    }

    public BriefTriggerProDTO setPriority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public BriefTriggerProDTO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getTemplateId() {
        return templateId;
    }

    public BriefTriggerProDTO setTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public BriefTriggerProDTO setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public BriefTriggerProDTO setUrl(String url) {
        this.url = url;
        return this;
    }

    public Integer getRecoveryMode() {
        return recoveryMode;
    }

    public BriefTriggerProDTO setRecoveryMode(Integer recoveryMode) {
        this.recoveryMode = recoveryMode;
        return this;
    }

    public String getRecoveryExpression() {
        return recoveryExpression;
    }

    public BriefTriggerProDTO setRecoveryExpression(String recoveryExpression) {
        this.recoveryExpression = recoveryExpression;
        return this;
    }

    public Integer getCorrelationMode() {
        return correlationMode;
    }

    public BriefTriggerProDTO setCorrelationMode(Integer correlationMode) {
        this.correlationMode = correlationMode;
        return this;
    }

    public String getCorrelationTag() {
        return correlationTag;
    }

    public BriefTriggerProDTO setCorrelationTag(String correlationTag) {
        this.correlationTag = correlationTag;
        return this;
    }

    public Integer getManualClose() {
        return manualClose;
    }

    public BriefTriggerProDTO setManualClose(Integer manualClose) {
        this.manualClose = manualClose;
        return this;
    }
}
