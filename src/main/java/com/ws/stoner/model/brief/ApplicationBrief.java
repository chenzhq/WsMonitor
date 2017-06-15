package com.ws.stoner.model.brief;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by pc on 2017/6/13.
 */
public class ApplicationBrief {
    @JSONField(name = "applicationid")
    private String applicationId;
    @JSONField(name = "hostid")
    private String hostId;
    private String name;
    private int flag;

    public String getApplicationId() {
        return applicationId;
    }

    public ApplicationBrief setApplicationId(String applicationId) {
        this.applicationId = applicationId;
        return this;
    }

    public String getHostId() {
        return hostId;
    }

    public ApplicationBrief setHostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ApplicationBrief setName(String name) {
        this.name = name;
        return this;
    }

    public int getFlag() {
        return flag;
    }

    public ApplicationBrief setFlag(int flag) {
        this.flag = flag;
        return this;
    }

    @Override
    public String toString() {
        return "ApplicationBrief{" +
                "applicationId='" + applicationId + '\'' +
                ", hostId='" + hostId + '\'' +
                ", name='" + name + '\'' +
                ", flag=" + flag +
                '}';
    }
}
