package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by pc on 2017/6/16.
 */
public class BriefPlatformDTO {
    @JSONField(name = "groupid")
    private String platformId;
    private String name;

    public static final String[] PROPERTY_NAMES = {"groupid","name"};

    public String getPlatformId() {
        return platformId;
    }

    public BriefPlatformDTO setPlatformId(String platformId) {
        this.platformId = platformId;
        return this;
    }

    public String getName() {
        return name;
    }

    public BriefPlatformDTO setName(String name) {
        this.name = name;
        return this;
    }

    public static String[] getPropertyNames() {
        return PROPERTY_NAMES;
    }

    @Override
    public String toString() {
        return "BriefPlatformDTO{" +
                "platformId='" + platformId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
