package com.ws.stoner.model.view.platform;


import java.util.List;

/**
 * Created by zkf on 2017/8/10.
 */
public class PlatformTreeVO {
    private String id;
    private String label;
    private String state;  //映射状态  state
    private String type;  //映射类型 业务平台、集群、设备类型 type
    private String url; //用于拼字符串映射取对应图标
    private List<PlatformTreeVO> children;

    public PlatformTreeVO() {
    }

    public PlatformTreeVO(String id, String label, String state, String type, String url) {
        this.id = id;
        this.label = label;
        this.state = state;
        this.type = type;
        this.url = url;
    }

    public PlatformTreeVO(String id, String label, String state, String type, String url, List<PlatformTreeVO> children) {
        this.id = id;
        this.label = label;
        this.state = state;
        this.type = type;
        this.url = url;
        this.children = children;
    }

    public String getState() {
        return state;
    }

    public PlatformTreeVO setState(String state) {
        this.state = state;
        return this;
    }

    public String getType() {
        return type;
    }

    public PlatformTreeVO setType(String type) {
        this.type = type;
        return this;
    }

    public String getId() {

        return id;
    }

    public PlatformTreeVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public PlatformTreeVO setLabel(String label) {
        this.label = label;
        return this;
    }

    public List<PlatformTreeVO> getChildren() {
        return children;
    }

    public PlatformTreeVO setChildren(List<PlatformTreeVO> children) {
        this.children = children;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public PlatformTreeVO setUrl(String url) {
        this.url = url;
        return this;
    }

    @Override
    public String toString() {
        return "PlatformTreeVO{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", state='" + state + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", children=" + children +
                '}';
    }
}