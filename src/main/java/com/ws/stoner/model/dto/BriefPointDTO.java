package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by pc on 2017/6/16.
 */
public class BriefPointDTO {
    @JSONField(name = "applicationid")
    private String pointId;
    private String name;
    @JSONField(name = "hostid")
    private String hostId;
    private BriefHostDTO host;
    private List<BriefItemDTO> items;

    public static final String[] PROPERTY_NAMES = {"applicationid","name","hostid"};

    @Override
    public String toString() {
        return "BriefPointDTO{" +
                "pointId='" + pointId + '\'' +
                ", name='" + name + '\'' +
                ", hostId='" + hostId + '\'' +
                ", host=" + host +
                '}';
    }

    public List<BriefItemDTO> getItems() {
        return items;
    }

    public BriefPointDTO setItems(List<BriefItemDTO> items) {
        this.items = items;
        return this;
    }

    public String getPointId() {
        return pointId;
    }

    public BriefPointDTO setPointId(String pointId) {
        this.pointId = pointId;
        return this;
    }

    public String getName() {
        return name;
    }

    public BriefPointDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getHostId() {
        return hostId;
    }

    public BriefPointDTO setHostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public BriefHostDTO getHost() {
        return host;
    }

    public BriefPointDTO setHost(BriefHostDTO host) {
        this.host = host;
        return this;
    }


}
