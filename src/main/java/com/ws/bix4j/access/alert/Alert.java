package com.ws.bix4j.access.alert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ws.bix4j.access.ZApiMethod;
import com.ws.bix4j.exception.ZApiException;

import java.util.List;

/**
 * Created by zkf on 2017/8/22.
 */
public class Alert extends ZApiMethod {
    public Alert(String apiUrl, String auth) {
        super(apiUrl, auth);
    }

    public <T> List<T> get(AlertGetRequest request, Class<T> clazz) throws ZApiException {
        request.setAuth(this.auth);
        String responseJson = sendRequest(JSON.toJSONString(request, SerializerFeature.NotWriteDefaultValue));
        List<T> response = JSON.parseArray(JSON.parseObject(responseJson).getJSONArray("result").toString(), clazz);
        return response;
    }
}
