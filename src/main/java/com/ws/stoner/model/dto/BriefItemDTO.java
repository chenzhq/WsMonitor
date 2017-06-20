package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by pc on 2017/6/20.
 */
public class BriefItemDTO {
    @JSONField(name = "itemid")
    private String itemId;
    private String name;

    public static final String[] PROPERTY_NAMES = {"itemid", "name"};

    @Override
    public String toString() {
        return "BriefItemDTO{" +
                "itemId='" + itemId + '\'' +
                ", name='" + name + '\'' +
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

    public static String[] getPropertyNames() {
        return PROPERTY_NAMES;
    }
}
