package com.ws.bix4j.exception;

/**
 * Created by chenzheqi on 2017/5/10.
 */
public enum ZApiExceptionEnum {
    HTTP_REQUSET_ERROR(10001),
    HTTP_STATUS_ERROR(10002),
    ID_MATCH_ERROR(10003),
    ZBX_API_ERROR(10010),
    ZBX_API_AUTH_EXPIRE(10011),
    ZBX_API_LOGIN_ERROR(10012);

    private int code;
    ZApiExceptionEnum(int code) {
        this.code = code;
    }
}
