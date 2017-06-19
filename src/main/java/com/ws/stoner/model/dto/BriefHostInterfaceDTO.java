package com.ws.stoner.model.dto;

/**
 * Created by pc on 2017/6/19.
 */
public class BriefHostInterfaceDTO {
    private String ip;

    public static final String[] PROPERTY_NAMES = {"ip"};

    public String getIp() {
        return ip;
    }

    public BriefHostInterfaceDTO setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public static String[] getPropertyNames() {
        return PROPERTY_NAMES;
    }

    @Override
    public String toString() {
        return "BriefHostInterfaceDTO{" +
                "ip='" + ip + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BriefHostInterfaceDTO that = (BriefHostInterfaceDTO) o;

        return ip != null ? ip.equals(that.ip) : that.ip == null;
    }

    @Override
    public int hashCode() {
        return ip != null ? ip.hashCode() : 0;
    }
}
