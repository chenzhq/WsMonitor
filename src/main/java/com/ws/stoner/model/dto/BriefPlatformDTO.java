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
    @JSONField(name = "hosts")
    private List<BriefHostDTO> hosts;

    public static final String[] PROPERTY_NAMES = {"groupid","name"};

    public String getPlatformId() {
        return platformId;
    }

    public BriefPlatformDTO setPlatformId(String platformId) {
        this.platformId = platformId;
        return this;
    }

    @Override
    public String toString() {
        return "BriefPlatformDTO{" +
                "platformId='" + platformId + '\'' +
                ", name='" + name + '\'' +
                ", hosts=" + hosts +
                '}';
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
