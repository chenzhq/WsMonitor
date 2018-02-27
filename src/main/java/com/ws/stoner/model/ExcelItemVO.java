package com.ws.stoner.model;

/**
 * Created by zhongkf on 2018/1/30
 */
public class ExcelItemVO {

    private String tempName;
    private String pointName;
    private String itemName;
    private String itemType;
    private String key;
    private String valueType;
    private String unit;
    private String delay;
    private String history;
    private String trends;
    private String delta;
    private String userName;
    private String password;
    private String SNMPOid;
    private String SNMPCommunity;
    private String port;
    private String snmpv3Contextname;
    private String snmpv3Securityname;
    private String snmpv3Securitylevel;
    private String SQLQuery;
    private String IPMISensor;
    private String PARAMS;
    private boolean isAlert;
    private boolean isDDL;
    private String description;

    public String getTempName() {
        return tempName;
    }

    public ExcelItemVO setTempName(String tempName) {
        this.tempName = tempName;
        return this;
    }

    public String getPointName() {
        return pointName;
    }

    public ExcelItemVO setPointName(String pointName) {
        this.pointName = pointName;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public ExcelItemVO setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public String getKey() {
        return key;
    }

    public ExcelItemVO setKey(String key) {
        this.key = key;
        return this;
    }

    public String getItemType() {
        return itemType;
    }

    public ExcelItemVO setItemType(String itemType) {
        this.itemType = itemType;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public ExcelItemVO setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public String getDelay() {
        return delay;
    }

    public ExcelItemVO setDelay(String delay) {
        this.delay = delay;
        return this;
    }

    public String getHistory() {
        return history;
    }

    public ExcelItemVO setHistory(String history) {
        this.history = history;
        return this;
    }

    public boolean isAlert() {
        return isAlert;
    }

    public ExcelItemVO setAlert(boolean alert) {
        isAlert = alert;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ExcelItemVO setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isDDL() {
        return isDDL;
    }

    public ExcelItemVO setDDL(boolean DDL) {
        isDDL = DDL;
        return this;
    }

    public String getValueType() {
        return valueType;
    }

    public ExcelItemVO setValueType(String valueType) {
        this.valueType = valueType;
        return this;
    }

    public String getTrends() {
        return trends;
    }

    public ExcelItemVO setTrends(String trends) {
        this.trends = trends;
        return this;
    }

    public String getDelta() {
        return delta;
    }

    public ExcelItemVO setDelta(String delta) {
        this.delta = delta;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public ExcelItemVO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ExcelItemVO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getSNMPOid() {
        return SNMPOid;
    }

    public ExcelItemVO setSNMPOid(String SNMPOid) {
        this.SNMPOid = SNMPOid;
        return this;
    }

    public String getSNMPCommunity() {
        return SNMPCommunity;
    }

    public ExcelItemVO setSNMPCommunity(String SNMPCommunity) {
        this.SNMPCommunity = SNMPCommunity;
        return this;
    }

    public String getPort() {
        return port;
    }

    public ExcelItemVO setPort(String port) {
        this.port = port;
        return this;
    }

    public String getSnmpv3Contextname() {
        return snmpv3Contextname;
    }

    public ExcelItemVO setSnmpv3Contextname(String snmpv3Contextname) {
        this.snmpv3Contextname = snmpv3Contextname;
        return this;
    }

    public String getSnmpv3Securityname() {
        return snmpv3Securityname;
    }

    public ExcelItemVO setSnmpv3Securityname(String snmpv3Securityname) {
        this.snmpv3Securityname = snmpv3Securityname;
        return this;
    }

    public String getSnmpv3Securitylevel() {
        return snmpv3Securitylevel;
    }

    public ExcelItemVO setSnmpv3Securitylevel(String snmpv3Securitylevel) {
        this.snmpv3Securitylevel = snmpv3Securitylevel;
        return this;
    }

    public String getSQLQuery() {
        return SQLQuery;
    }

    public ExcelItemVO setSQLQuery(String SQLQuery) {
        this.SQLQuery = SQLQuery;
        return this;
    }

    public String getIPMISensor() {
        return IPMISensor;
    }

    public ExcelItemVO setIPMISensor(String IPMISensor) {
        this.IPMISensor = IPMISensor;
        return this;
    }

    public String getPARAMS() {
        return PARAMS;
    }

    public ExcelItemVO setPARAMS(String PARAMS) {
        this.PARAMS = PARAMS;
        return this;
    }

    @Override
    public String toString() {
        return "ExcelItemVO{" +
                "tempName='" + tempName + '\'' +
                ", pointName='" + pointName + '\'' +
                ", itemName='" + itemName + '\'' +
                '}' + '\n';
    }
}
