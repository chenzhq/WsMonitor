package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * Created by pc on 2017/6/20.
 */
public class BriefItemDTO {
    @JSONField(name = "itemid")
    private String itemId;
    private String name;
    @JSONField(name = "custom_state")
    private int customState;
    @JSONField(name = "lastclock",format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastTime;
    @JSONField(name = "lastvalue")
    private String lastValue;
    private String units;
    @JSONField(name = "value_type")
    private String valueType;
    @JSONField(name = "applications")
    private List<BriefPointDTO> points;


    public static final String[] PROPERTY_NAMES = {"itemid", "name","lastclock","custom_state","lastvalue","value_type","units"};

    public int getCustomState() {
        return customState;
    }

    public BriefItemDTO setCustomState(int customState) {
        this.customState = customState;
        return this;
    }

    @Override
    public String toString() {
        return "BriefItemDTO{" +
                "itemId='" + itemId + '\'' +
                ", name='" + name + '\'' +
                ", customState=" + customState +
                ", lastTime=" + lastTime +
                ", lastValue='" + lastValue + '\'' +
                ", units='" + units + '\'' +
                ", valueType=" + valueType +
                ", points=" + points +
                '}';
    }

    public List<BriefPointDTO> getPoints() {
        return points;
    }

    public BriefItemDTO setPoints(List<BriefPointDTO> points) {
        this.points = points;
        return this;
    }

    public String getValueType() {
        return valueType;
    }

    public BriefItemDTO setValueType(String valueType) {
        this.valueType = valueType;
        return this;
    }

    public String getLastValue() {
        return lastValue;
    }

    public BriefItemDTO setLastValue(String lastValue) {
        this.lastValue = lastValue;
        return this;
    }

    public String getItemId() {
        return itemId;
    }

    public BriefItemDTO setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getName() {
        return name;
    }

    public BriefItemDTO setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDateTime getLastTime() {
        return lastTime;
    }

    public BriefItemDTO setLastTime(int lastTime) {
        if(lastTime == 0) {
            this.lastTime = null;
        }else {
            Instant instant = Instant.ofEpochSecond(lastTime);
            this.lastTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        }

        return this;
    }

    public String getUnits() {
        return units;
    }

    public BriefItemDTO setUnits(String units) {
        this.units = units;
        return this;
    }
}
