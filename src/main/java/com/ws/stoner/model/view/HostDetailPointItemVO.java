package com.ws.stoner.model.view;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Created by pc on 2017/7/13.
 */
public class HostDetailPointItemVO {

    @JSONField(name = "item_id")
    private String itemId;
    @JSONField(name = "item_name")
    private String name;
    private String value;
    private String units;
    private String state;
    @JSONField(name = "with_triggers")
    private boolean withTriggers;
    @JSONField(name = "last_time")
    private String lastTime;
    @JSONField(name = "warning_point",serialzeFeatures = SerializerFeature.WriteNullStringAsEmpty)
    private String warningPoint;
    @JSONField(name = "high_point",serialzeFeatures = SerializerFeature.WriteNullStringAsEmpty)
    private String highPoint;

    public String getUnits() {
        return units;
    }

    public HostDetailPointItemVO setUnits(String units) {
        this.units = units;
        return this;
    }

    public String getItemId() {
        return itemId;
    }

    public HostDetailPointItemVO setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public boolean isWithTriggers() {
        return withTriggers;
    }

    public HostDetailPointItemVO setWithTriggers(boolean withTriggers) {
        this.withTriggers = withTriggers;
        return this;
    }

    public String getName() {
        return name;
    }

    public HostDetailPointItemVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public HostDetailPointItemVO setValue(String value) {
        this.value = value;
        return this;
    }

    public String getState() {
        return state;
    }

    public HostDetailPointItemVO setState(String state) {
        this.state = state;
        return this;
    }

    public String getLastTime() {
        return lastTime;
    }

    public HostDetailPointItemVO setLastTime(String lastTime) {
        this.lastTime = lastTime;
        return this;
    }

    public String getWarningPoint() {
        return warningPoint;
    }

    public HostDetailPointItemVO setWarningPoint(String warningPoint) {
        this.warningPoint = warningPoint;
        return this;
    }

    public String getHighPoint() {
        return highPoint;
    }

    public HostDetailPointItemVO setHighPoint(String highPoint) {
        this.highPoint = highPoint;
        return this;
    }

    @Override
    public String toString() {
        return "HostDetailPointItemVO{" +
                "itemId='" + itemId + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", units='" + units + '\'' +
                ", state='" + state + '\'' +
                ", withTriggers=" + withTriggers +
                ", lastTime='" + lastTime + '\'' +
                ", warningPoint='" + warningPoint + '\'' +
                ", highPoint='" + highPoint + '\'' +
                '}';
    }
}
