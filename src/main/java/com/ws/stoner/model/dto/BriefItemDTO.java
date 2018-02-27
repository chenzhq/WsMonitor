package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * Created by pc on 2017/6/20.
 */
public class BriefItemDTO {
    @JSONField(name = "itemid")
    private String itemId;
    private int delay;
    @JSONField(name = "hostid")
    private String hostId;
    @JSONField(name = "interfaceid")
    private String interfaceId;
    @JSONField(name = "key_")
    private String key;
    private String name;
    private String type;
    @JSONField(name = "value_type")
    private String valueType;
    @JSONField(name = "authtype")
    private String authType;
    @JSONField(name = "data_type")
    private String dataType;
    @JSONField(name = "delay_flex")
    private String delayFlex;
    private Integer delta;
    private String description;
    private String error;
    private Integer flags;
    private Float formula;
    private int history;
    @JSONField(name = "inventory_link")
    private Integer inventoryLink;
    @JSONField(name = "ipmi_sensor")
    private String ipmiSensor;
    private String logtimefmt;
    private int mtime;
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

    @JSONField(name = "custom_state")
    private int customState;
    @JSONField(name = "lastclock",format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastTime;
    @JSONField(name = "lastvalue")
    private String lastValue;
    private Integer weight;

    @JSONField(name = "applications")
    private List<BriefPointDTO> points;
    private BriefHostDTO host;
    private List<BriefTriggerDTO> triggers;


    public static final String[] PROPERTY_NAMES = {"itemid","name","ipmi_sensor","snmp_oid", "lastclock","custom_state","lastvalue","value_type","data_type","units","valuemapid","weight","type","key_","description","hostid","delay","history","trends"};

    public int getCustomState() {
        return customState;
    }

    public BriefItemDTO setCustomState(int customState) {
        this.customState = customState;
        return this;
    }

    public String getType() {
        return type;
    }

    public BriefItemDTO setType(String type) {
        this.type = type;
        return this;
    }

    public String getKey() {
        return key;
    }

    public BriefItemDTO setKey(String key) {
        this.key = key;
        return this;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public BriefItemDTO setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
        return this;
    }

    public String getAuthType() {
        return authType;
    }

    public BriefItemDTO setAuthType(String authType) {
        this.authType = authType;
        return this;
    }

    public String getDelayFlex() {
        return delayFlex;
    }

    public BriefItemDTO setDelayFlex(String delayFlex) {
        this.delayFlex = delayFlex;
        return this;
    }

    public Integer getDelta() {
        return delta;
    }

    public BriefItemDTO setDelta(Integer delta) {
        this.delta = delta;
        return this;
    }

    public String getError() {
        return error;
    }

    public BriefItemDTO setError(String error) {
        this.error = error;
        return this;
    }

    public Integer getFlags() {
        return flags;
    }

    public BriefItemDTO setFlags(Integer flags) {
        this.flags = flags;
        return this;
    }

    public Float getFormula() {
        return formula;
    }

    public BriefItemDTO setFormula(Float formula) {
        this.formula = formula;
        return this;
    }

    public String getSnmpCommunity() {
        return snmpCommunity;
    }

    public BriefItemDTO setSnmpCommunity(String snmpCommunity) {
        this.snmpCommunity = snmpCommunity;
        return this;
    }

    public String getSnmpOid() {
        return snmpOid;
    }

    public BriefItemDTO setSnmpOid(String snmpOid) {
        this.snmpOid = snmpOid;
        return this;
    }

    public String getSnmpv3Authpassphrase() {
        return snmpv3Authpassphrase;
    }

    public BriefItemDTO setSnmpv3Authpassphrase(String snmpv3Authpassphrase) {
        this.snmpv3Authpassphrase = snmpv3Authpassphrase;
        return this;
    }

    public Integer getSnmpv3Authprotocol() {
        return snmpv3Authprotocol;
    }

    public BriefItemDTO setSnmpv3Authprotocol(Integer snmpv3Authprotocol) {
        this.snmpv3Authprotocol = snmpv3Authprotocol;
        return this;
    }

    public String getSnmpv3Contextname() {
        return snmpv3Contextname;
    }

    public BriefItemDTO setSnmpv3Contextname(String snmpv3Contextname) {
        this.snmpv3Contextname = snmpv3Contextname;
        return this;
    }

    public String getSnmpv3Privpassphrase() {
        return snmpv3Privpassphrase;
    }

    public BriefItemDTO setSnmpv3Privpassphrase(String snmpv3Privpassphrase) {
        this.snmpv3Privpassphrase = snmpv3Privpassphrase;
        return this;
    }

    public Integer getSnmpv3Privprotocol() {
        return snmpv3Privprotocol;
    }

    public BriefItemDTO setSnmpv3Privprotocol(Integer snmpv3Privprotocol) {
        this.snmpv3Privprotocol = snmpv3Privprotocol;
        return this;
    }

    public Integer getSnmpv3Securitylevel() {
        return snmpv3Securitylevel;
    }

    public BriefItemDTO setSnmpv3Securitylevel(Integer snmpv3Securitylevel) {
        this.snmpv3Securitylevel = snmpv3Securitylevel;
        return this;
    }

    public String getSnmpv3Securityname() {
        return snmpv3Securityname;
    }

    public BriefItemDTO setSnmpv3Securityname(String snmpv3Securityname) {
        this.snmpv3Securityname = snmpv3Securityname;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public BriefItemDTO setState(Integer state) {
        this.state = state;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public BriefItemDTO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getTemplateId() {
        return templateId;
    }

    public BriefItemDTO setTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public String getTrapperHosts() {
        return trapperHosts;
    }

    public BriefItemDTO setTrapperHosts(String trapperHosts) {
        this.trapperHosts = trapperHosts;
        return this;
    }

    public Integer getTrends() {
        return trends;
    }

    public BriefItemDTO setTrends(Integer trends) {
        this.trends = trends;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public BriefItemDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BriefItemDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<BriefPointDTO> getPoints() {
        return points;
    }

    public BriefItemDTO setPoints(List<BriefPointDTO> points) {
        this.points = points;
        return this;
    }

    public String getValueType() {
        return valueType;
    }

    public BriefItemDTO setValueType(String valueType) {
        this.valueType = valueType;
        return this;
    }

    public String getLastValue() {
        return lastValue;
    }

    public BriefItemDTO setLastValue(String lastValue) {
        this.lastValue = lastValue;
        return this;
    }

    public String getDataType() {
        return dataType;
    }

    public BriefItemDTO setDataType(String dataType) {
        this.dataType = dataType;
        return this;
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

    public LocalDateTime getLastTime() {
        return lastTime;
    }

    public BriefItemDTO setLastTime(int lastTime) {
        if(lastTime == 0) {
            this.lastTime = null;
        }else {
            Instant instant = Instant.ofEpochSecond(lastTime);
            this.lastTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        }

        return this;
    }

    public String getUnits() {
        return units;
    }

    public BriefItemDTO setUnits(String units) {
        this.units = units;
        return this;
    }

    public String getValuemapId() {
        return valuemapId;
    }

    public BriefItemDTO setValuemapId(String valuemapId) {
        this.valuemapId = valuemapId;
        return this;
    }

    public Integer getWeight() {
        return weight;
    }

    public BriefItemDTO setWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public BriefHostDTO getHost() {
        return host;
    }

    public BriefItemDTO setHost(BriefHostDTO host) {
        this.host = host;
        return this;
    }

    public List<BriefTriggerDTO> getTriggers() {
        return triggers;
    }

    public BriefItemDTO setTriggers(List<BriefTriggerDTO> triggers) {
        this.triggers = triggers;
        return this;
    }

    public String getHostId() {
        return hostId;
    }

    public BriefItemDTO setHostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public int getDelay() {
        return delay;
    }

    public BriefItemDTO setDelay(int delay) {
        this.delay = delay;
        return this;
    }

    public int getHistory() {
        return history;
    }

    public BriefItemDTO setHistory(int history) {
        this.history = history;
        return this;
    }

    public Integer getInventoryLink() {
        return inventoryLink;
    }

    public BriefItemDTO setInventoryLink(Integer inventoryLink) {
        this.inventoryLink = inventoryLink;
        return this;
    }

    public String getIpmiSensor() {
        return ipmiSensor;
    }

    public BriefItemDTO setIpmiSensor(String ipmiSensor) {
        this.ipmiSensor = ipmiSensor;
        return this;
    }

    public String getLogtimefmt() {
        return logtimefmt;
    }

    public BriefItemDTO setLogtimefmt(String logtimefmt) {
        this.logtimefmt = logtimefmt;
        return this;
    }

    public int getMtime() {
        return mtime;
    }

    public BriefItemDTO setMtime(int mtime) {
        this.mtime = mtime;
        return this;
    }

    public Integer getMultiplier() {
        return multiplier;
    }

    public BriefItemDTO setMultiplier(Integer multiplier) {
        this.multiplier = multiplier;
        return this;
    }

    public String getParams() {
        return params;
    }

    public BriefItemDTO setParams(String params) {
        this.params = params;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public BriefItemDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPort() {
        return port;
    }

    public BriefItemDTO setPort(String port) {
        this.port = port;
        return this;
    }

    public String getPrevvalue() {
        return prevvalue;
    }

    public BriefItemDTO setPrevvalue(String prevvalue) {
        this.prevvalue = prevvalue;
        return this;
    }

    public String getPrivatekey() {
        return privatekey;
    }

    public BriefItemDTO setPrivatekey(String privatekey) {
        this.privatekey = privatekey;
        return this;
    }

    public String getPublickey() {
        return publickey;
    }

    public BriefItemDTO setPublickey(String publickey) {
        this.publickey = publickey;
        return this;
    }


    @Override
    public String toString() {
        return "BriefItemDTO{" +
                "itemId='" + itemId + '\'' +
                ", name='" + name + '\'' +
                ", valuemapId='" + valuemapId + '\'' +
                ", customState=" + customState +
                ", lastTime=" + lastTime +
                ", lastValue='" + lastValue + '\'' +
                ", units='" + units + '\'' +
                ", valueType='" + valueType + '\'' +
                ", dataType='" + dataType + '\'' +
                ", weight=" + weight +
                ", points=" + points +
                '}';
    }
}
