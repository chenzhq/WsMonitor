package com.ws.stoner.model.brief;

import com.alibaba.fastjson.annotation.JSONField;

import java.time.Instant;

/**
 * Created by pc on 2017/6/13.
 */
public class TriggerBrief {
    @JSONField(name = "triggerid")
    private String triggerId;
    private String description ;
    private String expression ;
    private Integer flags;
    private Integer state;
    private Integer status;
    @JSONField(name = "templateid")
    private String templateId;
    private Integer type;
    private Integer value;
    private String url;
    private Instant lastchange;
    private Integer priority;

    @Override
    public String toString() {
        return "TriggerBrief{" +
                "triggerId='" + triggerId + '\'' +
                ", description='" + description + '\'' +
                ", expression='" + expression + '\'' +
                ", flags=" + flags +
                ", state=" + state +
                ", status=" + status +
                ", templateId='" + templateId + '\'' +
                ", type=" + type +
                ", value=" + value +
                ", url='" + url + '\'' +
                ", lastchange=" + lastchange +
                ", priority=" + priority +
                '}';
    }

    public String getTriggerId() {
        return triggerId;
    }

    public TriggerBrief setTriggerId(String triggerId) {
        this.triggerId = triggerId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TriggerBrief setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getExpression() {
        return expression;
    }

    public TriggerBrief setExpression(String expression) {
        this.expression = expression;
        return this;
    }

    public Integer getFlags() {
        return flags;
    }

    public TriggerBrief setFlags(Integer flags) {
        this.flags = flags;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public TriggerBrief setState(Integer state) {
        this.state = state;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public TriggerBrief setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getTemplateId() {
        return templateId;
    }

    public TriggerBrief setTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public TriggerBrief setType(Integer type) {
        this.type = type;
        return this;
    }

    public Integer getValue() {
        return value;
    }

    public TriggerBrief setValue(Integer value) {
        this.value = value;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public TriggerBrief setUrl(String url) {
        this.url = url;
        return this;
    }

    public Instant getLastchange() {
        return lastchange;
    }

    public TriggerBrief setLastchange(int lastchange) {
        this.lastchange = Instant.ofEpochSecond(lastchange);
        return this;
    }

    public Integer getPriority() {
        return priority;
    }

    public TriggerBrief setPriority(Integer priority) {
        this.priority = priority;
        return this;
    }
}
