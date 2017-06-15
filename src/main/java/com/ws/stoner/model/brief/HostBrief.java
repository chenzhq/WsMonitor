package com.ws.stoner.model.brief;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by pc on 2017/6/13.
 */
public class HostBrief {
    @JSONField(name = "hostid")
    private String hostId;
    private String host;

    private String name;
    private int available;
    @JSONField(name = "ipmi_available")
    private int ipmiAvailable;
    @JSONField(name = "jmx_available")
    private int jmxAvailable;
    @JSONField(name = "snmp_available")
    private int snmpAvailable;
    @JSONField(name = "maintenance_status")
    private int maintenanceStatus;

    private int flags;
    private int status;

    public String getHostId() {
        return hostId;
    }

    public HostBrief setHostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public String getHost() {
        return host;
    }

    public HostBrief setHost(String host) {
        this.host = host;
        return this;
    }

    public String getName() {
        return name;
    }

    public HostBrief setName(String name) {
        this.name = name;
        return this;
    }

    public int getAvailable() {
        return available;
    }

    public HostBrief setAvailable(int available) {
        this.available = available;
        return this;
    }

    public int getIpmiAvailable() {
        return ipmiAvailable;
    }

    public HostBrief setIpmiAvailable(int ipmiAvailable) {
        this.ipmiAvailable = ipmiAvailable;
        return this;
    }

    public int getJmxAvailable() {
        return jmxAvailable;
    }

    public HostBrief setJmxAvailable(int jmxAvailable) {
        this.jmxAvailable = jmxAvailable;
        return this;
    }

    public int getSnmpAvailable() {
        return snmpAvailable;
    }

    public HostBrief setSnmpAvailable(int snmpAvailable) {
        this.snmpAvailable = snmpAvailable;
        return this;
    }

    public int getMaintenanceStatus() {
        return maintenanceStatus;
    }

    public HostBrief setMaintenanceStatus(int maintenanceStatus) {
        this.maintenanceStatus = maintenanceStatus;
        return this;
    }

    public int getFlags() {
        return flags;
    }

    public HostBrief setFlags(int flags) {
        this.flags = flags;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public HostBrief setStatus(int status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "HostBrief{" +
                "hostId='" + hostId + '\'' +
                ", host='" + host + '\'' +
                ", name='" + name + '\'' +
                ", available=" + available +
                ", ipmiAvailable=" + ipmiAvailable +
                ", jmxAvailable=" + jmxAvailable +
                ", snmpAvailable=" + snmpAvailable +
                ", maintenanceStatus=" + maintenanceStatus +
                ", flags=" + flags +
                ", status=" + status +
                '}';
    }
}
