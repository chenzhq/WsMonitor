package com.ws.stoner.model.view.host;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.ZApiParameter;
import com.ws.stoner.model.dto.BriefHostDTO;
import com.ws.stoner.model.dto.BriefHostInterfaceDTO;

import java.util.List;

/**
 * Created by pc on 2017/7/14.
 */
public class HostDetailInterfaceVO {


    @JSONField(name = "host_id")
    private String hostId;
    @JSONField(name = "agent_ip")
    private String agentIp;
    @JSONField(name = "agent_dns")
    private String agentDNS;
    @JSONField(name = "agent_port")
    private String agentPort;
    @JSONField(name = "agent_useip")
    private boolean agentUseIp;
    @JSONField(name = "snmp_ip")
    private String SNMPIp;
    @JSONField(name = "snmp_dns")
    private String SNMPDNS;
    @JSONField(name = "snmp_port")
    private String SNMPPort;
    @JSONField(name = "snmp_useip")
    private boolean SNMPUseIp;
    @JSONField(name = "host_id")
    private String IPMIIp;
    @JSONField(name = "ipmi_dns")
    private String IPMIDNS;
    @JSONField(name = "ipmi_port")
    private String IPMIPort;
    @JSONField(name = "ipmi_useip")
    private boolean IPMIUseIp;
    @JSONField(name = "jmx_ip")
    private String JMXIp;
    @JSONField(name = "jmx_dns")
    private String JMXDNS;
    @JSONField(name = "jmx_port")
    private String JMXPort;
    @JSONField(name = "jmx_useip")
    private boolean JMXUseIp;

    public HostDetailInterfaceVO() {
        //初始化
        this.agentUseIp = true;
        this.SNMPUseIp = true;
        this.IPMIUseIp = true;
        this.JMXUseIp = true;
    }

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

    public boolean isAgentUseIp() {
        return agentUseIp;
    }

    public HostDetailInterfaceVO setAgentUseIp(boolean agentUseIp) {
        this.agentUseIp = agentUseIp;
        return this;
    }

    public boolean isSNMPUseIp() {
        return SNMPUseIp;
    }

    public HostDetailInterfaceVO setSNMPUseIp(boolean SNMPUseIp) {
        this.SNMPUseIp = SNMPUseIp;
        return this;
    }

    public boolean isIPMIUseIp() {
        return IPMIUseIp;
    }

    public HostDetailInterfaceVO setIPMIUseIp(boolean IPMIUseIp) {
        this.IPMIUseIp = IPMIUseIp;
        return this;
    }

    public boolean isJMXUseIp() {
        return JMXUseIp;
    }

    public HostDetailInterfaceVO setJMXUseIp(boolean JMXUseIp) {
        this.JMXUseIp = JMXUseIp;
        return this;
    }

    @Override
    public String toString() {
        return "HostDetailInterfaceVO{" +
                "hostId='" + hostId + '\'' +
                ", agentIp='" + agentIp + '\'' +
                ", agentDNS='" + agentDNS + '\'' +
                ", agentPort='" + agentPort + '\'' +
                ", agentUseIp=" + agentUseIp +
                ", SNMPIp='" + SNMPIp + '\'' +
                ", SNMPDNS='" + SNMPDNS + '\'' +
                ", SNMPPort='" + SNMPPort + '\'' +
                ", SNMPUseIp=" + SNMPUseIp +
                ", IPMIIp='" + IPMIIp + '\'' +
                ", IPMIDNS='" + IPMIDNS + '\'' +
                ", IPMIPort='" + IPMIPort + '\'' +
                ", IPMIUseIp=" + IPMIUseIp +
                ", JMXIp='" + JMXIp + '\'' +
                ", JMXDNS='" + JMXDNS + '\'' +
                ", JMXPort='" + JMXPort + '\'' +
                ", JMXUseIp=" + JMXUseIp +
                '}';
    }

    static public HostDetailInterfaceVO transforByHostDTO(BriefHostDTO hostDTO) {
        HostDetailInterfaceVO interfaceVO = new HostDetailInterfaceVO();
        // interfaces[interfaceid,dns ,hostid ,ip ,type],
        List<BriefHostInterfaceDTO> interfaces = hostDTO.getInterfaces();
        interfaceVO.setHostId(hostDTO.getHostId());
        for(BriefHostInterfaceDTO interfaceDTO : interfaces) {
            if(String.valueOf(ZApiParameter.HOST_INTERFACE_TYPE.AGENT.value).equals(interfaceDTO.getType())) {
                interfaceVO.setAgentDNS(interfaceDTO.getDns());
                interfaceVO.setAgentIp(interfaceDTO.getIp());
                interfaceVO.setAgentPort(interfaceDTO.getPort());
                interfaceVO.setAgentUseIp(ZApiParameter.HOST_INTERFACE_USEIP.USEIP.value == Integer.parseInt(interfaceDTO.getUseIp()) ? true :false);

            }else if(String.valueOf(ZApiParameter.HOST_INTERFACE_TYPE.SNMP.value).equals(interfaceDTO.getType())) {
                interfaceVO.setSNMPDNS(interfaceDTO.getDns());
                interfaceVO.setSNMPIp(interfaceDTO.getIp());
                interfaceVO.setSNMPPort(interfaceDTO.getPort());
                interfaceVO.setSNMPUseIp(ZApiParameter.HOST_INTERFACE_USEIP.USEIP.value == Integer.parseInt(interfaceDTO.getUseIp()) ? true :false);

            }else if(String.valueOf(ZApiParameter.HOST_INTERFACE_TYPE.IPMI.value).equals(interfaceDTO.getType())) {
                interfaceVO.setIPMIDNS(interfaceDTO.getDns());
                interfaceVO.setIPMIIp(interfaceDTO.getIp());
                interfaceVO.setIPMIPort(interfaceDTO.getPort());
                interfaceVO.setIPMIUseIp(ZApiParameter.HOST_INTERFACE_USEIP.USEIP.value == Integer.parseInt(interfaceDTO.getUseIp()) ? true :false);

            }else if(String.valueOf(ZApiParameter.HOST_INTERFACE_TYPE.JMX.value).equals(interfaceDTO.getType())) {
                interfaceVO.setJMXDNS(interfaceDTO.getDns());
                interfaceVO.setJMXIp(interfaceDTO.getIp());
                interfaceVO.setJMXPort(interfaceDTO.getPort());
                interfaceVO.setJMXUseIp(ZApiParameter.HOST_INTERFACE_USEIP.USEIP.value == Integer.parseInt(interfaceDTO.getUseIp()) ? true :false);
            }
        }
        return interfaceVO;
    }
}
