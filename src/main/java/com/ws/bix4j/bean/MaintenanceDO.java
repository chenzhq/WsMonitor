package com.ws.bix4j.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.time.Instant;

/**
 * Created by chenzheqi on 2017/5/2.
 */
public class MaintenanceDO {
    @JSONField(name = "maintenanceid")
    private String maintenanceId;
    private String name;
    @JSONField(name = "active_since")
    private Instant activeSince;
    @JSONField(name = "active_till")
    private Instant activeTill;
    private String description;
    @JSONField(name = "maintenance_type")
    private int maintenanceType;
}
