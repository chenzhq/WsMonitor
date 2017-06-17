package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by pc on 2017/6/16.
 */
public class BriefPlatformDTO {
    @JSONField(name = "groupid")
    private String platformId;
    private Integer availability;
    private String name;
    private Integer problemNum; //问题主机数量
    private String state;

    public static final String[] PROPERTY_NAMES = {"groupid","name"};

    public String getPlatformId() {
        return platformId;
    }

    public BriefPlatformDTO setPlatformId(String platformId) {
        this.platformId = platformId;
        return this;
    }

    public Integer getAvailability() {
        return availability;
    }

    public BriefPlatformDTO setAvailability(Integer availability) {
        this.availability = availability;
        return this;
    }

    public String getName() {
        return name;
    }

    public BriefPlatformDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getProblemNum() {
        return problemNum;
    }

    public BriefPlatformDTO setProblemNum(Integer problemNum) {
        this.problemNum = problemNum;
        return this;
    }

    public String getState() {
        return state;
    }

    public BriefPlatformDTO setState(String state) {
        this.state = state;
        return this;
    }

    public static String[] getPropertyNames() {
        return PROPERTY_NAMES;
    }

    @Override
    public String toString() {
        return "BriefPlatformDTO{" +
                "platformId='" + platformId + '\'' +
                ", availability=" + availability +
                ", name='" + name + '\'' +
                ", problemNum=" + problemNum +
                ", state='" + state + '\'' +
                '}';
    }
}
