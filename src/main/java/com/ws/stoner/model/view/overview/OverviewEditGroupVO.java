package com.ws.stoner.model.view.overview;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by pc on 2017/6/30.
 */
public class OverviewEditGroupVO {

    @JSONField(name = "old_group_name")
    private String oldGroupName;
    @JSONField(name = "new_group_name")
    private String newGroupName;
    @JSONField(name = "sup_group_id")
    private String supGroupId;

    @Override
    public String toString() {
        return "OverviewEditGroupVO{" +
                "oldGroupName='" + oldGroupName + '\'' +
                ", newGroupName='" + newGroupName + '\'' +
                ", supGroupId='" + supGroupId + '\'' +
                '}';
    }

    public OverviewEditGroupVO(String oldGroupName, String newGroupName, String supGroupId) {
        this.oldGroupName = oldGroupName;
        this.newGroupName = newGroupName;
        this.supGroupId = supGroupId;
    }

    public String getOldGroupName() {
        return oldGroupName;
    }

    public OverviewEditGroupVO setOldGroupName(String oldGroupName) {
        this.oldGroupName = oldGroupName;
        return this;
    }

    public String getNewGroupName() {
        return newGroupName;
    }

    public OverviewEditGroupVO setNewGroupName(String newGroupName) {
        this.newGroupName = newGroupName;
        return this;
    }

    public String getSupGroupId() {
        return supGroupId;
    }

    public OverviewEditGroupVO setSupGroupId(String supGroupId) {
        this.supGroupId = supGroupId;
        return this;
    }
}
