package com.ws.bix4j.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.time.Instant;

/**
 * Created by pc on 2017/6/9.
 */
public class ItemDO {
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
    @JSONField(name = "value_type")
    private Integer valueType;
    @JSONField(name = "authtype")
    private Integer authType;
    @JSONField(name = "data_type")
    private Integer dataType;
    @JSONField(name = "delay_flex")
    private String delayFlex;
    private Integer delta;
    private String description;
    private String error;
    private Integer flags;
    private Float formula;
    private Integer history;
    @JSONField(name = "inventory_link")
    private Integer inventoryLink;
    @JSONField(name = "ipmi_sensor")
    private String ipmiSensor;
    private Instant lastclock;
    private Integer lastns;
    private String lastvalue;
    private String logtimefmt;
    private Instant mtime;
    private Integer multiplier;
    private String params;
    private String password;
    private String port;
    private String prevvalue;
    private String privatekey;
    private String publickey;
    @JSONField(name = "snmp_community")
    private String snmpCommunity;
    @JSONField(name = "snmp_oid")
    private String snmpOid;
    @JSONField(name = "snmpv3_authpassphrase")
    private String snmpv3Authpassphrase;
    @JSONField(name = "snmpv3_authprotocol")
    private Integer snmpv3Authprotocol;
    @JSONField(name = "snmpv3_contextname")
    private String  snmpv3Contextname;
    @JSONField(name = "snmpv3_privpassphrase")
    private String snmpv3Privpassphrase;
    @JSONField(name = "snmpv3_privprotocol")
    private Integer snmpv3Privprotocol;
    @JSONField(name = "snmpv3_securitylevel")
    private Integer snmpv3Securitylevel;
    @JSONField(name = "snmpv3_securityname")
    private String snmpv3Securityname;
    private Integer state;
    private Integer status;
    @JSONField(name = "templateid")
    private String templateId;
    @JSONField(name = "trapper_hosts")
    private String trapperHosts;
    private Integer trends;
    private String units;
    private String username;
    @JSONField(name = "valuemapid")
    private  String valuemapId;

    public String getItemId() {
        return itemId;
    }

    public ItemDO setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public Integer getDelay() {
        return delay;
    }

    public ItemDO setDelay(Integer delay) {
        this.delay = delay;
        return this;
    }

    public String getHostId() {
        return hostId;
    }

    public ItemDO setHostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public ItemDO setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
        return this;
    }

    public String getKey_() {
        return key_;
    }

    public ItemDO setKey_(String key_) {
        this.key_ = key_;
        return this;
    }

    public String getName() {
        return name;
    }

    public ItemDO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public ItemDO setType(Integer type) {
        this.type = type;
        return this;
    }

    public Integer getValueType() {
        return valueType;
    }

    public ItemDO setValueType(Integer valueType) {
        this.valueType = valueType;
        return this;
    }

    public Integer getAuthType() {
        return authType;
    }

    public ItemDO setAuthType(Integer authType) {
        this.authType = authType;
        return this;
    }

    public Integer getDataType() {
        return dataType;
    }

    public ItemDO setDataType(Integer dataType) {
        this.dataType = dataType;
        return this;
    }

    public String getDelayFlex() {
        return delayFlex;
    }

    public ItemDO setDelayFlex(String delayFlex) {
        this.delayFlex = delayFlex;
        return this;
    }

    public Integer getDelta() {
        return delta;
    }

    public ItemDO setDelta(Integer delta) {
        this.delta = delta;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ItemDO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getError() {
        return error;
    }

    public ItemDO setError(String error) {
        this.error = error;
        return this;
    }

    public Integer getFlags() {
        return flags;
    }

    public ItemDO setFlags(Integer flags) {
        this.flags = flags;
        return this;
    }

    public Float getFormula() {
        return formula;
    }

    public ItemDO setFormula(Float formula) {
        this.formula = formula;
        return this;
    }

    public Integer getHistory() {
        return history;
    }

    public ItemDO setHistory(Integer history) {
        this.history = history;
        return this;
    }

    public Integer getInventoryLink() {
        return inventoryLink;
    }

    public ItemDO setInventoryLink(Integer inventoryLink) {
        this.inventoryLink = inventoryLink;
        return this;
    }

    public String getIpmiSensor() {
        return ipmiSensor;
    }

    public ItemDO setIpmiSensor(String ipmiSensor) {
        this.ipmiSensor = ipmiSensor;
        return this;
    }

    public Instant getLastclock() {
        return lastclock;
    }

    public ItemDO setLastclock(int lastclock) {
        this.lastclock = Instant.ofEpochSecond(lastclock);
        return this;
    }

    public Integer getLastns() {
        return lastns;
    }

    public ItemDO setLastns(Integer lastns) {
        this.lastns = lastns;
        return this;
    }

    public String getLastvalue() {
        return lastvalue;
    }

    public ItemDO setLastvalue(String lastvalue) {
        this.lastvalue = lastvalue;
        return this;
    }

    public String getLogtimefmt() {
        return logtimefmt;
    }

    public ItemDO setLogtimefmt(String logtimefmt) {
        this.logtimefmt = logtimefmt;
        return this;
    }

    public Instant getMtime() {
        return mtime;
    }

    public ItemDO setMtime(int mtime) {
        this.mtime = Instant.ofEpochSecond(mtime);
        return this;
    }

    public Integer getMultiplier() {
        return multiplier;
    }

    public ItemDO setMultiplier(Integer multiplier) {
        this.multiplier = multiplier;
        return this;
    }

    public String getParams() {
        return params;
    }

    public ItemDO setParams(String params) {
        this.params = params;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ItemDO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPort() {
        return port;
    }

    public ItemDO setPort(String port) {
        this.port = port;
        return this;
    }

    public String getPrevvalue() {
        return prevvalue;
    }

    public ItemDO setPrevvalue(String prevvalue) {
        this.prevvalue = prevvalue;
        return this;
    }

    public String getPrivatekey() {
        return privatekey;
    }

    public ItemDO setPrivatekey(String privatekey) {
        this.privatekey = privatekey;
        return this;
    }

    public String getPublickey() {
        return publickey;
    }

    public ItemDO setPublickey(String publickey) {
        this.publickey = publickey;
        return this;
    }

    public String getSnmpCommunity() {
        return snmpCommunity;
    }

    public ItemDO setSnmpCommunity(String snmpCommunity) {
        this.snmpCommunity = snmpCommunity;
        return this;
    }

    public String getSnmpOid() {
        return snmpOid;
    }

    public ItemDO setSnmpOid(String snmpOid) {
        this.snmpOid = snmpOid;
        return this;
    }

    public String getSnmpv3Authpassphrase() {
        return snmpv3Authpassphrase;
    }

    public ItemDO setSnmpv3Authpassphrase(String snmpv3Authpassphrase) {
        this.snmpv3Authpassphrase = snmpv3Authpassphrase;
        return this;
    }

    public Integer getSnmpv3Authprotocol() {
        return snmpv3Authprotocol;
    }

    public ItemDO setSnmpv3Authprotocol(Integer snmpv3Authprotocol) {
        this.snmpv3Authprotocol = snmpv3Authprotocol;
        return this;
    }

    public String getSnmpv3Contextname() {
        return snmpv3Contextname;
    }

    public ItemDO setSnmpv3Contextname(String snmpv3Contextname) {
        this.snmpv3Contextname = snmpv3Contextname;
        return this;
    }

    public String getSnmpv3Privpassphrase() {
        return snmpv3Privpassphrase;
    }

    public ItemDO setSnmpv3Privpassphrase(String snmpv3Privpassphrase) {
        this.snmpv3Privpassphrase = snmpv3Privpassphrase;
        return this;
    }

    public Integer getSnmpv3Privprotocol() {
        return snmpv3Privprotocol;
    }

    public ItemDO setSnmpv3Privprotocol(Integer snmpv3Privprotocol) {
        this.snmpv3Privprotocol = snmpv3Privprotocol;
        return this;
    }

    public Integer getSnmpv3Securitylevel() {
        return snmpv3Securitylevel;
    }

    public ItemDO setSnmpv3Securitylevel(Integer snmpv3Securitylevel) {
        this.snmpv3Securitylevel = snmpv3Securitylevel;
        return this;
    }

    public String getSnmpv3Securityname() {
        return snmpv3Securityname;
    }

    public ItemDO setSnmpv3Securityname(String snmpv3Securityname) {
        this.snmpv3Securityname = snmpv3Securityname;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public ItemDO setState(Integer state) {
        this.state = state;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ItemDO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getTemplateId() {
        return templateId;
    }

    public ItemDO setTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public String getTrapperHosts() {
        return trapperHosts;
    }

    public ItemDO setTrapperHosts(String trapperHosts) {
        this.trapperHosts = trapperHosts;
        return this;
    }

    public Integer getTrends() {
        return trends;
    }

    public ItemDO setTrends(Integer trends) {
        this.trends = trends;
        return this;
    }

    public String getUnits() {
        return units;
    }

    public ItemDO setUnits(String units) {
        this.units = units;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ItemDO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getValuemapId() {
        return valuemapId;
    }

    public ItemDO setValuemapId(String valuemapId) {
        this.valuemapId = valuemapId;
        return this;
    }

    @Override
    public String toString() {
        return "ItemDO{" +
                "itemId='" + itemId + '\'' +
                ", delay=" + delay +
                ", hostId='" + hostId + '\'' +
                ", interfaceId='" + interfaceId + '\'' +
                ", key_='" + key_ + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", valueType=" + valueType +
                ", authType=" + authType +
                ", dataType=" + dataType +
                ", delayFlex='" + delayFlex + '\'' +
                ", delta=" + delta +
                ", description='" + description + '\'' +
                ", error='" + error + '\'' +
                ", flags=" + flags +
                ", formula=" + formula +
                ", history=" + history +
                ", inventoryLink=" + inventoryLink +
                ", ipmiSensor='" + ipmiSensor + '\'' +
                ", lastclock=" + lastclock +
                ", lastns=" + lastns +
                ", lastvalue='" + lastvalue + '\'' +
                ", logtimefmt='" + logtimefmt + '\'' +
                ", mtime=" + mtime +
                ", multiplier=" + multiplier +
                ", params='" + params + '\'' +
                ", password='" + password + '\'' +
                ", port='" + port + '\'' +
                ", prevvalue='" + prevvalue + '\'' +
                ", privatekey='" + privatekey + '\'' +
                ", publickey='" + publickey + '\'' +
                ", snmpCommunity='" + snmpCommunity + '\'' +
                ", snmpOid='" + snmpOid + '\'' +
                ", snmpv3Authpassphrase='" + snmpv3Authpassphrase + '\'' +
                ", snmpv3Authprotocol=" + snmpv3Authprotocol +
                ", snmpv3Contextname='" + snmpv3Contextname + '\'' +
                ", snmpv3Privpassphrase='" + snmpv3Privpassphrase + '\'' +
                ", snmpv3Privprotocol=" + snmpv3Privprotocol +
                ", snmpv3Securitylevel=" + snmpv3Securitylevel +
                ", snmpv3Securityname='" + snmpv3Securityname + '\'' +
                ", state=" + state +
                ", status=" + status +
                ", templateId='" + templateId + '\'' +
                ", trapperHosts='" + trapperHosts + '\'' +
                ", trends=" + trends +
                ", units='" + units + '\'' +
                ", username='" + username + '\'' +
                ", valuemapId='" + valuemapId + '\'' +
                '}';
    }
}
