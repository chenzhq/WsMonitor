package com.ws.stoner.model.mongoDO;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Arrays;

/**
 * Created by pc on 2017/6/28.
 */
@Document(collection = "mongo_group")
public class MongoGroupDO {
    @Id
    private String id;
    private String name;
    private String flag;
    @Field("group_children")
    private String[] groupChildren;
    @Field("host_children")
    private String[] hostChildren;

    public MongoGroupDO(String name, String flag, String[] groupChildren, String[] hostChildren) {
        this.name = name;
        this.flag = flag;
        this.groupChildren = groupChildren;
        this.hostChildren = hostChildren;
    }

    public String getName() {
        return name;
    }

    public MongoGroupDO setName(String name) {
        this.name = name;
        return this;
    }

    public String getFlag() {
        return flag;
    }

    public MongoGroupDO setFlag(String flag) {
        this.flag = flag;
        return this;
    }

    public String[] getGroupChildren() {
        return groupChildren;
    }

    public MongoGroupDO setGroupChildren(String[] groupChildren) {
        this.groupChildren = groupChildren;
        return this;
    }

    public String[] getHostChildren() {
        return hostChildren;
    }

    public MongoGroupDO setHostChildren(String[] hostChildren) {
        this.hostChildren = hostChildren;
        return this;
    }

    public String getId() {

        return id;
    }

    public MongoGroupDO setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "MongoGroupDO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", flag='" + flag + '\'' +
                ", groupChildren=" + Arrays.toString(groupChildren) +
                ", hostChildren=" + Arrays.toString(hostChildren) +
                '}';
    }
}