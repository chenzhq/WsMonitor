package com.ws.stoner.model.view;

import com.ws.stoner.model.DO.mongo.PlatformTree;

import java.util.List;

/**
 * Created by zkf on 2017/8/10.
 */
public class PlatformTreeVO {
    private String id;
    private String label;
    private String color;  //映射状态  state
    private String shape;  //映射类型 业务平台、集群、设备类型 type
    private List<PlatformTreeVO> children;

    @Override
    public String toString() {
        return "PlatformTreeVO{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", color='" + color + '\'' +
                ", shape='" + shape + '\'' +
                ", children=" + children +
                '}';
    }

    public PlatformTreeVO() {
    }

    public PlatformTreeVO(String id, String label, String color, String shape) {
        this.id = id;
        this.label = label;
        this.color = color;
        this.shape = shape;
    }

    public PlatformTreeVO(String id, String label, String color, String shape, List<PlatformTreeVO> children) {
        this.id = id;
        this.label = label;
        this.color = color;
        this.shape = shape;
        this.children = children;
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

    public String getColor() {
        return color;
    }

    public PlatformTreeVO setColor(String color) {
        this.color = color;
        return this;
    }

    public String getShape() {
        return shape;
    }

    public PlatformTreeVO setShape(String shape) {
        this.shape = shape;
        return this;
    }

    public List<PlatformTreeVO> getChildren() {
        return children;
    }

    public PlatformTreeVO setChildren(List<PlatformTreeVO> children) {
        this.children = children;
        return this;
    }
}