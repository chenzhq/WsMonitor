package com.ws.stoner.constant;

/**
 * Created by zhongkf on 2018/1/29
 */
public enum ItemTypeEnum {
    ZABBIX_AGENT(0,"zabbix_agent","Zabbix 客户端"),
    SNMPV1_AGENT(1,"snmpv1_agent","SNMPv1 客户端"),
    ZABBIX_TRAPPER(2,"zabbix_trapper","Zabbix 采集器"),
    SIMPLE_CHECK(3,"simple_check","简单检查"),
    SNMPV2_AGENT(4,"snmpv2_agent","SNMPv2 客户端"),
    ZABBIX_INTERNAL(5,"zabbix_internal","Zabbix 内部"),
    SNMPV3_AGENT(6,"snmpv3_agent","SNMPv3 客户端"),
    ZABBIX_AGENT_ACTIVE(7,"zabbix_agent_active","Zabbix 客户端（主动式）"),
    ZABBIX_AGGREGATE(8,"zabbix_aggregate","Zabbix 整合"),
    WEB_ITEM(9,"web_item","web_item"),
    EXTERNAL_CHECK(10,"external_check","外部检查"),
    DATABASE_MONITOR(11,"database_monitor","数据库监控"),
    IPMI_AGENT(12,"ipmi_agent","IPMI客户端"),
    SSH_AGENT(13,"ssh_agent","SSH客户端"),
    TELNET_AGENT(14,"telnet_agent","TELNET客户端"),
    CALCULATED(15,"calculated","可计算的"),
    JMX_AGENT(16,"jmx_agent","JMX客户端"),
    SNMP_TRAP(17,"snmp_trap","  snmp trap");

    private int code;
    private String text;
    private String name;

    ItemTypeEnum() {
    }

    ItemTypeEnum(int code, String text, String name) {
        this.code = code;
        this.text = text;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public ItemTypeEnum setCode(int code) {
        this.code = code;
        return this;
    }

    public String getText() {
        return text;
    }

    public ItemTypeEnum setText(String text) {
        this.text = text;
        return this;
    }

    public String getName() {
        return name;
    }

    public ItemTypeEnum setName(String name) {
        this.name = name;
        return this;
    }
}
