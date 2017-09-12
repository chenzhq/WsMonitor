package com.ws.stoner.model.view.platform;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zkf on 2017/8/14.
 */
public class PlatDetailPlatformVO {

    @JSONField(name = "platform_id")
    private String platformId;
    @JSONField(name = "platform_name")
    private String platformName;

    public PlatDetailPlatformVO(String platformId) {
        this.platformId = platformId;
    }

    public PlatDetailPlatformVO(String platformId, String platformName) {
        this.platformId = platformId;
        this.platformName = platformName;
    }

    @Override
    public String toString() {
        return "PlatDetailPlatformVO{" +
                "platformId='" + platformId + '\'' +
                ", platformName='" + platformName + '\'' +
                '}';
    }

    public String getPlatformId() {
        return platformId;
    }

    public PlatDetailPlatformVO setPlatformId(String platformId) {
        this.platformId = platformId;
        return this;
    }

    public String getPlatformName() {
        return platformName;
    }

    public PlatDetailPlatformVO setPlatformName(String platformName) {
        this.platformName = platformName;
        return this;
    }
}
