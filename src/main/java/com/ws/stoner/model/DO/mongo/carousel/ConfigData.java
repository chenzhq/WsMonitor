package com.ws.stoner.model.DO.mongo.carousel;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by zkf on 2017/9/14.
 */
public class ConfigData {
    @JSONField(name = "block_name")
    @Field("block_name")
    private String blockName;
    @JSONField(name = "block_type")
    @Field("block_type")
    private String blockType;
    @JSONField(name = "graph_type")
    @Field("graph_type")
    private String graphType;
    private String contents;

    public ConfigData() {
    }

    public ConfigData(String blockName, String blockType, String graphType, String contents) {
        this.blockName = blockName;
        this.blockType = blockType;
        this.graphType = graphType;
        this.contents = contents;
    }

    public String getBlockName() {
        return blockName;
    }

    public ConfigData setBlockName(String blockName) {
        this.blockName = blockName;
        return this;
    }

    public String getBlockType() {
        return blockType;
    }

    public ConfigData setBlockType(String blockType) {
        this.blockType = blockType;
        return this;
    }

    public String getGraphType() {
        return graphType;
    }

    public ConfigData setGraphType(String graphType) {
        this.graphType = graphType;
        return this;
    }

    public String getContents() {
        return contents;
    }

    public ConfigData setContents(String contents) {
        this.contents = contents;
        return this;
    }

    @Override
    public String toString() {
        return "ConfigData{" +
                "blockName='" + blockName + '\'' +
                ", blockType='" + blockType + '\'' +
                ", graphType='" + graphType + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}