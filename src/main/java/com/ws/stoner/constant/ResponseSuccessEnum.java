package com.ws.stoner.constant;

/**
 * Created by zhongkf on 2018/1/8
 */
public enum ResponseSuccessEnum {

    REST_RESPONSE_SUCCESS(MessageConsts.REST_RESPONSE_SUCCESS, "200"),
    REST_UPDATE_SUCCESS(MessageConsts.REST_UPDATE_SUCCESS, "201");

    public String name;
    public String code;

    ResponseSuccessEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    ResponseSuccessEnum(String name) {
        this.name = name;
    }
}
