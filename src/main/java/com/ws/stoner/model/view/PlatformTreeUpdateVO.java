package com.ws.stoner.model.view;

import java.util.List;

/**
 * Created by pc on 2017/8/17.
 */
public class PlatformTreeUpdateVO {
    private String id;
    private String text;
    private String color;  //映射状态  state
    private String shape;  //映射类型 业务平台、集群、设备 type
    private List<PlatformTreeUpdateVO> children;

    public PlatformTreeUpdateVO() {
    }

    public PlatformTreeUpdateVO(String id, String text, String color, String shape) {
        this.id = id;
        this.text = text;
        this.color = color;
        this.shape = shape;
    }

    public PlatformTreeUpdateVO(String id, String text, String color, String shape, List<PlatformTreeUpdateVO> children) {
        this.id = id;
        this.text = text;
        this.color = color;
        this.shape = shape;
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public PlatformTreeUpdateVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public PlatformTreeUpdateVO setText(String text) {
        this.text = text;
        return this;
    }

    public String getColor() {
        return color;
    }

    public PlatformTreeUpdateVO setColor(String color) {
        this.color = color;
        return this;
    }

    public String getShape() {
        return shape;
    }

    public PlatformTreeUpdateVO setShape(String shape) {
        this.shape = shape;
        return this;
    }

    public List<PlatformTreeUpdateVO> getChildren() {
        return children;
    }

    public PlatformTreeUpdateVO setChildren(List<PlatformTreeUpdateVO> children) {
        this.children = children;
        return this;
    }

    @Override
    public String toString() {
        return "PlatformTreeUpdateVO{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", color='" + color + '\'' +
                ", shape='" + shape + '\'' +
                ", children=" + children +
                '}';
    }
}
