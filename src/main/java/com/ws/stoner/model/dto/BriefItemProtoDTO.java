package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by pc on 2017/6/20.
 */
public class BriefItemProtoDTO {
    @JSONField(name = "itemid")
    private String itemId;
    private int delay;
    @JSONField(name = "hostid")
    private String hostId;
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
    private String formula;
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

    @JSONField(name = "applications")
    private List<BriefPointDTO> points;
    @JSONField(name = "applicationPrototypes")
    private List<BriefPointProtoDTO> pointProtos;
    private BriefHostDTO host;
    private List<BriefTriggerDTO> triggers;


    public static final String[] PROPERTY_NAMES = {"itemid", "name","ipmi_sensor","snmp_oid","units","valuemapid","type","key_","description","hostid","delay","history","trends","value_type"};
    public static final String[] ALL_NAMES = {};

    public String getItemId() {
        return itemId;
    }

    public BriefItemProtoDTO setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getName() {
        return name;
    }

    public BriefItemProtoDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getValuemapId() {
        return valuemapId;
    }

    public BriefItemProtoDTO setValuemapId(String valuemapId) {
        this.valuemapId = valuemapId;
        return this;
    }

    public String getUnits() {
        return units;
    }

    public BriefItemProtoDTO setUnits(String units) {
        this.units = units;
        return this;
    }

    public String getType() {
        return type;
    }

    public BriefItemProtoDTO setType(String type) {
        this.type = type;
        return this;
    }

    public String getKey() {
        return key;
    }

    public BriefItemProtoDTO setKey(String key) {
        this.key = key;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BriefItemProtoDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<BriefPointDTO> getPoints() {
        return points;
    }

    public BriefItemProtoDTO setPoints(List<BriefPointDTO> points) {
        this.points = points;
        return this;
    }

    public BriefHostDTO getHost() {
        return host;
    }

    public BriefItemProtoDTO setHost(BriefHostDTO host) {
        this.host = host;
        return this;
    }

    public List<BriefTriggerDTO> getTriggers() {
        return triggers;
    }

    public BriefItemProtoDTO setTriggers(List<BriefTriggerDTO> triggers) {
        this.triggers = triggers;
        return this;
    }

    public List<BriefPointProtoDTO> getPointProtos() {
        return pointProtos;
    }

    public BriefItemProtoDTO setPointProtos(List<BriefPointProtoDTO> pointProtos) {
        this.pointProtos = pointProtos;
        return this;
    }

    public String getHostId() {
        return hostId;
    }

    public BriefItemProtoDTO setHostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public int getDelay() {
        return delay;
    }

    public BriefItemProtoDTO setDelay(int delay) {
        this.delay = delay;
        return this;
    }

    public int getHistory() {
        return history;
    }

    public BriefItemProtoDTO setHistory(int history) {
        this.history = history;
        return this;
    }

    public String getValueType() {
        return valueType;
    }

    public BriefItemProtoDTO setValueType(String valueType) {
        this.valueType = valueType;
        return this;
    }

    public String getAuthType() {
        return authType;
    }

    public BriefItemProtoDTO setAuthType(String authType) {
        this.authType = authType;
        return this;
    }

    public String getDataType() {
        return dataType;
    }

    public BriefItemProtoDTO setDataType(String dataType) {
        this.dataType = dataType;
        return this;
    }

    public String getDelayFlex() {
        return delayFlex;
    }

    public BriefItemProtoDTO setDelayFlex(String delayFlex) {
        this.delayFlex = delayFlex;
        return this;
    }

    public Integer getDelta() {
        return delta;
    }

    public BriefItemProtoDTO setDelta(Integer delta) {
        this.delta = delta;
        return this;
    }

    public String getFormula() {
        return formula;
    }

    public BriefItemProtoDTO setFormula(String formula) {
        this.formula = formula;
        return this;
    }

    public Integer getInventoryLink() {
        return inventoryLink;
    }

    public BriefItemProtoDTO setInventoryLink(Integer inventoryLink) {
        this.inventoryLink = inventoryLink;
        return this;
    }

    public String getIpmiSensor() {
        return ipmiSensor;
    }

    public BriefItemProtoDTO setIpmiSensor(String ipmiSensor) {
        this.ipmiSensor = ipmiSensor;
        return this;
    }

    public String getLogtimefmt() {
        return logtimefmt;
    }

    public BriefItemProtoDTO setLogtimefmt(String logtimefmt) {
        this.logtimefmt = logtimefmt;
        return this;
    }

    public int getMtime() {
        return mtime;
    }

    public BriefItemProtoDTO setMtime(int mtime) {
        this.mtime = mtime;
        return this;
    }

    public Integer getMultiplier() {
        return multiplier;
    }

    public BriefItemProtoDTO setMultiplier(Integer multiplier) {
        this.multiplier = multiplier;
        return this;
    }

    public String getParams() {
        return params;
    }

    public BriefItemProtoDTO setParams(String params) {
        this.params = params;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public BriefItemProtoDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPort() {
        return port;
    }

    public BriefItemProtoDTO setPort(String port) {
        this.port = port;
        return this;
    }

    public String getPrivatekey() {
        return privatekey;
    }

    public BriefItemProtoDTO setPrivatekey(String privatekey) {
        this.privatekey = privatekey;
        return this;
    }

    public String getPublickey() {
        return publickey;
    }

    public BriefItemProtoDTO setPublickey(String publickey) {
        this.publickey = publickey;
        return this;
    }

    public String getSnmpCommunity() {
        return snmpCommunity;
    }

    public BriefItemProtoDTO setSnmpCommunity(String snmpCommunity) {
        this.snmpCommunity = snmpCommunity;
        return this;
    }

    public String getSnmpOid() {
        return snmpOid;
    }

    public BriefItemProtoDTO setSnmpOid(String snmpOid) {
        this.snmpOid = snmpOid;
        return this;
    }

    public String getSnmpv3Authpassphrase() {
        return snmpv3Authpassphrase;
    }

    public BriefItemProtoDTO setSnmpv3Authpassphrase(String snmpv3Authpassphrase) {
        this.snmpv3Authpassphrase = snmpv3Authpassphrase;
        return this;
    }

    public Integer getSnmpv3Authprotocol() {
        return snmpv3Authprotocol;
    }

    public BriefItemProtoDTO setSnmpv3Authprotocol(Integer snmpv3Authprotocol) {
        this.snmpv3Authprotocol = snmpv3Authprotocol;
        return this;
    }

    public String getSnmpv3Contextname() {
        return snmpv3Contextname;
    }

    public BriefItemProtoDTO setSnmpv3Contextname(String snmpv3Contextname) {
        this.snmpv3Contextname = snmpv3Contextname;
        return this;
    }

    public String getSnmpv3Privpassphrase() {
        return snmpv3Privpassphrase;
    }

    public BriefItemProtoDTO setSnmpv3Privpassphrase(String snmpv3Privpassphrase) {
        this.snmpv3Privpassphrase = snmpv3Privpassphrase;
        return this;
    }

    public Integer getSnmpv3Privprotocol() {
        return snmpv3Privprotocol;
    }

    public BriefItemProtoDTO setSnmpv3Privprotocol(Integer snmpv3Privprotocol) {
        this.snmpv3Privprotocol = snmpv3Privprotocol;
        return this;
    }

    public Integer getSnmpv3Securitylevel() {
        return snmpv3Securitylevel;
    }

    public BriefItemProtoDTO setSnmpv3Securitylevel(Integer snmpv3Securitylevel) {
        this.snmpv3Securitylevel = snmpv3Securitylevel;
        return this;
    }

    public String getSnmpv3Securityname() {
        return snmpv3Securityname;
    }

    public BriefItemProtoDTO setSnmpv3Securityname(String snmpv3Securityname) {
        this.snmpv3Securityname = snmpv3Securityname;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public BriefItemProtoDTO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getTemplateId() {
        return templateId;
    }

    public BriefItemProtoDTO setTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public String getTrapperHosts() {
        return trapperHosts;
    }

    public BriefItemProtoDTO setTrapperHosts(String trapperHosts) {
        this.trapperHosts = trapperHosts;
        return this;
    }

    public Integer getTrends() {
        return trends;
    }

    public BriefItemProtoDTO setTrends(Integer trends) {
        this.trends = trends;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public BriefItemProtoDTO setUsername(String username) {
        this.username = username;
        return this;
    }
}
