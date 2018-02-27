package com.ws.stoner.model;

import java.util.List;

/**
 * Created by zhongkf on 2018/2/6
 */
public class ExcelDDLVO {
    private String ddlName;
    private String itemType;
    private String key;
    private String SNMPOID;
    private String SNMPCommunity;
    private String delay;
    private String ipmiSensor;
    private String lifetime;
    private String description;

    private List<ExcelItemVO> items;

    public String getDdlName() {
        return ddlName;
    }

    public ExcelDDLVO setDdlName(String ddlName) {
        this.ddlName = ddlName;
        return this;
    }

    public String getItemType() {
        return itemType;
    }

    public ExcelDDLVO setItemType(String itemType) {
        this.itemType = itemType;
        return this;
    }

    public String getKey() {
        return key;
    }

    public ExcelDDLVO setKey(String key) {
        this.key = key;
        return this;
    }

    public String getSNMPOID() {
        return SNMPOID;
    }

    public ExcelDDLVO setSNMPOID(String SNMPOID) {
        this.SNMPOID = SNMPOID;
        return this;
    }

    public String getSNMPCommunity() {
        return SNMPCommunity;
    }

    public ExcelDDLVO setSNMPCommunity(String SNMPCommunity) {
        this.SNMPCommunity = SNMPCommunity;
        return this;
    }

    public String getDelay() {
        return delay;
    }

    public ExcelDDLVO setDelay(String delay) {
        this.delay = delay;
        return this;
    }

    public String getIpmiSensor() {
        return ipmiSensor;
    }

    public ExcelDDLVO setIpmiSensor(String ipmiSensor) {
        this.ipmiSensor = ipmiSensor;
        return this;
    }

    public String getLifetime() {
        return lifetime;
    }

    public ExcelDDLVO setLifetime(String lifetime) {
        this.lifetime = lifetime;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ExcelDDLVO setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<ExcelItemVO> getItems() {
        return items;
    }

    public ExcelDDLVO setItems(List<ExcelItemVO> items) {
        this.items = items;
        return this;
    }
}
