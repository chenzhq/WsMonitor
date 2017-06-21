package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.time.Instant;

/**
 * Created by pc on 2017/6/20.
 */
public class BriefItemDTO {
    @JSONField(name = "itemid")
    private String itemId;
    private String name;
    @JSONField(name = "lastclock")
    private Instant lastTime;

    public static final String[] PROPERTY_NAMES = {"itemid", "name","lastclock"};

    @Override
    public String toString() {
        return "BriefItemDTO{" +
                "itemId='" + itemId + '\'' +
                ", name='" + name + '\'' +
                ", lastTime=" + lastTime +
                '}';
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

    public Instant getLastTime() {
        return lastTime;
    }

    public BriefItemDTO setLastTime(int lastTime) {
        this.lastTime = Instant.ofEpochSecond(lastTime);
        return this;
    }
}
