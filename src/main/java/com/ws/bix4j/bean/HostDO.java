package com.ws.bix4j.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.time.Instant;
import java.util.List;

/**
 * Created by chenzheqi on 2017/4/28.
 * api文档中host对象的附带对象太多，暂时不对所有的附带对象进行实现
 */
public class HostDO{

    @JSONField(name = "hostid")
    private String hostId;
    private String host;
    /**
     * 可见的名称
     * */
    private String name;
    private int available;
    private String description;
    @JSONField(name = "disable_until")
    private Instant disableUntil;
    private String error;
    @JSONField(name = "errors_from")
    private Instant errorsFrom;
    private int flags;
    @JSONField(name = "inventory_mode")
    private int inventoryMode;
    @JSONField(name = "proxy_hostid")
    private String proxyHostId;
    private int status;
    /**
     *
     * */
    @JSONField(name = "lastaccess")
    private String lastaccess;

    @JSONField(name = "ipmi_authtype")
    private int ipmiAuthtype;
    @JSONField(name = "ipmi_disable_until")
    private Instant ipmiDisableUntil;
    @JSONField(name = "ipmi_available")
    private int ipmiAvailable;
    @JSONField(name = "ipmi_error")
    private String ipmiError;
    @JSONField(name = "ipmi_errors_from")
    private Instant ipmiErrorsFrom;
    @JSONField(name = "ipmi_privilege")
    private String ipmiPrivilege;
    @JSONField(name = "ipmi_username")
    private String ipmiUsername;
    @JSONField(name = "ipmi_password")
    private String ipmiPassword;

    @JSONField(name = "jmx_disable_until")
    private Instant jmxDisableUntil;
    @JSONField(name = "jmx_available")
    private int jmxAvailable;
    @JSONField(name = "jmx_errors_from")
    private Instant jmxErrorsFrom;
    @JSONField(name = "jmx_error")
    private String jmxError;

    private String maintenanceid;
    @JSONField(name = "maintenance_status")
    private int maintenanceStatus;
    @JSONField(name = "maintenance_type")
    private int maintenanceType;
    @JSONField(name = "maintenance_from")
    private Instant maintenanceFrom;

    @JSONField(name = "snmp_disable_until")
    private Instant snmpDisableUntil;
    @JSONField(name = "snmp_available")
    private int snmpAvailable;
    @JSONField(name = "snmp_errors_from")
    private Instant snmpErrorsFrom;
    @JSONField(name = "snmp_error")
    private String snmpError;

    @JSONField(name = "tls_connect")
    private String tlsConnect;
    @JSONField(name = "tls_accept")
    private String tlsAccept;
    @JSONField(name = "tls_issuer")
    private String tlsIssuer;
    @JSONField(name = "tls_subject")
    private String tlsSubject;
    @JSONField(name = "tls_psk_identity")
    private String tlsPskIdentity;
    @JSONField(name = "tls_psk")
    private String tlsPsk;

    /**
     * 官网api示例中默认返回维护对象
     */
    private List<MaintenanceDO> maintenances;
    /**
     * 查询参数 selectGroups 则返回该字段 <br>
     * Query 【extend props]
     */
    private List<HostGroupDO> groups;
    /**
     * 查询参数 selectApplications 则返回该字段 <br>
     * Query [all]
     */
    private List<ApplicationDO> applications;

    /**
     * 查询参数 selectItems 则返回该字段 <br>
     * Query [all]
     */
    private List<?> items;

    /**
     * 查询参数 selectTriggers 则返回该字段 <br>
     * Query [all]
     */
    private List<?> triggers;

    public String getHostId() {
        return hostId;
    }

    public HostDO setHostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public String getHost() {
        return host;
    }

    public HostDO setHost(String host) {
        this.host = host;
        return this;
    }

    public String getName() {
        return name;
    }

    public HostDO setName(String name) {
        this.name = name;
        return this;
    }

    public int getAvailable() {
        return available;
    }

    public HostDO setAvailable(int available) {
        this.available = available;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public HostDO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Instant getDisableUntil() {
        return disableUntil;
    }

    public HostDO setDisableUntil(int disableUntil) {
        this.disableUntil = Instant.ofEpochSecond(disableUntil);
        return this;
    }

    public String getError() {
        return error;
    }

    public HostDO setError(String error) {
        this.error = error;
        return this;
    }

    public Instant getErrorsFrom() {
        return errorsFrom;
    }

    public HostDO setErrorsFrom(int errorsFrom) {
        this.errorsFrom = Instant.ofEpochSecond(errorsFrom);
        return this;
    }

    public int getFlags() {
        return flags;
    }

    public HostDO setFlags(int flags) {
        this.flags = flags;
        return this;
    }

    public int getInventoryMode() {
        return inventoryMode;
    }

    public HostDO setInventoryMode(int inventoryMode) {
        this.inventoryMode = inventoryMode;
        return this;
    }

    public String getProxyHostId() {
        return proxyHostId;
    }

    public HostDO setProxyHostId(String proxyHostId) {
        this.proxyHostId = proxyHostId;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public HostDO setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getLastaccess() {
        return lastaccess;
    }

    public HostDO setLastaccess(String lastaccess) {
        this.lastaccess = lastaccess;
        return this;
    }

    public int getIpmiAuthtype() {
        return ipmiAuthtype;
    }

    public HostDO setIpmiAuthtype(int ipmiAuthtype) {
        this.ipmiAuthtype = ipmiAuthtype;
        return this;
    }

    public Instant getIpmiDisableUntil() {
        return ipmiDisableUntil;
    }

    public HostDO setIpmiDisableUntil(int ipmiDisableUntil) {
        this.ipmiDisableUntil = Instant.ofEpochSecond(ipmiDisableUntil);
        return this;
    }

    public int getIpmiAvailable() {
        return ipmiAvailable;
    }

    public HostDO setIpmiAvailable(int ipmiAvailable) {
        this.ipmiAvailable = ipmiAvailable;
        return this;
    }

    public String getIpmiError() {
        return ipmiError;
    }

    public HostDO setIpmiError(String ipmiError) {
        this.ipmiError = ipmiError;
        return this;
    }

    public Instant getIpmiErrorsFrom() {
        return ipmiErrorsFrom;
    }

    public HostDO setIpmiErrorsFrom(int ipmiErrorsFrom) {
        this.ipmiErrorsFrom = Instant.ofEpochSecond(ipmiErrorsFrom);
        return this;
    }

    public String getIpmiPrivilege() {
        return ipmiPrivilege;
    }

    public HostDO setIpmiPrivilege(String ipmiPrivilege) {
        this.ipmiPrivilege = ipmiPrivilege;
        return this;
    }

    public String getIpmiUsername() {
        return ipmiUsername;
    }

    public HostDO setIpmiUsername(String ipmiUsername) {
        this.ipmiUsername = ipmiUsername;
        return this;
    }

    public String getIpmiPassword() {
        return ipmiPassword;
    }

    public HostDO setIpmiPassword(String ipmiPassword) {
        this.ipmiPassword = ipmiPassword;
        return this;
    }

    public Instant getJmxDisableUntil() {
        return jmxDisableUntil;
    }

    public HostDO setJmxDisableUntil(int jmxDisableUntil) {
        this.jmxDisableUntil = Instant.ofEpochSecond(jmxDisableUntil);
        return this;
    }

    public int getJmxAvailable() {
        return jmxAvailable;
    }

    public HostDO setJmxAvailable(int jmxAvailable) {
        this.jmxAvailable = jmxAvailable;
        return this;
    }

    public Instant getJmxErrorsFrom() {
        return jmxErrorsFrom;
    }

    public HostDO setJmxErrorsFrom(int jmxErrorsFrom) {
        this.jmxErrorsFrom = Instant.ofEpochSecond(jmxErrorsFrom);
        return this;
    }

    public String getJmxError() {
        return jmxError;
    }

    public HostDO setJmxError(String jmxError) {
        this.jmxError = jmxError;
        return this;
    }

    public String getMaintenanceid() {
        return maintenanceid;
    }

    public HostDO setMaintenanceid(String maintenanceid) {
        this.maintenanceid = maintenanceid;
        return this;
    }

    public int getMaintenanceStatus() {
        return maintenanceStatus;
    }

    public HostDO setMaintenanceStatus(int maintenanceStatus) {
        this.maintenanceStatus = maintenanceStatus;
        return this;
    }

    public int getMaintenanceType() {
        return maintenanceType;
    }

    public HostDO setMaintenanceType(int maintenanceType) {
        this.maintenanceType = maintenanceType;
        return this;
    }

    public Instant getMaintenanceFrom() {
        return maintenanceFrom;
    }

    public HostDO setMaintenanceFrom(int maintenanceFrom) {
        this.maintenanceFrom = Instant.ofEpochSecond(maintenanceFrom);
        return this;
    }

    public Instant getSnmpDisableUntil() {
        return snmpDisableUntil;
    }

    public HostDO setSnmpDisableUntil(int snmpDisableUntil) {
        this.snmpDisableUntil = Instant.ofEpochSecond(snmpDisableUntil);
        return this;
    }

    public int getSnmpAvailable() {
        return snmpAvailable;
    }

    public HostDO setSnmpAvailable(int snmpAvailable) {
        this.snmpAvailable = snmpAvailable;
        return this;
    }

    public Instant getSnmpErrorsFrom() {
        return snmpErrorsFrom;
    }

    public HostDO setSnmpErrorsFrom(int snmpErrorsFrom) {
        this.snmpErrorsFrom = Instant.ofEpochSecond(snmpErrorsFrom);
        return this;
    }

    public String getSnmpError() {
        return snmpError;
    }

    public HostDO setSnmpError(String snmpError) {
        this.snmpError = snmpError;
        return this;
    }

    public String getTlsConnect() {
        return tlsConnect;
    }

    public HostDO setTlsConnect(String tlsConnect) {
        this.tlsConnect = tlsConnect;
        return this;
    }

    public String getTlsAccept() {
        return tlsAccept;
    }

    public HostDO setTlsAccept(String tlsAccept) {
        this.tlsAccept = tlsAccept;
        return this;
    }

    public String getTlsIssuer() {
        return tlsIssuer;
    }

    public HostDO setTlsIssuer(String tlsIssuer) {
        this.tlsIssuer = tlsIssuer;
        return this;
    }

    public String getTlsSubject() {
        return tlsSubject;
    }

    public HostDO setTlsSubject(String tlsSubject) {
        this.tlsSubject = tlsSubject;
        return this;
    }

    public String getTlsPskIdentity() {
        return tlsPskIdentity;
    }

    public HostDO setTlsPskIdentity(String tlsPskIdentity) {
        this.tlsPskIdentity = tlsPskIdentity;
        return this;
    }

    public String getTlsPsk() {
        return tlsPsk;
    }

    public HostDO setTlsPsk(String tlsPsk) {
        this.tlsPsk = tlsPsk;
        return this;
    }

    public List<MaintenanceDO> getMaintenances() {
        return maintenances;
    }

    public HostDO setMaintenances(List<MaintenanceDO> maintenances) {
        this.maintenances = maintenances;
        return this;
    }

    public HostDO() {
    }

    @Override
    public String toString() {
        return "HostDO{" +
                "hostId='" + hostId + '\'' +
                ", host='" + host + '\'' +
                ", name='" + name + '\'' +
                ", available=" + available +
                ", description='" + description + '\'' +
                ", disableUntil=" + disableUntil +
                ", error='" + error + '\'' +
                ", errorsFrom=" + errorsFrom +
                ", flags=" + flags +
                ", inventoryMode=" + inventoryMode +
                ", proxyHostId='" + proxyHostId + '\'' +
                ", status=" + status +
                ", lastaccess='" + lastaccess + '\'' +
                ", ipmiAuthtype=" + ipmiAuthtype +
                ", ipmiDisableUntil=" + ipmiDisableUntil +
                ", ipmiAvailable=" + ipmiAvailable +
                ", ipmiError='" + ipmiError + '\'' +
                ", ipmiErrorsFrom=" + ipmiErrorsFrom +
                ", ipmiPrivilege='" + ipmiPrivilege + '\'' +
                ", ipmiUsername='" + ipmiUsername + '\'' +
                ", ipmiPassword='" + ipmiPassword + '\'' +
                ", jmxDisableUntil=" + jmxDisableUntil +
                ", jmxAvailable=" + jmxAvailable +
                ", jmxErrorsFrom=" + jmxErrorsFrom +
                ", jmxError='" + jmxError + '\'' +
                ", maintenanceid='" + maintenanceid + '\'' +
                ", maintenanceStatus=" + maintenanceStatus +
                ", maintenanceType=" + maintenanceType +
                ", maintenanceFrom=" + maintenanceFrom +
                ", snmpDisableUntil=" + snmpDisableUntil +
                ", snmpAvailable=" + snmpAvailable +
                ", snmpErrorsFrom=" + snmpErrorsFrom +
                ", snmpError='" + snmpError + '\'' +
                ", tlsConnect='" + tlsConnect + '\'' +
                ", tlsAccept='" + tlsAccept + '\'' +
                ", tlsIssuer='" + tlsIssuer + '\'' +
                ", tlsSubject='" + tlsSubject + '\'' +
                ", tlsPskIdentity='" + tlsPskIdentity + '\'' +
                ", tlsPsk='" + tlsPsk + '\'' +
                ", maintenances=" + maintenances +
                ", groups=" + groups +
                ", applications=" + applications +
                ", items=" + items +
                ", triggers=" + triggers +
                '}';
    }
}
