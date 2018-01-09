package com.ws.stoner.model.vo.value;

/**
 * Created by zhongkf on 2018/1/3
 */
public class FloatValueVO extends ValueVO {

    private Float value;

    public FloatValueVO() {
    }

    public FloatValueVO(Long clock, Float value) {
        super(clock);
        this.value = value;
    }

    public Float getValue() {
        return value;
    }

    public FloatValueVO setValue(Float value) {
        this.value = value;
        return this;
    }
}
