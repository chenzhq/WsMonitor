package com.ws.stoner.model.view;

/**
 * Created by pc on 2017/7/14.
 */
public class HostDetailInterfaceVO {

    private String hostId;
    private String agentIp;
    private String agentDNS;
    private String agentPort;
    private String SNMPIp;
    private String SNMPDNS;
    private String SNMPPort;
    private String IPMIIp;
    private String IPMIDNS;
    private String IPMIPort;
    private String JMXIp;
    private String JMXDNS;
    private String JMXPort;

    public String getHostId() {
        return hostId;
    }

    public HostDetailInterfaceVO setHostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public String getAgentIp() {
        return agentIp;
    }

    public HostDetailInterfaceVO setAgentIp(String agentIp) {
        this.agentIp = agentIp;
        return this;
    }

    public String getAgentDNS() {
        return agentDNS;
    }

    public HostDetailInterfaceVO setAgentDNS(String agentDNS) {
        this.agentDNS = agentDNS;
        return this;
    }

    public String getSNMPIp() {
        return SNMPIp;
    }

    public HostDetailInterfaceVO setSNMPIp(String SNMPIp) {
        this.SNMPIp = SNMPIp;
        return this;
    }

    public String getSNMPDNS() {
        return SNMPDNS;
    }

    public HostDetailInterfaceVO setSNMPDNS(String SNMPDNS) {
        this.SNMPDNS = SNMPDNS;
        return this;
    }

    public String getIPMIIp() {
        return IPMIIp;
    }

    public HostDetailInterfaceVO setIPMIIp(String IPMIIp) {
        this.IPMIIp = IPMIIp;
        return this;
    }

    public String getIPMIDNS() {
        return IPMIDNS;
    }

    public HostDetailInterfaceVO setIPMIDNS(String IPMIDNS) {
        this.IPMIDNS = IPMIDNS;
        return this;
    }

    public String getJMXIp() {
        return JMXIp;
    }

    public HostDetailInterfaceVO setJMXIp(String JMXIp) {
        this.JMXIp = JMXIp;
        return this;
    }

    public String getJMXDNS() {
        return JMXDNS;
    }

    public HostDetailInterfaceVO setJMXDNS(String JMXDNS) {
        this.JMXDNS = JMXDNS;
        return this;
    }

    public String getAgentPort() {
        return agentPort;
    }

    public HostDetailInterfaceVO setAgentPort(String agentPort) {
        this.agentPort = agentPort;
        return this;
    }

    public String getSNMPPort() {
        return SNMPPort;
    }

    public HostDetailInterfaceVO setSNMPPort(String SNMPPort) {
        this.SNMPPort = SNMPPort;
        return this;
    }

    public String getIPMIPort() {
        return IPMIPort;
    }

    public HostDetailInterfaceVO setIPMIPort(String IPMIPort) {
        this.IPMIPort = IPMIPort;
        return this;
    }

    public String getJMXPort() {
        return JMXPort;
    }

    public HostDetailInterfaceVO setJMXPort(String JMXPort) {
        this.JMXPort = JMXPort;
        return this;
    }

    @Override
    public String toString() {
        return "HostDetailInterfaceVO{" +
                "hostId='" + hostId + '\'' +
                ", agentIp='" + agentIp + '\'' +
                ", agentDNS='" + agentDNS + '\'' +
                ", agentPort='" + agentPort + '\'' +
                ", SNMPIp='" + SNMPIp + '\'' +
                ", SNMPDNS='" + SNMPDNS + '\'' +
                ", SNMPPort='" + SNMPPort + '\'' +
                ", IPMIIp='" + IPMIIp + '\'' +
                ", IPMIDNS='" + IPMIDNS + '\'' +
                ", IPMIPort='" + IPMIPort + '\'' +
                ", JMXIp='" + JMXIp + '\'' +
                ", JMXDNS='" + JMXDNS + '\'' +
                ", JMXPort='" + JMXPort + '\'' +
                '}';
    }
}
