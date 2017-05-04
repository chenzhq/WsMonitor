package com.ws.bix4j;

/**
 * @Date 2017/4/3
 * @Author chen
 */
public class ZApiException extends Exception{
    public ZApiException(String message){
        super(message);
    }

    public ZApiException(Throwable t){
        super(t);
    }
}
