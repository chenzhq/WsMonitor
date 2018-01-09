package com.ws.stoner.model.vo.hostinterface;

/**
 * Created by zhongkf on 2017/12/8
 */
public class InterfaceVO {

    private String type;
    private String ip;
    private String dns;
    private boolean useip;
    private String port;
    private boolean main;
    private String bulk;

    @Override
    public String toString() {
        return "InterfaceVO{" +
                "type='" + type + '\'' +
                ", ip='" + ip + '\'' +
                ", dns='" + dns + '\'' +
                ", useip=" + useip +
                ", port='" + port + '\'' +
                ", main=" + main +
                ", bulk='" + bulk + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public InterfaceVO setType(String type) {
        this.type = type;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public InterfaceVO setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getDns() {
        return dns;
    }

    public InterfaceVO setDns(String dns) {
        this.dns = dns;
        return this;
    }

    public boolean isUseip() {
        return useip;
    }

    public InterfaceVO setUseip(boolean useip) {
        this.useip = useip;
        return this;
    }

    public String getPort() {
        return port;
    }

    public InterfaceVO setPort(String port) {
        this.port = port;
        return this;
    }

    public boolean isMain() {
        return main;
    }

    public InterfaceVO setMain(boolean main) {
        this.main = main;
        return this;
    }

    public String getBulk() {
        return bulk;
    }

    public InterfaceVO setBulk(String bulk) {
        this.bulk = bulk;
        return this;
    }

    public InterfaceVO(String type, String ip, String dns, boolean useip, String port, boolean main, String bulk) {

        this.type = type;
        this.ip = ip;
        this.dns = dns;
        this.useip = useip;
        this.port = port;
        this.main = main;
        this.bulk = bulk;
    }

    public InterfaceVO() {

    }
}
