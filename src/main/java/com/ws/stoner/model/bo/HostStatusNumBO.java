package com.ws.stoner.model.bo;

/**
 * Created by chenzheqi on 2017/5/24.
 */
public class HostStatusNumBO {
    private String name;
    private int value;

    public HostStatusNumBO(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public HostStatusNumBO setName(String name) {
        this.name = name;
        return this;
    }

    public int getValue() {
        return value;
    }

    public HostStatusNumBO setValue(int value) {
        this.value = value;
        return this;
    }
}
