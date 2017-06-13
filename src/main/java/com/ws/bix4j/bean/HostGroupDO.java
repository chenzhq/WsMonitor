package com.ws.bix4j.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by chenzheqi on 2017/5/2.
 */
public class HostGroupDO {
    @JSONField(name = "groupid")
    private String groupId;
    private String name;
    /**
     * 只读 主机群组来源 <br>
     * 0 普通群组；4 自动发现群组
     */
    private int flags;
    /**
     * 是否内部群组，内部群组不能删除
     */
    private int internal;

    public String getGroupId() {
        return groupId;
    }

    public HostGroupDO setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getName() {
        return name;
    }

    public HostGroupDO setName(String name) {
        this.name = name;
        return this;
    }

    public int getFlags() {
        return flags;
    }


    public int getInternal() {
        return internal;
    }

}
