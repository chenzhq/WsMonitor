package com.ws.stoner.model.brief;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by pc on 2017/6/13.
 */
public class ItemBrief {
    @JSONField(name = "itemid")
    private String itemId;
    private Integer delay;
    @JSONField(name = "hostid")
    private String hostId;
    @JSONField(name = "interfaceid")
    private String interfaceId;
    private String key_;
    private String name;
    private Integer type;
    private Integer flags;

    private String port;
    @JSONField(name = "snmp_oid")
    private String snmpOid;
    private Integer state;
    private Integer status;
    @JSONField(name = "templateid")
    private String templateId;
    private Integer trends;

    @Override
    public String toString() {
        return "ItemBrief{" +
                "itemId='" + itemId + '\'' +
                ", delay=" + delay +
                ", hostId='" + hostId + '\'' +
                ", interfaceId='" + interfaceId + '\'' +
                ", key_='" + key_ + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", flags=" + flags +
                ", port='" + port + '\'' +
                ", snmpOid='" + snmpOid + '\'' +
                ", state=" + state +
                ", status=" + status +
                ", templateId='" + templateId + '\'' +
                ", trends=" + trends +
                '}';
    }

    public String getItemId() {
        return itemId;
    }

    public ItemBrief setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public Integer getDelay() {
        return delay;
    }

    public ItemBrief setDelay(Integer delay) {
        this.delay = delay;
        return this;
    }

    public String getHostId() {
        return hostId;
    }

    public ItemBrief setHostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public ItemBrief setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
        return this;
    }

    public String getKey_() {
        return key_;
    }

    public ItemBrief setKey_(String key_) {
        this.key_ = key_;
        return this;
    }

    public String getName() {
        return name;
    }

    public ItemBrief setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public ItemBrief setType(Integer type) {
        this.type = type;
        return this;
    }

    public Integer getFlags() {
        return flags;
    }

    public ItemBrief setFlags(Integer flags) {
        this.flags = flags;
        return this;
    }

    public String getPort() {
        return port;
    }

    public ItemBrief setPort(String port) {
        this.port = port;
        return this;
    }

    public String getSnmpOid() {
        return snmpOid;
    }

    public ItemBrief setSnmpOid(String snmpOid) {
        this.snmpOid = snmpOid;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public ItemBrief setState(Integer state) {
        this.state = state;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ItemBrief setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getTemplateId() {
        return templateId;
    }

    public ItemBrief setTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public Integer getTrends() {
        return trends;
    }

    public ItemBrief setTrends(Integer trends) {
        this.trends = trends;
        return this;
    }
}
