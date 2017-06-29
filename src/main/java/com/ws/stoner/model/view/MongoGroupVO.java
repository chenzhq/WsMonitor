package com.ws.stoner.model.view;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by pc on 2017/6/29.
 */
public class MongoGroupVO {

    @JSONField(name = "cid")
    private String cId;
    @JSONField(name = "pid")
    private String pId;
    private String name;
    private String state;
    private String type;

    @Override
    public String toString() {
        return "MongoGroupVO{" +
                "cId='" + cId + '\'' +
                ", pId='" + pId + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", type='" + type + '\'' +
                '}';
    }


    public String getName() {
        return name;
    }

    public MongoGroupVO setName(String name) {
        this.name = name;
        return this;
    }


    public String getcId() {
        return cId;
    }

    public MongoGroupVO setcId(String cId) {
        this.cId = cId;
        return this;
    }

    public String getpId() {
        return pId;
    }

    public MongoGroupVO setpId(String pId) {
        this.pId = pId;
        return this;
    }

    public String getState() {
        return state;
    }

    public MongoGroupVO setState(String state) {
        this.state = state;
        return this;
    }

    public String getType() {
        return type;
    }

    public MongoGroupVO setType(String type) {
        this.type = type;
        return this;
    }
}
