package com.ws.stoner.model.DO.mongo.view;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by zkf on 2017/9/11.
 */
@Document(collection = "graph_view")
public class GraphView {
    @Id
    private String id;
    private String name;
    private String type;

    public GraphView() {
    }

    public GraphView(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public GraphView setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public GraphView setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public GraphView setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return "GraphView{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
