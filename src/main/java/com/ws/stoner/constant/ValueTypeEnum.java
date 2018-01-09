package com.ws.stoner.constant;

/**
 * Created by zhongkf on 2017/12/15
 */
public enum ValueTypeEnum {

    FLOAT("float",0),
    INTEGER("integer",1),
    BOOLEAN("boolean",2),
    TEXT("text",3),
    STRING("string",4),
    LOG("log",5);

    ValueTypeEnum() {
    }

    ValueTypeEnum(String type, int code) {
        this.type = type;
        this.code = code;
    }

    public String type;
    public int code;

    public String getType() {
        return type;
    }

    public ValueTypeEnum setType(String type) {
        this.type = type;
        return this;
    }

    public int getCode() {
        return code;
    }

    public ValueTypeEnum setCode(int code) {
        this.code = code;
        return this;
    }
}
