package com.ws.stoner.model.brief;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by pc on 2017/6/13.
 */
public class HostGroupBrief {
    @JSONField(name = "groupid")
    private String groupId;
    private String name;

    private int flags;
    private int internal;

    public String getGroupId() {
        return groupId;
    }

    public HostGroupBrief setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getName() {
        return name;
    }

    public HostGroupBrief setName(String name) {
        this.name = name;
        return this;
    }

    public int getFlags() {
        return flags;
    }

    public HostGroupBrief setFlags(int flags) {
        this.flags = flags;
        return this;
    }

    public int getInternal() {
        return internal;
    }

    public HostGroupBrief setInternal(int internal) {
        this.internal = internal;
        return this;
    }

    @Override
    public String toString() {
        return "HostGroupBrief{" +
                "groupId='" + groupId + '\'' +
                ", name='" + name + '\'' +
                ", flags=" + flags +
                ", internal=" + internal +
                '}';
    }
}
