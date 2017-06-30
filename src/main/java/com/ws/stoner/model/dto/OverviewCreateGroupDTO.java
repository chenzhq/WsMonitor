package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by pc on 2017/6/30.
 */
public class OverviewCreateGroupDTO {

    @JSONField(name = "new_group")
    private String newGroupId;
    @JSONField(name = "new_group_name")
    private String newGroupName;
    @JSONField(name = "sup_group")
    private String supGroupId;

    @Override
    public String toString() {
        return "OverviewCreateGroupDTO{" +
                "newGroupId='" + newGroupId + '\'' +
                ", newGroupName='" + newGroupName + '\'' +
                ", supGroupId='" + supGroupId + '\'' +
                '}';
    }

    public String getNewGroupId() {
        return newGroupId;
    }

    public OverviewCreateGroupDTO setNewGroupId(String newGroupId) {
        this.newGroupId = newGroupId;
        return this;
    }

    public String getNewGroupName() {
        return newGroupName;
    }

    public OverviewCreateGroupDTO setNewGroupName(String newGroupName) {
        this.newGroupName = newGroupName;
        return this;
    }

    public String getSupGroupId() {
        return supGroupId;
    }

    public OverviewCreateGroupDTO setSupGroupId(String supGroupId) {
        this.supGroupId = supGroupId;
        return this;
    }
}
