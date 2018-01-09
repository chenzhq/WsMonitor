package com.ws.stoner.model.vo;

import com.ws.stoner.model.vo.hostinterface.InterfaceVO;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/8
 */
public class HostInterfacesVO {

    private String id;
    private String name;
    private List<InterfaceVO> interfaces;

    @Override
    public String toString() {
        return "HostInterfacesVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", interfaces=" + interfaces +
                '}';
    }

    public String getId() {

        return id;
    }

    public HostInterfacesVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public HostInterfacesVO setName(String name) {
        this.name = name;
        return this;
    }

    public List<InterfaceVO> getInterfaces() {
        return interfaces;
    }

    public HostInterfacesVO setInterfaces(List<InterfaceVO> interfaces) {
        this.interfaces = interfaces;
        return this;
    }

    public HostInterfacesVO(String id, String name, List<InterfaceVO> interfaces) {

        this.id = id;
        this.name = name;
        this.interfaces = interfaces;
    }

    public HostInterfacesVO() {

    }
}
