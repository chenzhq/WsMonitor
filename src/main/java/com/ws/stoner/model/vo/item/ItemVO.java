package com.ws.stoner.model.vo.item;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Created by zhongkf on 2017/12/8
 */
public class ItemVO {

    private String id;
    private String name;
    @JSONField(serialzeFeatures = SerializerFeature.WriteNullStringAsEmpty)
    private String state;
    private String lastTime;
    private String valueType;
    private String unit;
    private Integer weight;
    private boolean isCore;

    public ItemVO() {
    }

    public ItemVO(String id, String name, String state, boolean isCore) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.isCore = isCore;
    }

    @Override
    public String toString() {
        return "ItemVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", lastTime='" + lastTime + '\'' +
                ", valueType='" + valueType + '\'' +
                ", unit='" + unit + '\'' +
                ", weight=" + weight +
                ", isCore=" + isCore +
                '}';
    }

    public String getId() {
        return id;
    }

    public ItemVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ItemVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getState() {
        return state;
    }

    public ItemVO setState(String state) {
        this.state = state;
        return this;
    }

    public String getLastTime() {
        return lastTime;
    }

    public ItemVO setLastTime(String lastTime) {
        this.lastTime = lastTime;
        return this;
    }

    public String getValueType() {
        return valueType;
    }

    public ItemVO setValueType(String valueType) {
        this.valueType = valueType;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public ItemVO setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public Integer getWeight() {
        return weight;
    }

    public ItemVO setWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public boolean isCore() {
        return isCore;
    }

    public ItemVO setCore(boolean core) {
        isCore = core;
        return this;
    }

}
