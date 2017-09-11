package com.ws.stoner.model.DO.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Created by zkf on 2017/9/11.
 */
@Document(collection = "graph_view")
public class GraphView {
    @Id
    private String id;
    private String name;
    private String type;
    @Field("hostids")
    private List<String> hostIds;
    @Field("max_num")
    private Integer maxNum;

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

    public List<String> getHostIds() {
        return hostIds;
    }

    public GraphView setHostIds(List<String> hostIds) {
        this.hostIds = hostIds;
        return this;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public GraphView setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
        return this;
    }
}
