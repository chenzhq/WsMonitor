package com.ws.stoner.model.DO.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Arrays;

/**
 * Created by pc on 2017/7/18.
 */
@Document(collection = "graph_type")
public class GraphType {
    @Id
    private String id;
    @Field("value_type")
    private String valueType;
    @Field("graph_type")
    private String[] graphType;

    public String getId() {
        return id;
    }

    public GraphType setId(String id) {
        this.id = id;
        return this;
    }

    public String getValueType() {
        return valueType;
    }

    public GraphType setValueType(String valueType) {
        this.valueType = valueType;
        return this;
    }

    public String[] getGraphType() {
        return graphType;
    }

    public GraphType setGraphType(String[] graphType) {
        this.graphType = graphType;
        return this;
    }

    @Override
    public String toString() {
        return "GraphType{" +
                "id='" + id + '\'' +
                ", valueType='" + valueType + '\'' +
                ", graphType=" + Arrays.toString(graphType) +
                '}';
    }
}
