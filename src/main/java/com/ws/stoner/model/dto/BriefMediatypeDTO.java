package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zkf on 2017/8/29.
 */
public class BriefMediatypeDTO {

    @JSONField(name = "mediatypeid")
    private String mediatypId;
    private String description;
    private Integer type;

    public static final String[] PROPERTY_NAMES = {"mediatypeid", "description","type"};

    public String getMediatypId() {
        return mediatypId;
    }

    public BriefMediatypeDTO setMediatypId(String mediatypId) {
        this.mediatypId = mediatypId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BriefMediatypeDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public BriefMediatypeDTO setType(Integer type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return "BriefMediatypeDTO{" +
                "mediatypId='" + mediatypId + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
