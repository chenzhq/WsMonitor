package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by zkf on 2017/7/12.
 */
public class BriefHistoryDTO {

    @JSONField(name = "itemid")
    private String itemId;
    @JSONField(name = "clock")
    private LocalDateTime lastTime;
    private String value;

    public static final String[] PROPERTY_NAMES = {"itemid", "clock","value"};

    public String getItemId() {
        return itemId;
    }

    public BriefHistoryDTO setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public LocalDateTime getLastTime() {
        return lastTime;
    }

    public BriefHistoryDTO setLastTime(int lastTime) {
        Instant instant = Instant.ofEpochSecond(lastTime);
        this.lastTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return this;
    }

    public String getValue() {
        return value;
    }

    public BriefHistoryDTO setValue(String value) {
        this.value = value;
        return this;
    }
}
