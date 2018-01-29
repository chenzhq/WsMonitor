package com.ws.stoner.model;

/**
 * Created by zhongkf on 2018/1/29
 */
public class ExportItemVO {

    private String itemName;
    private String key;
    private String itemType;
    private String unit;
    private String warning;
    private String high;
    private boolean isAlter;
    private String description;

    public String getItemName() {
        return itemName;
    }

    public ExportItemVO setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public String getKey() {
        return key;
    }

    public ExportItemVO setKey(String key) {
        this.key = key;
        return this;
    }

    public String getItemType() {
        return itemType;
    }

    public ExportItemVO setItemType(String itemType) {
        this.itemType = itemType;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public ExportItemVO setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public String getWarning() {
        return warning;
    }

    public ExportItemVO setWarning(String warning) {
        this.warning = warning;
        return this;
    }

    public String getHigh() {
        return high;
    }

    public ExportItemVO setHigh(String high) {
        this.high = high;
        return this;
    }

    public boolean isAlter() {
        return isAlter;
    }

    public ExportItemVO setAlter(boolean alter) {
        isAlter = alter;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ExportItemVO setDescription(String description) {
        this.description = description;
        return this;
    }
}
