package com.ws.stoner.model.vo.value;

/**
 * Created by zhongkf on 2018/1/3
 */
public class StringValueVO extends ValueVO {

    private String value;

    public StringValueVO() {
    }

    public StringValueVO(Long clock, String value) {
        super(clock);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public StringValueVO setValue(String value) {
        this.value = value;
        return this;
    }
}
