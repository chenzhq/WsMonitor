package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by pc on 2017/6/20.
 */
public class BriefTriggerDTO {
    @JSONField(name = "triggerid")
    private String triggerId;
    private String name;

    public static final String[] PROPERTY_NAMES = {"triggerid", "name"};

    @Override
    public String toString() {
        return "BriefTriggerDTO{" +
                "triggerId='" + triggerId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

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

    public static String[] getPropertyNames() {
        return PROPERTY_NAMES;
    }
}
