package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Arrays;

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
    private Boolean enable;
    @JSONField(name = "group_children")
    private String[] groupChildren;

    @Override
    public String toString() {
        return "OverviewListGroupDTO{" +
                "cId='" + cId + '\'' +
                ", pId='" + pId + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", type='" + type + '\'' +
                ", enable=" + enable +
                ", groupChildren=" + Arrays.toString(groupChildren) +
                '}';
    }

    public Boolean isEnable() {
        return enable;
    }

    public OverviewListGroupDTO setEnable(Boolean enable) {
        this.enable = enable;
        return this;
    }

    public String[] getGroupChildren() {
        return groupChildren;
    }

    public OverviewListGroupDTO setGroupChildren(String[] groupChildren) {
        this.groupChildren = groupChildren;
        return this;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OverviewListGroupDTO groupDTO = (OverviewListGroupDTO) o;

        if (enable != groupDTO.enable) return false;
        if (cId != null ? !cId.equals(groupDTO.cId) : groupDTO.cId != null) return false;
        if (pId != null ? !pId.equals(groupDTO.pId) : groupDTO.pId != null) return false;
        if (name != null ? !name.equals(groupDTO.name) : groupDTO.name != null) return false;
        if (state != null ? !state.equals(groupDTO.state) : groupDTO.state != null) return false;
        if (type != null ? !type.equals(groupDTO.type) : groupDTO.type != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(groupChildren, groupDTO.groupChildren);
    }

    @Override
    public int hashCode() {
        int result = cId != null ? cId.hashCode() : 0;
        result = 31 * result + (pId != null ? pId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (enable ? 1 : 0);
        result = 31 * result + Arrays.hashCode(groupChildren);
        return result;
    }
}
