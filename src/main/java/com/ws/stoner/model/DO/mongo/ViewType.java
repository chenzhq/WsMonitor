package com.ws.stoner.model.DO.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Created by zkf on 2017/9/11.
 */
@Document(collection = "view_type")
public class ViewType {

    @Id
    private String id;
    private String name;
    private String type;

    public String getId() {
        return id;
    }

    public ViewType setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ViewType setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public ViewType setType(String type) {
        this.type = type;
        return this;
    }
}
