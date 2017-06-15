package com.ws.stoner.constant;

/**
 * Created by chenzheqi on 2017/5/24.
 */
public enum ResponseErrorEnum {
    ILLEGAL_PARAMS("请求参数不合法"),
    SERVICE_HANDLE_ERROR("服务器处理错误");

    public String name;

    ResponseErrorEnum(String name) {
        this.name = name;
    }
}
