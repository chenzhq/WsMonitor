package com.ws.stoner.model.vo;

import com.ws.stoner.model.vo.value.ValueMappingVO;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/8
 */
public class ItemDetailVO {

    private String id;
    private String name;
    private String state;
    private String valueType;
    private String lastTime;
    private String lastValue;
    private String unit;
    private Integer weight;
    private boolean isCore;
    private String description;
    private List<String> graphType;
    private Integer multiplier;
    private Integer interval;
    private Integer historyRetention;
    private String storedValue;
    private List<ValueMappingVO> valueMapping;

    public ItemDetailVO(String id, String name, String state, String valueType, String lastTime, String lastValue, String unit, Integer weight, boolean isCore, String description, List<String> graphType, Integer multiplier, Integer interval, Integer historyRetention, String storedValue, List<ValueMappingVO> valueMapping) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.valueType = valueType;
        this.lastTime = lastTime;
        this.lastValue = lastValue;
        this.unit = unit;
        this.weight = weight;
        this.isCore = isCore;
        this.description = description;
        this.graphType = graphType;
        this.multiplier = multiplier;
        this.interval = interval;
        this.historyRetention = historyRetention;
        this.storedValue = storedValue;
        this.valueMapping = valueMapping;
    }

    public ItemDetailVO() {

    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ItemDetailVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", valueType='" + valueType + '\'' +
                ", lastTime='" + lastTime + '\'' +
                ", lastValue='" + lastValue + '\'' +
                ", unit='" + unit + '\'' +
                ", weight=" + weight +
                ", isCore=" + isCore +
                ", description='" + description + '\'' +
                ", graphType=" + graphType +
                ", multiplier=" + multiplier +
                ", interval=" + interval +
                ", historyRetention=" + historyRetention +
                ", storedValue='" + storedValue + '\'' +
                ", valueMapping=" + valueMapping +
                '}';
    }

    public ItemDetailVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ItemDetailVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getState() {
        return state;
    }

    public ItemDetailVO setState(String state) {
        this.state = state;
        return this;
    }

    public String getValueType() {
        return valueType;
    }

    public ItemDetailVO setValueType(String valueType) {
        this.valueType = valueType;
        return this;
    }

    public String getLastTime() {
        return lastTime;
    }

    public ItemDetailVO setLastTime(String lastTime) {
        this.lastTime = lastTime;
        return this;
    }

    public String getLastValue() {
        return lastValue;
    }

    public ItemDetailVO setLastValue(String lastValue) {
        this.lastValue = lastValue;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public ItemDetailVO setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public Integer getWeight() {
        return weight;
    }

    public ItemDetailVO setWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public boolean isCore() {
        return isCore;
    }

    public ItemDetailVO setCore(boolean core) {
        isCore = core;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ItemDetailVO setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<String> getGraphType() {
        return graphType;
    }

    public ItemDetailVO setGraphType(List<String> graphType) {
        this.graphType = graphType;
        return this;
    }

    public Integer getMultiplier() {
        return multiplier;
    }

    public ItemDetailVO setMultiplier(Integer multiplier) {
        this.multiplier = multiplier;
        return this;
    }

    public Integer getInterval() {
        return interval;
    }

    public ItemDetailVO setInterval(Integer interval) {
        this.interval = interval;
        return this;
    }

    public Integer getHistoryRetention() {
        return historyRetention;
    }

    public ItemDetailVO setHistoryRetention(Integer historyRetention) {
        this.historyRetention = historyRetention;
        return this;
    }

    public String getStoredValue() {
        return storedValue;
    }

    public ItemDetailVO setStoredValue(String storedValue) {
        this.storedValue = storedValue;
        return this;
    }

    public List<ValueMappingVO> getValueMapping() {
        return valueMapping;
    }

    public ItemDetailVO setValueMapping(List<ValueMappingVO> valueMapping) {
        this.valueMapping = valueMapping;
        return this;
    }
}
