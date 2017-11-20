package com.ws.stoner.model.view.itemvalue;

/**
 * Created by zkf on 2017/11/16.
 */
public class ItemValueUnit {

    private String value;
    private String units;

    public String getValue() {
        return value;
    }

    public ItemValueUnit setValue(String value) {
        this.value = value;
        return this;
    }

    public String getUnits() {
        return units;
    }

    public ItemValueUnit setUnits(String units) {
        this.units = units;
        return this;
    }

    public ItemValueUnit(String value, String units) {

        this.value = value;
        this.units = units;
    }

    public ItemValueUnit() {

    }

    @Override
    public String toString() {
        return "ItemValueUnit{" +
                "value='" + value + '\'' +
                ", units='" + units + '\'' +
                '}';
    }
}
