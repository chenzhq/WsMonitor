package com.ws.stoner.model.view;

import com.ws.stoner.model.dto.BriefPointDTO;

import java.util.List;

/**
 * Created by pc on 2017/7/13.
 */
public class HostDetailVO {

    private String hostId;
    private String hostName;
    private String state;
    private String type;
    private String ip;
    private String description;
    private String agentIp;
    private String agentDNS;
    private String SNMPIp;
    private String SNMPDNS;
    private String IPMIIp;
    private String IPMIDNS;
    private String JMXIp;
    private String JMXDNS;
    private List<HostDetailPointVO> points;


    @Override
    public String toString() {
        return "HostDetailVO{" +
                "hostId='" + hostId + '\'' +
                ", hostName='" + hostName + '\'' +
                ", state='" + state + '\'' +
                ", type='" + type + '\'' +
                ", ip='" + ip + '\'' +
                ", description='" + description + '\'' +
                ", agentIp='" + agentIp + '\'' +
                ", agentDNS='" + agentDNS + '\'' +
                ", SNMPIp='" + SNMPIp + '\'' +
                ", SNMPDNS='" + SNMPDNS + '\'' +
                ", IPMIIp='" + IPMIIp + '\'' +
                ", IPMIDNS='" + IPMIDNS + '\'' +
                ", JMXIp='" + JMXIp + '\'' +
                ", JMXDNS='" + JMXDNS + '\'' +
                ", points=" + points +
                '}';
    }

    public String getHostId() {
        return hostId;
    }

    public HostDetailVO setHostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public String getHostName() {
        return hostName;
    }

    public HostDetailVO setHostName(String hostName) {
        this.hostName = hostName;
        return this;
    }

    public String getState() {
        return state;
    }

    public HostDetailVO setState(String state) {
        this.state = state;
        return this;
    }

    public String getType() {
        return type;
    }

    public HostDetailVO setType(String type) {
        this.type = type;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public HostDetailVO setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public HostDetailVO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getAgentIp() {
        return agentIp;
    }

    public HostDetailVO setAgentIp(String agentIp) {
        this.agentIp = agentIp;
        return this;
    }

    public String getAgentDNS() {
        return agentDNS;
    }

    public HostDetailVO setAgentDNS(String agentDNS) {
        this.agentDNS = agentDNS;
        return this;
    }

    public String getSNMPIp() {
        return SNMPIp;
    }

    public HostDetailVO setSNMPIp(String SNMPIp) {
        this.SNMPIp = SNMPIp;
        return this;
    }

    public String getSNMPDNS() {
        return SNMPDNS;
    }

    public HostDetailVO setSNMPDNS(String SNMPDNS) {
        this.SNMPDNS = SNMPDNS;
        return this;
    }

    public String getIPMIIp() {
        return IPMIIp;
    }

    public HostDetailVO setIPMIIp(String IPMIIp) {
        this.IPMIIp = IPMIIp;
        return this;
    }

    public String getIPMIDNS() {
        return IPMIDNS;
    }

    public HostDetailVO setIPMIDNS(String IPMIDNS) {
        this.IPMIDNS = IPMIDNS;
        return this;
    }

    public String getJMXIp() {
        return JMXIp;
    }

    public HostDetailVO setJMXIp(String JMXIp) {
        this.JMXIp = JMXIp;
        return this;
    }

    public String getJMXDNS() {
        return JMXDNS;
    }

    public HostDetailVO setJMXDNS(String JMXDNS) {
        this.JMXDNS = JMXDNS;
        return this;
    }

    public List<HostDetailPointVO> getPoints() {
        return points;
    }

    public HostDetailVO setPoints(List<HostDetailPointVO> points) {
        this.points = points;
        return this;
    }
}
