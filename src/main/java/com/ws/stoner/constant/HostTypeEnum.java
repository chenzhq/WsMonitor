package com.ws.stoner.constant;

/**
 * Created by zhongkf on 2018/1/5
 */
public enum HostTypeEnum {

    SERVER("模板-服务器","server"),
    DATABASE("模板-数据库","database"),
    SWITCH("模板-交换机","switch"),
    ROUTER("模板-路由器","router"),
    FIREWALL("模板-防火墙","firewall"),
    AP("模板-无线AP","AP"),
    STORAGE("模板-存储","storage"),
    CAMERA("模板-摄像机","camera"),
    HYPERVISOR("模板-物理机","hypervisor"),
    VIRTUAL("模板-虚拟机","virtual"),
    VIRTUALBOTTOM("模板-虚拟底层","virtualbottom"),
    HARDWARE("模板-硬件","hardware");

    public String name;
    public String text;

    HostTypeEnum(String name,String text) {
        this.name = name;
        this.text = text;
    }
}
