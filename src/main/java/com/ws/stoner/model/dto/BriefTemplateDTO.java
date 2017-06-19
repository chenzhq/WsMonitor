package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by pc on 2017/6/19.
 */
public class BriefTemplateDTO {

    @JSONField(name = "templateid")
    private String templateId;
    private String name;
    @JSONField(name = "groups")
    private List<BriefTemplateGroupDTO> templateGroups;

    public static final String[] PROPERTY_NAMES = {"templateid", "name"};

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BriefTemplateDTO that = (BriefTemplateDTO) o;

        if (templateId != null ? !templateId.equals(that.templateId) : that.templateId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return templateGroups != null ? templateGroups.equals(that.templateGroups) : that.templateGroups == null;
    }

    @Override
    public String toString() {
        return "BriefTemplateDTO{" +
                "templateId='" + templateId + '\'' +
                ", name='" + name + '\'' +
                ", templateGroups=" + templateGroups +
                '}';
    }

    @Override
    public int hashCode() {
        int result = templateId != null ? templateId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (templateGroups != null ? templateGroups.hashCode() : 0);
        return result;
    }

    public String getTemplateId() {

        return templateId;
    }

    public BriefTemplateDTO setTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public String getName() {
        return name;
    }

    public BriefTemplateDTO setName(String name) {
        this.name = name;
        return this;
    }

    public List<BriefTemplateGroupDTO> getTemplateGroups() {
        return templateGroups;
    }

    public BriefTemplateDTO setTemplateGroups(List<BriefTemplateGroupDTO> templateGroups) {
        this.templateGroups = templateGroups;
        return this;
    }

    public static String[] getPropertyNames() {
        return PROPERTY_NAMES;
    }
}
