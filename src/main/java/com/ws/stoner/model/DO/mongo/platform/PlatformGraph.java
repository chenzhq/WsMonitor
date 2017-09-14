package com.ws.stoner.model.DO.mongo.platform;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by zkf on 2017/8/11.
 */
@Document(collection = "platform_graph")
public class PlatformGraph {

    @Id
    private String id;
    @Field("item_id")
    private String itemId;
    @Field("host_id")
    private String hostId;
    @Field("platform_id")
    private String platformId;
    @Field("graph_name")
    private String graphName;
    @Field("graph_type")
    private String graphType;

    public PlatformGraph() {
    }

    public PlatformGraph(String itemId, String hostId, String platformId, String graphName, String graphType) {
        this.itemId = itemId;
        this.hostId = hostId;
        this.platformId = platformId;
        this.graphName = graphName;
        this.graphType = graphType;
    }

    public String getId() {
        return id;
    }

    public PlatformGraph setId(String id) {
        this.id = id;
        return this;
    }

    public String getItemId() {
        return itemId;
    }

    public PlatformGraph setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getHostId() {
        return hostId;
    }

    public PlatformGraph setHostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public String getPlatformId() {
        return platformId;
    }

    public PlatformGraph setPlatformId(String platformId) {
        this.platformId = platformId;
        return this;
    }

    public String getGraphName() {
        return graphName;
    }

    public PlatformGraph setGraphName(String graphName) {
        this.graphName = graphName;
        return this;
    }

    public String getGraphType() {
        return graphType;
    }

    public PlatformGraph setGraphType(String graphType) {
        this.graphType = graphType;
        return this;
    }

    @Override
    public String toString() {
        return "PlatformGraph{" +
                "id='" + id + '\'' +
                ", itemId='" + itemId + '\'' +
                ", hostId='" + hostId + '\'' +
                ", platformId='" + platformId + '\'' +
                ", graphName='" + graphName + '\'' +
                ", graphType='" + graphType + '\'' +
                '}';
    }
}
