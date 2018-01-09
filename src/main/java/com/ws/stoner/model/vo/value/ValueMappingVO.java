package com.ws.stoner.model.vo.value;

/**
 * Created by zhongkf on 2017/12/8
 */
public class ValueMappingVO {
    private String value;
    private String newValue;

    public ValueMappingVO() {
    }

    @Override
    public String toString() {
        return "ValueMappingVO{" +
                "value='" + value + '\'' +
                ", newValue='" + newValue + '\'' +
                '}';
    }

    public String getValue() {
        return value;
    }

    public ValueMappingVO setValue(String value) {
        this.value = value;
        return this;
    }

    public String getNewValue() {
        return newValue;
    }

    public ValueMappingVO setNewValue(String newValue) {
        this.newValue = newValue;
        return this;
    }

    public ValueMappingVO(String value, String newValue) {

        this.value = value;
        this.newValue = newValue;
    }
}
