package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by pc on 2017/6/16.
 */
public class BriefPlatformDTO {
    @JSONField(name = "groupid")
    private String platformId;
    private String name;
    @JSONField(name = "custom_state")
    private String customState;
    @JSONField(name = "hosts")
    private List<BriefHostDTO> hosts;

    public static final String[] PROPERTY_NAMES = {"groupid","name","custom_state"};

    @Override
    public String toString() {
        return "BriefPlatformDTO{" +
                "platformId='" + platformId + '\'' +
                ", name='" + name + '\'' +
                ", customState='" + customState + '\'' +
                ", hosts=" + hosts +
                '}';
    }

    public String getCustomState() {
        return customState;
    }

    public BriefPlatformDTO setCustomState(String customState) {
        this.customState = customState;
        return this;
    }

    public String getPlatformId() {
        return platformId;
    }

    public BriefPlatformDTO setPlatformId(String platformId) {
        this.platformId = platformId;
        return this;
    }

    public List<BriefHostDTO> getHosts() {
        return hosts;
    }

    public BriefPlatformDTO setHosts(List<BriefHostDTO> hosts) {
        this.hosts = hosts;
        return this;
    }

    public String getName() {
        return name;
    }

    public BriefPlatformDTO setName(String name) {
        this.name = name;
        return this;
    }

}
