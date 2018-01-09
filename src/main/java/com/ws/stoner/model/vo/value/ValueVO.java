package com.ws.stoner.model.vo.value;

/**
 * Created by zhongkf on 2017/12/8
 */
public class ValueVO {

    private Long clock;

    public ValueVO() {
    }

    public ValueVO(Long clock) {
        this.clock = clock;
    }

    public Long getClock() {
        return clock;
    }

    public ValueVO setClock(Long clock) {
        this.clock = clock;
        return this;
    }
}
