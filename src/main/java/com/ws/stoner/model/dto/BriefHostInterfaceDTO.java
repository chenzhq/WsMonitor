package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by pc on 2017/6/19.
 */
public class BriefHostInterfaceDTO {
    @JSONField(name = "interfaceid")
    private String interfaceId;
    @JSONField(name = "hostid")
    private String hostId;
    private String dns;
    private String type;
    private String ip;
    private String port;
    @JSONField(name = "useip")
    private String useIp;

    public static final String[] PROPERTY_NAMES = {"interfaceid","ip","hostid","dns","type","port","useip"};

    public String getIp() {
        return ip;
    }

    public BriefHostInterfaceDTO setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public BriefHostInterfaceDTO setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
        return this;
    }

    public String getHostId() {
        return hostId;
    }

    public BriefHostInterfaceDTO setHostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public String getDns() {
        return dns;
    }

    public BriefHostInterfaceDTO setDns(String dns) {
        this.dns = dns;
        return this;
    }

    public String getType() {
        return type;
    }

    public BriefHostInterfaceDTO setType(String type) {
        this.type = type;
        return this;
    }

    public String getPort() {
        return port;
    }

    public BriefHostInterfaceDTO setPort(String port) {
        this.port = port;
        return this;
    }

    public String getUseIp() {
        return useIp;
    }

    public BriefHostInterfaceDTO setUseIp(String useIp) {
        this.useIp = useIp;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BriefHostInterfaceDTO that = (BriefHostInterfaceDTO) o;

        if (interfaceId != null ? !interfaceId.equals(that.interfaceId) : that.interfaceId != null) return false;
        if (hostId != null ? !hostId.equals(that.hostId) : that.hostId != null) return false;
        if (dns != null ? !dns.equals(that.dns) : that.dns != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;
        if (port != null ? !port.equals(that.port) : that.port != null) return false;
        return useIp != null ? useIp.equals(that.useIp) : that.useIp == null;
    }

    @Override
    public int hashCode() {
        int result = interfaceId != null ? interfaceId.hashCode() : 0;
        result = 31 * result + (hostId != null ? hostId.hashCode() : 0);
        result = 31 * result + (dns != null ? dns.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (port != null ? port.hashCode() : 0);
        result = 31 * result + (useIp != null ? useIp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BriefHostInterfaceDTO{" +
                "interfaceId='" + interfaceId + '\'' +
                ", hostId='" + hostId + '\'' +
                ", dns='" + dns + '\'' +
                ", type='" + type + '\'' +
                ", ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", useIp='" + useIp + '\'' +
                '}';
    }
}
