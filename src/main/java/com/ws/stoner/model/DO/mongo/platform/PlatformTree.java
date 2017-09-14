package com.ws.stoner.model.DO.mongo.platform;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by pc on 2017/8/10.
 */
@Document(collection = "platform_tree")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlatformTree {
    @Id
    private String id;
    private String label;
    private String type;
    private List<PlatformTree> children;

    public PlatformTree() {
    }

    public PlatformTree(String id, String label, String type) {
        this.id = id;
        this.label = label;
        this.type = type;
    }


    public PlatformTree(String id, String label, String type, List<PlatformTree> children) {
        this.id = id;
        this.label = label;
        this.type = type;
        this.children = children;
    }


    @Override
    public String toString() {
        return "PlatformTree{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", type='" + type + '\'' +
                ", children=" + children +
                '}';
    }

    public String getId() {
        return id;
    }

    public PlatformTree setId(String id) {
        this.id = id;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public PlatformTree setLabel(String label) {
        this.label = label;
        return this;
    }

    public String getType() {
        return type;
    }

    public PlatformTree setType(String type) {
        this.type = type;
        return this;
    }

    public List<PlatformTree> getChildren() {
        return children;
    }

    public PlatformTree setChildren(List<PlatformTree> children) {
        this.children = children;
        return this;
    }
}
