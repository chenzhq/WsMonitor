package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by pc on 2017/7/3.
 */
public class OverviewMoveGroupDTO {

    @JSONField(name = "group")
    private String groupId;
    @JSONField(name = "from_group")
    private String fromGroupId;
    @JSONField(name = "to_group")
    private String toGroupId;


    @Override
    public String toString() {
        return "OverviewMoveGroupDTO{" +
                "groupId='" + groupId + '\'' +
                ", fromGroupId='" + fromGroupId + '\'' +
                ", toGroupId='" + toGroupId + '\'' +
                '}';
    }

    public String getGroupId() {
        return groupId;
    }

    public OverviewMoveGroupDTO setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getFromGroupId() {
        return fromGroupId;
    }

    public OverviewMoveGroupDTO setFromGroupId(String fromGroupId) {
        this.fromGroupId = fromGroupId;
        return this;
    }

    public String getToGroupId() {
        return toGroupId;
    }

    public OverviewMoveGroupDTO setToGroupId(String toGroupId) {
        this.toGroupId = toGroupId;
        return this;
    }
}
