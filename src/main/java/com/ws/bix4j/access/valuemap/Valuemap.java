package com.ws.bix4j.access.valuemap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ws.bix4j.access.ZApiMethod;
import com.ws.bix4j.exception.ZApiException;

import java.util.List;

/**
 * Created by zkf on 2017/8/2.
 */
public class Valuemap extends ZApiMethod {

    public Valuemap(String apiUrl, String auth) {
        super(apiUrl, auth);
    }

    public <T> List<T> get(ValuemapGetRequest request, Class<T> clazz) throws ZApiException {
        request.setAuth(this.auth);
        String responseJson = sendRequest(JSON.toJSONString(request, SerializerFeature.NotWriteDefaultValue));
        List<T> response = JSON.parseArray(JSON.parseObject(responseJson).getJSONArray("result").toString(), clazz);
        return response;
    }

}
