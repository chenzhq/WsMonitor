package com.ws.bix4j.exception;

/**
 * @Date 2017/4/3
 * @Author chen
 */
public class ZApiException extends Exception{

    private ZApiExceptionEnum code;

    public ZApiException(ZApiExceptionEnum code, String message) {
        super(message);
        this.code = code;
    }

    public ZApiExceptionEnum getCode() {
        return code;
    }
}
