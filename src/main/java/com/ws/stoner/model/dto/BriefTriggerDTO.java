package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by zkf on 2017/6/20.
 */
public class BriefTriggerDTO {
    @JSONField(name = "triggerid")
    private String triggerId;
    @JSONField(name = "description")
    private String name;
    private String expression;
    private Integer priority;
    private Integer value;
    private List<BriefItemDTO> items;
    private List<BriefHostDTO> hosts;
    private BriefEventDTO lastEvent;


    public static final String[] PROPERTY_NAMES = {"triggerid", "description","expression","priority","value"};

    public String getTriggerId() {
        return triggerId;
    }

    public BriefTriggerDTO setTriggerId(String triggerId) {
        this.triggerId = triggerId;
        return this;
    }

    public String getName() {
        return name;
    }

    public BriefTriggerDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getExpression() {
        return expression;
    }

    public BriefTriggerDTO setExpression(String expression) {
        this.expression = expression;
        return this;
    }

    public Integer getPriority() {
        return priority;
    }

    public BriefTriggerDTO setPriority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public List<BriefItemDTO> getItems() {
        return items;
    }

    public BriefTriggerDTO setItems(List<BriefItemDTO> items) {
        this.items = items;
        return this;
    }

    public List<BriefHostDTO> getHosts() {
        return hosts;
    }

    public BriefTriggerDTO setHosts(List<BriefHostDTO> hosts) {
        this.hosts = hosts;
        return this;
    }

    public BriefEventDTO getLastEvent() {
        return lastEvent;
    }

    public BriefTriggerDTO setLastEvent(BriefEventDTO lastEvent) {
        this.lastEvent = lastEvent;
        return this;
    }

    public Integer getValue() {
        return value;
    }

    public BriefTriggerDTO setValue(Integer value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return "BriefTriggerDTO{" +
                "triggerId='" + triggerId + '\'' +
                ", name='" + name + '\'' +
                ", expression='" + expression + '\'' +
                ", priority=" + priority +
                ", value=" + value +
                ", items=" + items +
                ", hosts=" + hosts +
                ", lastEvent=" + lastEvent +
                '}';
    }
}
