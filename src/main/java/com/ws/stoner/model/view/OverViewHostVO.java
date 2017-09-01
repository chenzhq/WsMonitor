package com.ws.stoner.model.view;

/**
 * Created by zkf on 2017/9/1.
 */
public class OverViewHostVO {

    private String id;
    private String parent;
    private String text;
    private String type;
    private String status;

    public OverViewHostVO() {
    }

    public OverViewHostVO(String id, String parent, String text, String type, String status) {
        this.id = id;
        this.parent = parent;
        this.text = text;
        this.type = type;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public OverViewHostVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getParent() {
        return parent;
    }

    public OverViewHostVO setParent(String parent) {
        this.parent = parent;
        return this;
    }

    public String getText() {
        return text;
    }

    public OverViewHostVO setText(String text) {
        this.text = text;
        return this;
    }

    public String getType() {
        return type;
    }

    public OverViewHostVO setType(String type) {
        this.type = type;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public OverViewHostVO setStatus(String status) {
        this.status = status;
        return this;
    }
}
