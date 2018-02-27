package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by zhongkf on 2018/1/30
 */
public class BriefDDLDTO {

    @JSONField(name = "itemid")
    private String itemId;
    private String delay;
    @JSONField(name = "hostid")
    private String hostId;
    @JSONField(name = "interfaceid")
    private String interfaceId;
    private String key_;
    private String name;
    private int type ;
    private int authtype;
    @JSONField(name = "delay_flex")
    private String delayFlex;
    private String description;
    private String error;
    @JSONField(name = "ipmi_sensor")
    private String ipmiSensor;
    private String lifetime;
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
    private int snmpv3Authprotocol;
    @JSONField(name = "snmpv3_contextname")
    private String snmpv3Contextname;
    @JSONField(name = "snmpv3_privpassphrase")
    private String snmpv3Privpassphrase;
    @JSONField(name = "snmpv3_privprotocol")
    private int snmpv3Privprotocol;
    @JSONField(name = "snmpv3_securitylevel")
    private int snmpv3Securitylevel;
    @JSONField(name = "snmpv3_securityname")
    private String snmpv3Securityname;
    private int state;
    private int status;
    @JSONField(name = "templateid")
    private String templateId;
    @JSONField(name = "trapper_hosts")
    private String trapperHosts;
    @JSONField(name = "username")
    private String userName;

    private List<BriefItemProtoDTO> items;
    private List<BriefTriggerProDTO> triggers;

    public static final String[] PROPERTY_NAMES = {"itemid","snmp_oid","hostid","key_","ipmi_sensor","name","templateid","delay","lifetime","description"};


    public String getItemId() {
        return itemId;
    }

    public BriefDDLDTO setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getDelay() {
        return delay;
    }

    public BriefDDLDTO setDelay(String delay) {
        this.delay = delay;
        return this;
    }

    public String getHostId() {
        return hostId;
    }

    public BriefDDLDTO setHostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public BriefDDLDTO setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
        return this;
    }

    public String getKey_() {
        return key_;
    }

    public BriefDDLDTO setKey_(String key_) {
        this.key_ = key_;
        return this;
    }

    public String getName() {
        return name;
    }

    public BriefDDLDTO setName(String name) {
        this.name = name;
        return this;
    }

    public int getType() {
        return type;
    }

    public BriefDDLDTO setType(int type) {
        this.type = type;
        return this;
    }

    public int getAuthtype() {
        return authtype;
    }

    public BriefDDLDTO setAuthtype(int authtype) {
        this.authtype = authtype;
        return this;
    }

    public String getDelayFlex() {
        return delayFlex;
    }

    public BriefDDLDTO setDelayFlex(String delayFlex) {
        this.delayFlex = delayFlex;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BriefDDLDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getError() {
        return error;
    }

    public BriefDDLDTO setError(String error) {
        this.error = error;
        return this;
    }

    public String getIpmiSensor() {
        return ipmiSensor;
    }

    public BriefDDLDTO setIpmiSensor(String ipmiSensor) {
        this.ipmiSensor = ipmiSensor;
        return this;
    }

    public String getLifetime() {
        return lifetime;
    }

    public BriefDDLDTO setLifetime(String lifetime) {
        this.lifetime = lifetime;
        return this;
    }

    public String getParams() {
        return params;
    }

    public BriefDDLDTO setParams(String params) {
        this.params = params;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public BriefDDLDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPort() {
        return port;
    }

    public BriefDDLDTO setPort(String port) {
        this.port = port;
        return this;
    }

    public String getPrivatekey() {
        return privatekey;
    }

    public BriefDDLDTO setPrivatekey(String privatekey) {
        this.privatekey = privatekey;
        return this;
    }

    public String getPublickey() {
        return publickey;
    }

    public BriefDDLDTO setPublickey(String publickey) {
        this.publickey = publickey;
        return this;
    }

    public String getSnmpCommunity() {
        return snmpCommunity;
    }

    public BriefDDLDTO setSnmpCommunity(String snmpCommunity) {
        this.snmpCommunity = snmpCommunity;
        return this;
    }

    public String getSnmpOid() {
        return snmpOid;
    }

    public BriefDDLDTO setSnmpOid(String snmpOid) {
        this.snmpOid = snmpOid;
        return this;
    }

    public String getSnmpv3Authpassphrase() {
        return snmpv3Authpassphrase;
    }

    public BriefDDLDTO setSnmpv3Authpassphrase(String snmpv3Authpassphrase) {
        this.snmpv3Authpassphrase = snmpv3Authpassphrase;
        return this;
    }

    public int getSnmpv3Authprotocol() {
        return snmpv3Authprotocol;
    }

    public BriefDDLDTO setSnmpv3Authprotocol(int snmpv3Authprotocol) {
        this.snmpv3Authprotocol = snmpv3Authprotocol;
        return this;
    }

    public String getSnmpv3Contextname() {
        return snmpv3Contextname;
    }

    public BriefDDLDTO setSnmpv3Contextname(String snmpv3Contextname) {
        this.snmpv3Contextname = snmpv3Contextname;
        return this;
    }

    public String getSnmpv3Privpassphrase() {
        return snmpv3Privpassphrase;
    }

    public BriefDDLDTO setSnmpv3Privpassphrase(String snmpv3Privpassphrase) {
        this.snmpv3Privpassphrase = snmpv3Privpassphrase;
        return this;
    }

    public int getSnmpv3Privprotocol() {
        return snmpv3Privprotocol;
    }

    public BriefDDLDTO setSnmpv3Privprotocol(int snmpv3Privprotocol) {
        this.snmpv3Privprotocol = snmpv3Privprotocol;
        return this;
    }

    public int getSnmpv3Securitylevel() {
        return snmpv3Securitylevel;
    }

    public BriefDDLDTO setSnmpv3Securitylevel(int snmpv3Securitylevel) {
        this.snmpv3Securitylevel = snmpv3Securitylevel;
        return this;
    }

    public String getSnmpv3Securityname() {
        return snmpv3Securityname;
    }

    public BriefDDLDTO setSnmpv3Securityname(String snmpv3Securityname) {
        this.snmpv3Securityname = snmpv3Securityname;
        return this;
    }

    public int getState() {
        return state;
    }

    public BriefDDLDTO setState(int state) {
        this.state = state;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public BriefDDLDTO setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getTemplateId() {
        return templateId;
    }

    public BriefDDLDTO setTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public String getTrapperHosts() {
        return trapperHosts;
    }

    public BriefDDLDTO setTrapperHosts(String trapperHosts) {
        this.trapperHosts = trapperHosts;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public BriefDDLDTO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public List<BriefItemProtoDTO> getItems() {
        return items;
    }

    public BriefDDLDTO setItems(List<BriefItemProtoDTO> items) {
        this.items = items;
        return this;
    }

    public List<BriefTriggerProDTO> getTriggers() {
        return triggers;
    }

    public BriefDDLDTO setTriggers(List<BriefTriggerProDTO> triggers) {
        this.triggers = triggers;
        return this;
    }
}
