package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by chenzheqi on 2017/6/13.
 */
public class BriefHostDTO {
    @JSONField(name = "hostid")
    private String hostId;
    private String host;
    private String name;
    private List<BriefTemplateDTO> parentTemplates; //通过模板映射类型
    private List<BriefHostInterfaceDTO> interfaces; //通过接口获取ip

    public static final String[] PROPERTY_NAMES = {"hostid", "name"};

    @Override
    public String toString() {
        return "BriefHostDTO{" +
                "hostId='" + hostId + '\'' +
                ", host='" + host + '\'' +
                ", name='" + name + '\'' +
                ", parentTemplates=" + parentTemplates +
                ", interfaces=" + interfaces +
                '}';
    }

    public String getHostId() {
        return hostId;
    }

    public BriefHostDTO setHostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public String getHost() {
        return host;
    }

    public BriefHostDTO setHost(String host) {
        this.host = host;
        return this;
    }

    public String getName() {
        return name;
    }

    public BriefHostDTO setName(String name) {
        this.name = name;
        return this;
    }

    public List<BriefTemplateDTO> getParentTemplates() {
        return parentTemplates;
    }

    public BriefHostDTO setParentTemplates(List<BriefTemplateDTO> parentTemplates) {
        this.parentTemplates = parentTemplates;
        return this;
    }

    public List<BriefHostInterfaceDTO> getInterfaces() {
        return interfaces;
    }

    public BriefHostDTO setInterfaces(List<BriefHostInterfaceDTO> interfaces) {
        this.interfaces = interfaces;
        return this;
    }

    public static String[] getPropertyNames() {
        return PROPERTY_NAMES;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BriefHostDTO that = (BriefHostDTO) o;

        if (hostId != null ? !hostId.equals(that.hostId) : that.hostId != null) return false;
        if (host != null ? !host.equals(that.host) : that.host != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (parentTemplates != null ? !parentTemplates.equals(that.parentTemplates) : that.parentTemplates != null)
            return false;
        return interfaces != null ? interfaces.equals(that.interfaces) : that.interfaces == null;
    }

    @Override
    public int hashCode() {
        int result = hostId != null ? hostId.hashCode() : 0;
        result = 31 * result + (host != null ? host.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (parentTemplates != null ? parentTemplates.hashCode() : 0);
        result = 31 * result + (interfaces != null ? interfaces.hashCode() : 0);
        return result;
    }
}
