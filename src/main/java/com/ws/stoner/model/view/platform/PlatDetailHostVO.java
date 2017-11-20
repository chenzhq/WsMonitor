package com.ws.stoner.model.view.platform;

import com.alibaba.fastjson.annotation.JSONField;


/**
 * Created by zkf on 2017/8/9.
 */
public class PlatDetailHostVO {
    @JSONField(name = "host_id")
    private String hostId;
    @JSONField(name = "host_name")
    private String hostName;
    private String state;

    public PlatDetailHostVO(String hostId, String hostName, String state) {
        this.hostId = hostId;
        this.hostName = hostName;
        this.state = state;
    }

    @Override
    public String toString() {
        return "PlatDetailHostVO{" +
                "hostId='" + hostId + '\'' +
                ", hostName='" + hostName + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public String getHostId() {
        return hostId;
    }

    public PlatDetailHostVO setHostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public String getHostName() {
        return hostName;
    }

    public PlatDetailHostVO setHostName(String hostName) {
        this.hostName = hostName;
        return this;
    }

    public String getState() {
        return state;
    }

    public PlatDetailHostVO setState(String state) {
        this.state = state;
        return this;
    }
}
