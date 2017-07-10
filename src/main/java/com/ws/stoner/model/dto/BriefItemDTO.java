package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by pc on 2017/6/20.
 */
public class BriefItemDTO {
    @JSONField(name = "itemid")
    private String itemId;
    private String name;
    @JSONField(name = "custom_state")
    private String customState;
    @JSONField(name = "lastclock",format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastTime;

    public static final String[] PROPERTY_NAMES = {"itemid", "name","lastclock","custom_state"};

    public String getCustomState() {
        return customState;
    }

    public BriefItemDTO setCustomState(String customState) {
        this.customState = customState;
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

    @Override
    public String toString() {
        return "BriefItemDTO{" +
                "itemId='" + itemId + '\'' +
                ", name='" + name + '\'' +
                ", customState='" + customState + '\'' +
                ", lastTime=" + lastTime +
                '}';
    }

    public BriefItemDTO setLastTime(int lastTime) {
        Instant instant = Instant.ofEpochSecond(lastTime);
        this.lastTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return this;
    }
}
