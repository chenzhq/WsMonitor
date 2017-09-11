package com.ws.stoner.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by pc on 2017/8/2.
 */
public class BriefValuemapDTO {

    @JSONField(name = "valuemapid")
    private String valuemapId;
    private String name;
    private List<Mapping> mappings;

    public static final String[] PROPERTY_NAMES = {"valuemapid", "name","mappings"};

    public static final String[] PROPERTY_MAPPINGS = {"value", "newvalue"};

    public class Mapping {
        private String value;
        @JSONField(name = "newvalue")
        private String newValue;

        public String getValue() {
            return value;
        }

        public Mapping setValue(String value) {
            this.value = value;
            return this;
        }

        public String getNewValue() {
            return newValue;
        }

        public Mapping setNewValue(String newValue) {
            this.newValue = newValue;
            return this;
        }

        @Override
        public String toString() {
            return "Mapping{" +
                    "value='" + value + '\'' +
                    ", newValue='" + newValue + '\'' +
                    '}';
        }
    }

    public String getValuemapId() {
        return valuemapId;
    }

    public BriefValuemapDTO setValuemapId(String valuemapId) {
        this.valuemapId = valuemapId;
        return this;
    }

    public String getName() {
        return name;
    }

    public BriefValuemapDTO setName(String name) {
        this.name = name;
        return this;
    }

    public List<Mapping> getMappings() {
        return mappings;
    }

    public BriefValuemapDTO setMappings(List<Mapping> mappings) {
        this.mappings = mappings;
        return this;
    }

    @Override
    public String toString() {
        return "BriefValuemapDTO{" +
                "valuemapId='" + valuemapId + '\'' +
                ", name='" + name + '\'' +
                ", mappings=" + mappings +
                '}';
    }
}
