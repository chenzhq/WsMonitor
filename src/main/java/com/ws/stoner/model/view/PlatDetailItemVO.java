package com.ws.stoner.model.view;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Created by zkf on 2017/8/9.
 */
public class PlatDetailItemVO {

    @JSONField(name = "item_id")
    private String itemId;
    @JSONField(name = "item_name",serialzeFeatures = SerializerFeature.WriteNullStringAsEmpty)
    private String itemName;
    @JSONField(serialzeFeatures = SerializerFeature.WriteNullStringAsEmpty)
    private String state;
    @JSONField(serialzeFeatures = SerializerFeature.WriteNullStringAsEmpty)
    private String value;
    @JSONField(name = "with_triggers",serialzeFeatures = SerializerFeature.WriteNullStringAsEmpty)
    private boolean withTriggers;
    @JSONField(serialzeFeatures = SerializerFeature.WriteNullStringAsEmpty)
    private Integer weight;

    public PlatDetailItemVO(String itemId, String itemName, String state, String value, boolean withTriggers, Integer weight) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.state = state;
        this.value = value;
        this.withTriggers = withTriggers;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "PlatDetailItemVO{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", state='" + state + '\'' +
                ", value='" + value + '\'' +
                ", withTriggers=" + withTriggers +
                ", weight=" + weight +
                '}';
    }

    public String getItemId() {
        return itemId;
    }

    public PlatDetailItemVO setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public PlatDetailItemVO setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public String getState() {
        return state;
    }

    public PlatDetailItemVO setState(String state) {
        this.state = state;
        return this;
    }

    public String getValue() {
        return value;
    }

    public PlatDetailItemVO setValue(String value) {
        this.value = value;
        return this;
    }

    public boolean isWithTriggers() {
        return withTriggers;
    }

    public PlatDetailItemVO setWithTriggers(boolean withTriggers) {
        this.withTriggers = withTriggers;
        return this;
    }

    public Integer getWeight() {
        return weight;
    }

    public PlatDetailItemVO setWeight(Integer weight) {
        this.weight = weight;
        return this;
    }
}
