package com.ws.stoner.utils;

import com.alibaba.fastjson.JSON;
import com.ws.stoner.constant.ResponseErrorEnum;
import com.ws.stoner.model.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chenzheqi on 2017/5/24.
 */
public class RestResultGenerator {
    private static final Logger logger = LoggerFactory.getLogger(RestResultGenerator.class);

    public static <T> ResponseResult<T> genResult(T data, String message) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setSuccess(true).setMessage(message).setData(data);
        logger.debug("responseResult : {}", JSON.toJSONString(result));
        return result;
    }

    public static <T> ResponseResult<T> genErrorResult(ResponseErrorEnum errorEnum) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setSuccess(false).setErrorInfo(errorEnum);
        logger.debug("error: {}", JSON.toJSONString(result));

        return result;
    }

}
