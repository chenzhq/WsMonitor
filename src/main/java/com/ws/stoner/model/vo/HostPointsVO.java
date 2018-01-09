package com.ws.stoner.model.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/8
 */
public class HostPointsVO {
    private String id;
    private String name;
    private String state;
    private String type;
    @JSONField(name = "details")
    private List<PointVO> points;

    @Override
    public String toString() {
        return "HostPointsVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", type='" + type + '\'' +
                ", points=" + points +
                '}';
    }

    public String getId() {
        return id;
    }

    public HostPointsVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public HostPointsVO setName(String name) {
        this.name = name;
        return this;
    }

    public List<PointVO> getPoints() {
        return points;
    }

    public HostPointsVO setPoints(List<PointVO> points) {
        this.points = points;
        return this;
    }

    public String getState() {
        return state;
    }

    public HostPointsVO setState(String state) {
        this.state = state;
        return this;
    }

    public String getType() {
        return type;
    }

    public HostPointsVO setType(String type) {
        this.type = type;
        return this;
    }

    public HostPointsVO(String id, String name, String state, String type, List<PointVO> points) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.type = type;
        this.points = points;
    }

    public HostPointsVO() {

    }
}
