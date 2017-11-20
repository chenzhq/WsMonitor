package com.ws.stoner.model.view.platform;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zkf on 2017/8/9.
 */
public class PlatDetailPointVO {

    @JSONField(name = "point_id")
    private String pointId;
    @JSONField(name = "point_name")
    private String pointName;
    private String state;

    public PlatDetailPointVO(String pointId, String pointName, String state) {
        this.pointId = pointId;
        this.pointName = pointName;
        this.state = state;
    }

    @Override
    public String toString() {
        return "PlatDetailPointVO{" +
                "pointId='" + pointId + '\'' +
                ", pointName='" + pointName + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public String getPointId() {
        return pointId;
    }

    public PlatDetailPointVO setPointId(String pointId) {
        this.pointId = pointId;
        return this;
    }

    public String getPointName() {
        return pointName;
    }

    public PlatDetailPointVO setPointName(String pointName) {
        this.pointName = pointName;
        return this;
    }

    public String getState() {
        return state;
    }

    public PlatDetailPointVO setState(String state) {
        this.state = state;
        return this;
    }
}
