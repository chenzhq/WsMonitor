package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by pc on 2017/6/16.
 */
public class BriefPointDTO {
    @JSONField(name = "applicationid")
    private String pointId;
    private String name;
    private Integer problemNum;
    private String state;

    public static final String[] PROPERTY_NAMES = {"applicationid","name"};

    public String getPointId() {
        return pointId;
    }

    public BriefPointDTO setPointId(String pointId) {
        this.pointId = pointId;
        return this;
    }

    public String getName() {
        return name;
    }

    public BriefPointDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getProblemNum() {
        return problemNum;
    }

    public BriefPointDTO setProblemNum(Integer problemNum) {
        this.problemNum = problemNum;
        return this;
    }

    public String getState() {
        return state;
    }

    public BriefPointDTO setState(String state) {
        this.state = state;
        return this;
    }

    public static String[] getPropertyNames() {
        return PROPERTY_NAMES;
    }


    @Override
    public String toString() {
        return "BriefPointDTO{" +
                "pointId='" + pointId + '\'' +
                ", name='" + name + '\'' +
                ", problemNum=" + problemNum +
                ", state='" + state + '\'' +
                '}';
    }
}
