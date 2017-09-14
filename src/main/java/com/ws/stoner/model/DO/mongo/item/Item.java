package com.ws.stoner.model.DO.mongo.item;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by pc on 2017/7/12.
 */
@Document(collection = "item")
public class Item {
    @Id
    private String id;
    @Field("item_id")
    private String itemId;
    @Field("host_id")
    private String hostId;
    @Field("graph_name")
    private String graphName;
    @Field("graph_type")
    private String graphType;

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", itemId='" + itemId + '\'' +
                ", hostId='" + hostId + '\'' +
                ", graphName='" + graphName + '\'' +
                ", graphType='" + graphType + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public Item setId(String id) {
        this.id = id;
        return this;
    }

    public String getItemId() {
        return itemId;
    }

    public Item setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getHostId() {
        return hostId;
    }

    public Item setHostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public String getGraphName() {
        return graphName;
    }

    public Item setGraphName(String graphName) {
        this.graphName = graphName;
        return this;
    }

    public String getGraphType() {
        return graphType;
    }

    public Item setGraphType(String graphType) {
        this.graphType = graphType;
        return this;
    }
}
