package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Created by pc on 2017/6/29.
 */
public class OverviewListGroupDTO {

    @JSONField(name = "cid" )
    private String cId;
    @JSONField(name = "pid", serialzeFeatures = SerializerFeature.WriteNullStringAsEmpty)
    private String pId;
    private String name;
    @JSONField(serialzeFeatures = SerializerFeature.WriteNullStringAsEmpty)
    private String state;
    private String type;

    @Override
    public String toString() {
        return "OverviewListGroupDTO{" +
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

    public OverviewListGroupDTO setName(String name) {
        this.name = name;
        return this;
    }


    public String getcId() {
        return cId;
    }

    public OverviewListGroupDTO setcId(String cId) {
        this.cId = cId;
        return this;
    }

    public String getpId() {
        return pId;
    }

    public OverviewListGroupDTO setpId(String pId) {
        this.pId = pId;
        return this;
    }

    public String getState() {
        return state;
    }

    public OverviewListGroupDTO setState(String state) {
        this.state = state;
        return this;
    }

    public String getType() {
        return type;
    }

    public OverviewListGroupDTO setType(String type) {
        this.type = type;
        return this;
    }
}
