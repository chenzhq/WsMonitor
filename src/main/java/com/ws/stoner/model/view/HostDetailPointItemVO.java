package com.ws.stoner.model.view;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by pc on 2017/7/13.
 */
public class HostDetailPointItemVO {

    @JSONField(name = "item_id")
    private String itemId;
    private String name;
    private String value;
    private String state;

    public String getItemId() {
        return itemId;
    }

    public HostDetailPointItemVO setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    @Override
    public String
    toString() {
        return "HostDetailPointItemVO{" +
                "itemId='" + itemId + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", state='" + state + '\'' +
                '}';
    }


    public String getName() {
        return name;
    }

    public HostDetailPointItemVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public HostDetailPointItemVO setValue(String value) {
        this.value = value;
        return this;
    }

    public String getState() {
        return state;
    }

    public HostDetailPointItemVO setState(String state) {
        this.state = state;
        return this;
    }
}
