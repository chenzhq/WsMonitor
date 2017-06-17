package com.ws.bix4j.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by pc on 2017/6/16.
 */
public class HostInterfaceDO {
    @JSONField(name = "interfaceid")
    private String interfaceId;
    private String DNS;
    @JSONField(name = "hostid")
    private String hostId;
    private String ip;
    private Integer main;
    private String port;
    private Integer type;
    @JSONField(name = "useip")
    private Integer useIp;
    private Integer bulk;

    public String getInterfaceId() {
        return interfaceId;
    }

    public HostInterfaceDO setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
        return this;
    }

    public String getDNS() {
        return DNS;
    }

    public HostInterfaceDO setDNS(String DNS) {
        this.DNS = DNS;
        return this;
    }

    public String getHostId() {
        return hostId;
    }

    public HostInterfaceDO setHostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public HostInterfaceDO setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public Integer getMain() {
        return main;
    }

    public HostInterfaceDO setMain(Integer main) {
        this.main = main;
        return this;
    }

    public String getPort() {
        return port;
    }

    public HostInterfaceDO setPort(String port) {
        this.port = port;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public HostInterfaceDO setType(Integer type) {
        this.type = type;
        return this;
    }

    public Integer getUseIp() {
        return useIp;
    }

    public HostInterfaceDO setUseIp(Integer useIp) {
        this.useIp = useIp;
        return this;
    }

    public Integer getBulk() {
        return bulk;
    }

    public HostInterfaceDO setBulk(Integer bulk) {
        this.bulk = bulk;
        return this;
    }

    @Override
    public String toString() {
        return "HostInterfaceDO{" +
                "interfaceId='" + interfaceId + '\'' +
                ", DNS='" + DNS + '\'' +
                ", hostId='" + hostId + '\'' +
                ", ip='" + ip + '\'' +
                ", main=" + main +
                ", port='" + port + '\'' +
                ", type=" + type +
                ", useIp=" + useIp +
                ", bulk=" + bulk +
                '}';
    }
}
