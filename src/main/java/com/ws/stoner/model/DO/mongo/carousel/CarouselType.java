package com.ws.stoner.model.DO.mongo.carousel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by zkf on 2017/9/18.
 */
@Document(collection = "carousel_type")
public class CarouselType {
    @Id
    private String id;
    private String name;
    private String type;

    public CarouselType() {
    }

    public CarouselType(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public CarouselType setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CarouselType setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public CarouselType setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return "CarouselType{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
