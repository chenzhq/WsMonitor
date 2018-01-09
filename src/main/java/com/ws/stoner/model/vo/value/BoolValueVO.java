package com.ws.stoner.model.vo.value;

/**
 * Created by zhongkf on 2018/1/3
 */
public class BoolValueVO extends ValueVO {

    private boolean value;

    public BoolValueVO() {
    }

    public BoolValueVO(Long clock, boolean value) {
        super(clock);
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }

    public BoolValueVO setValue(boolean value) {
        this.value = value;
        return this;
    }
}
