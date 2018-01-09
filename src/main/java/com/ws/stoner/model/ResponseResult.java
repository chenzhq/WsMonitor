package com.ws.stoner.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ws.stoner.constant.ResponseErrorEnum;
import com.ws.stoner.constant.ResponseSuccessEnum;

/**
 * Created by chenzheqi on 2017/5/24.
 */
public class ResponseResult<T> {
    private boolean success;
    private String message;
    @JSONField(serialzeFeatures = SerializerFeature.NotWriteDefaultValue)
    private T data;
    @JSONField(name="status",serialzeFeatures = SerializerFeature.NotWriteDefaultValue)
    private String Code;

    public ResponseResult setInfo(ResponseErrorEnum errorEnum) {
        this.Code = errorEnum.code;
        this.message = errorEnum.name;
        return this;
    }

    public ResponseResult setInfo(ResponseSuccessEnum successEnum) {
        this.Code = successEnum.code;
        this.message = successEnum.name;
        return this;
    }


    public boolean isSuccess() {
        return success;
    }

    public ResponseResult setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getCode() {
        return Code;
    }

    public ResponseResult<T> setCode(String code) {
        Code = code;
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
