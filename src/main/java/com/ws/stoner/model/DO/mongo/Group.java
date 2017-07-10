package com.ws.stoner.model.DO.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Arrays;

/**
 * Created by pc on 2017/6/28.
 */
@Document(collection = "group")
public class Group {
    @Id
    private String id;
    private String name;
    private String flag;
    @Field("cid")
    private String cId;
    @Field("pid")
    private String pId;
    @Field("group_children")
    private String[] groupChildren;
    @Field("host_children")
    private String[] hostChildren;

    public Group() {
    }

    public Group(String id, String name, String flag, String cId, String pId, String[] groupChildren, String[] hostChildren) {
        this.id = id;
        this.name = name;
        this.flag = flag;
        this.cId = cId;
        this.pId = pId;
        this.groupChildren = groupChildren;
        this.hostChildren = hostChildren;
    }

    public String getName() {
        return name;
    }

    public Group setName(String name) {
        this.name = name;
        return this;
    }

    public String getFlag() {
        return flag;
    }

    public Group setFlag(String flag) {
        this.flag = flag;
        return this;
    }

    public String getpId() {
        return pId;
    }

    public Group setpId(String pId) {
        this.pId = pId;
        return this;
    }

    public String[] getGroupChildren() {
        return groupChildren;
    }

    public Group setGroupChildren(String[] groupChildren) {
        this.groupChildren = groupChildren;
        return this;
    }

    public String[] getHostChildren() {
        return hostChildren;
    }

    public Group setHostChildren(String[] hostChildren) {
        this.hostChildren = hostChildren;
        return this;
    }

    public String getId() {

        return id;
    }

    public Group setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", flag='" + flag + '\'' +
                ", cId='" + cId + '\'' +
                ", pId='" + pId + '\'' +
                ", groupChildren=" + Arrays.toString(groupChildren) +
                ", hostChildren=" + Arrays.toString(hostChildren) +
                '}';
    }

    public Group(String name, String flag, String cId, String pId, String[] groupChildren, String[] hostChildren) {
        this.name = name;
        this.flag = flag;
        this.cId = cId;
        this.pId = pId;
        this.groupChildren = groupChildren;
        this.hostChildren = hostChildren;
    }

    public String getcId() {

        return cId;
    }

    public Group setcId(String cId) {
        this.cId = cId;
        return this;
    }
}
