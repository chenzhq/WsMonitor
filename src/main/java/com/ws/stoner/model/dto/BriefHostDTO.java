package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by chenzheqi on 2017/6/13.
 */
public class BriefHostDTO {
    @JSONField(name = "hostid")
    private String hostId;
    @JSONField(name = "host")
    private String hostName;
    @JSONField(name = "name")
    private String visibleName;

    public static final String[] PROPERTY_NAMES = {"hostid", "host", "name"};


    public String getHostId() {
        return hostId;
    }

    public BriefHostDTO setHostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public String getHostName() {
        return hostName;
    }

    public BriefHostDTO setHostName(String hostName) {
        this.hostName = hostName;
        return this;
    }

    public String getVisibleName() {
        return visibleName;
    }

    public BriefHostDTO setVisibleName(String visibleName) {
        this.visibleName = visibleName;
        return this;
    }
}
