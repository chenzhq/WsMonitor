package com.ws.stoner.model.mongo.hosttree;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by zhongkf on 2017/12/18
 */
@Document(collection = "host_tree")
public class HostNode {

    @Id
    private String id;
    @Field("node_id")
    private String nodeId;
    @Field("node_name")
    private String nodeName;
    @Field("parent_id")
    private String parentId;

    public HostNode() {
    }

    public HostNode(String nodeId, String nodeName, String parentId) {
        this.nodeId = nodeId;
        this.nodeName = nodeName;
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public HostNode setId(String id) {
        this.id = id;
        return this;
    }

    public String getNodeId() {
        return nodeId;
    }

    public HostNode setNodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    public String getNodeName() {
        return nodeName;
    }

    public HostNode setNodeName(String nodeName) {
        this.nodeName = nodeName;
        return this;
    }

    public String getParentId() {
        return parentId;
    }

    public HostNode setParentId(String parentId) {
        this.parentId = parentId;
        return this;
    }

    @Override
    public String toString() {
        return "HostNode{" +
                "id='" + id + '\'' +
                ", nodeId='" + nodeId + '\'' +
                ", nodeName='" + nodeName + '\'' +
                ", parentId='" + parentId + '\'' +
                '}';
    }
}
