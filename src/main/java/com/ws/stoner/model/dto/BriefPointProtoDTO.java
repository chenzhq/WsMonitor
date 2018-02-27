package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zhongkf on 2018/1/30
 */
public class BriefPointProtoDTO {

    @JSONField(name = "application_discoveryid")
    private String applicationDiscoveryid;
    @JSONField(name = "applicationid")
    private String applicationId;
    @JSONField(name = "application_prototypeid")
    private String applicationPrototypeid;
    private String name;

    public static final String[] PROPERTY_NAMES = {"application_discoveryid","applicationid","application_prototypeid","name"};

    public String getApplicationDiscoveryid() {
        return applicationDiscoveryid;
    }

    public BriefPointProtoDTO setApplicationDiscoveryid(String applicationDiscoveryid) {
        this.applicationDiscoveryid = applicationDiscoveryid;
        return this;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public BriefPointProtoDTO setApplicationId(String applicationId) {
        this.applicationId = applicationId;
        return this;
    }

    public String getApplicationPrototypeid() {
        return applicationPrototypeid;
    }

    public BriefPointProtoDTO setApplicationPrototypeid(String applicationPrototypeid) {
        this.applicationPrototypeid = applicationPrototypeid;
        return this;
    }

    public String getName() {
        return name;
    }

    public BriefPointProtoDTO setName(String name) {
        this.name = name;
        return this;
    }
}
