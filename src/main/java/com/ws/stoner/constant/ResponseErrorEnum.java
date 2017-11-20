package com.ws.stoner.constant;

/**
 * Created by chenzheqi on 2017/5/24.
 */
public enum ResponseErrorEnum {
    ILLEGAL_PARAMS("请求参数不合法", "501"),
    AUTH_EXPIRE("需要重新登录", "407"),
    SERVICE_HANDLE_ERROR("服务器处理错误", "500"),
    ALREADY_EXISTS_ERROR("已存在该名称","430");

    public String name;
    public String code;

    ResponseErrorEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    ResponseErrorEnum(String name) {
        this.name = name;
    }
}
