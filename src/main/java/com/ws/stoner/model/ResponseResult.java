package com.ws.stoner.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ws.stoner.exception.ResponseErrorEnum;

/**
 * Created by chenzheqi on 2017/5/24.
 */
public class ResponseResult<T> {
    private boolean success;
    private String message;
    private T data;
    @JSONField(serialzeFeatures = SerializerFeature.NotWriteDefaultValue)
    private String errorCode;

    public void setErrorInfo(ResponseErrorEnum errorEnum) {
        this.errorCode = errorEnum.name();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public ResponseResult setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseResult setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
