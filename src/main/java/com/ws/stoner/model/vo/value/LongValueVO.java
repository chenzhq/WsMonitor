package com.ws.stoner.model.vo.value;

/**
 * Created by zhongkf on 2018/1/3
 */
public class LongValueVO extends ValueVO {

    private Long value;

    public LongValueVO() {
    }

    public LongValueVO(Long clock, Long value) {
        super(clock);
        this.value = value;
    }

    public Long getValue() {

        return value;
    }

    public LongValueVO setValue(Long value) {
        this.value = value;
        return this;
    }
}
