package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by pc on 2017/7/3.
 */
public class OverviewMoveHostDTO {

    @JSONField(name = "from_group")
    private String fromGroupId;
    @JSONField(name = "host")
    private  String hostId;
    @JSONField(name = "to_group")
    private String toGroupId;

    @Override
    public String toString() {
        return "OverviewMoveHostDTO{" +
                "fromGroupId='" + fromGroupId + '\'' +
                ", hostId='" + hostId + '\'' +
                ", toGroupId='" + toGroupId + '\'' +
                '}';
    }

    public String getFromGroupId() {
        return fromGroupId;
    }

    public OverviewMoveHostDTO setFromGroupId(String fromGroupId) {
        this.fromGroupId = fromGroupId;
        return this;
    }

    public String getHostId() {
        return hostId;
    }

    public OverviewMoveHostDTO setHostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public String getToGroupId() {
        return toGroupId;
    }

    public OverviewMoveHostDTO setToGroupId(String toGroupId) {
        this.toGroupId = toGroupId;
        return this;
    }
}
