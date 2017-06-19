package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by pc on 2017/6/19.
 */
public class BriefTemplateGroupDTO {
    @JSONField(name = "groupid")
    private String groupId;

    private String name;

    public static String[] getPropertyNames() {
        return PROPERTY_NAMES;
    }

    public static final String[] PROPERTY_NAMES = {"groupid", "name"};


    public String getGroupId() {
        return groupId;
    }

    public BriefTemplateGroupDTO setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getName() {
        return name;
    }

    public BriefTemplateGroupDTO setName(String name) {
        this.name = name;
        return this;
    }
}
