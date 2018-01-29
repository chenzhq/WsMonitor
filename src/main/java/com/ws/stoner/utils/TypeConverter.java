package com.ws.stoner.utils;

import com.ws.stoner.constant.HostTypeEnum;
import com.ws.stoner.constant.ItemTypeEnum;

/**
 * Created by zhongkf on 2018/1/5
 */
public  class TypeConverter {

    //设备类型转换 "模板-服务器"  ==>> "server"
    public static String transforHostType(String typeName) {

        if(HostTypeEnum.SERVER.name.equals(typeName)) {
            //服务器
            return HostTypeEnum.SERVER.text;
        }else if(HostTypeEnum.DATABASE.name.equals(typeName)) {
            //数据库
            return HostTypeEnum.DATABASE.text;
        }else if(HostTypeEnum.SWITCH.name.equals(typeName)) {
            //交换机
            return HostTypeEnum.SWITCH.text;
        }else if(HostTypeEnum.ROUTER.name.equals(typeName)) {
            //路由器
            return HostTypeEnum.ROUTER.text;
        }else if(HostTypeEnum.FIREWALL.name.equals(typeName)) {
            //防火墙
            return HostTypeEnum.FIREWALL.text;
        }else if(HostTypeEnum.AP.name.equals(typeName)) {
            //无线AP
            return HostTypeEnum.AP.text;
        }else if(HostTypeEnum.HARDWARE.name.equals(typeName)) {
            //硬件
            return HostTypeEnum.HARDWARE.text;
        }else if(HostTypeEnum.STORAGE.name.equals(typeName)) {
            //存储
            return HostTypeEnum.STORAGE.text;
        }else if(HostTypeEnum.HYPERVISOR.name.equals(typeName)) {
            //物理机
            return HostTypeEnum.HYPERVISOR.text;
        }else if(HostTypeEnum.VIRTUAL.name.equals(typeName)) {
            //虚拟机
            return HostTypeEnum.VIRTUAL.text;
        }else if(HostTypeEnum.VIRTUALBOTTOM.name.equals(typeName)) {
            //虚拟底层
            return HostTypeEnum.VIRTUALBOTTOM.text;
        }else if(HostTypeEnum.CAMERA.name.equals(typeName)) {
            //摄像机
            return HostTypeEnum.CAMERA.text;
        }else {
            return "none";
        }

    }

    public static String TransforItemType(String itemType) {
        int type = Integer.parseInt(itemType);
        String typeName = "";
        if(ItemTypeEnum.ZABBIX_AGENT.getCode() == type) {
            typeName = ItemTypeEnum.ZABBIX_AGENT.getName();
        }else if(ItemTypeEnum.ZABBIX_TRAPPER.getCode() == type) {
            typeName = ItemTypeEnum.ZABBIX_TRAPPER.getName();
        }else if(ItemTypeEnum.SIMPLE_CHECK.getCode() == type) {
            typeName = ItemTypeEnum.SIMPLE_CHECK.getName();
        }else if(ItemTypeEnum.SNMPV2_AGENT.getCode() == type) {
            typeName = ItemTypeEnum.SNMPV2_AGENT.getName();
        }else if(ItemTypeEnum.ZABBIX_INTERNAL.getCode() == type) {
            typeName = ItemTypeEnum.ZABBIX_INTERNAL.getName();
        }else if(ItemTypeEnum.SNMPV3_AGENT.getCode() == type) {
            typeName = ItemTypeEnum.SNMPV3_AGENT.getName();
        }else if(ItemTypeEnum.ZABBIX_AGENT_ACTIVE.getCode() == type) {
            typeName = ItemTypeEnum.ZABBIX_AGENT_ACTIVE.getName();
        }else if(ItemTypeEnum.ZABBIX_AGGREGATE.getCode() == type) {
            typeName = ItemTypeEnum.ZABBIX_AGGREGATE.getName();
        }else if(ItemTypeEnum.WEB_ITEM.getCode() == type) {
            typeName = ItemTypeEnum.WEB_ITEM.getName();
        }else if(ItemTypeEnum.EXTERNAL_CHECK.getCode() == type) {
            typeName = ItemTypeEnum.EXTERNAL_CHECK.getName();
        }else if(ItemTypeEnum.DATABASE_MONITOR.getCode() == type) {
            typeName = ItemTypeEnum.DATABASE_MONITOR.getName();
        }else if(ItemTypeEnum.IPMI_AGENT.getCode() == type) {
            typeName = ItemTypeEnum.IPMI_AGENT.getName();
        }else if(ItemTypeEnum.SSH_AGENT.getCode() == type) {
            typeName = ItemTypeEnum.SSH_AGENT.getName();
        }else if(ItemTypeEnum.TELNET_AGENT.getCode() == type) {
            typeName = ItemTypeEnum.TELNET_AGENT.getName();
        }else if(ItemTypeEnum.CALCULATED.getCode() == type) {
            typeName = ItemTypeEnum.CALCULATED.getName();
        }else if(ItemTypeEnum.JMX_AGENT.getCode() == type) {
            typeName = ItemTypeEnum.JMX_AGENT.getName();
        }else if(ItemTypeEnum.SNMP_TRAP.getCode() == type) {
            typeName = ItemTypeEnum.SNMP_TRAP.getName();
        }else {

        }
        return typeName;
    }
}
