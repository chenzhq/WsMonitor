package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by pc on 2017/7/3.
 */
public class OverviewDelGroupDTO {

    @JSONField(name = "del_group")
    private String delGroupId;

    @JSONField(name = "to_group")
    private String toGroupId;

    @Override
    public String toString() {
        return "OverviewDelGroupDTO{" +
                "delGroupId='" + delGroupId + '\'' +
                ", toGroupId='" + toGroupId + '\'' +
                '}';
    }

    public String getDelGroupId() {
        return delGroupId;
    }

    public OverviewDelGroupDTO setDelGroupId(String delGroupId) {
        this.delGroupId = delGroupId;
        return this;
    }

    public String getToGroupId() {
        return toGroupId;
    }

    public OverviewDelGroupDTO setToGroupId(String toGroupId) {
        this.toGroupId = toGroupId;
        return this;
    }
}
