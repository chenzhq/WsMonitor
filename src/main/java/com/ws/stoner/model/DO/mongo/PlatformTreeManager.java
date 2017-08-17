package com.ws.stoner.model.DO.mongo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Created by zkf on 2017/8/17.
 */
@Document(collection = "platform_tree")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlatformTreeManager {

    @Id
    private String id;
    @Field("label")
    private String text;
    private String type;
    private List<PlatformTreeManager> children;

    public PlatformTreeManager(String id, String text, String type) {
        this.id = id;
        this.text = text;
        this.type = type;
    }

    public PlatformTreeManager(String id, String text, String type, List<PlatformTreeManager> children) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.children = children;
    }

    public PlatformTreeManager() {

    }

    public String getId() {
        return id;
    }

    public PlatformTreeManager setId(String id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public PlatformTreeManager setText(String text) {
        this.text = text;
        return this;
    }

    public String getType() {
        return type;
    }

    public PlatformTreeManager setType(String type) {
        this.type = type;
        return this;
    }

    public List<PlatformTreeManager> getChildren() {
        return children;
    }

    public PlatformTreeManager setChildren(List<PlatformTreeManager> children) {
        this.children = children;
        return this;
    }

    @Override
    public String toString() {
        return "PlatformTreeManager{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", children=" + children +
                '}';
    }
}
