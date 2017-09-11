package com.ws.bix4j.access.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ws.bix4j.access.ZApiMethod;
import com.ws.bix4j.exception.ZApiException;

import java.util.List;

/**
 * Created by zkf on 2017/8/22.
 */
public class Event extends ZApiMethod {

    public Event(String apiUrl, String auth) {
        super(apiUrl, auth);
    }

    public <T> List<T> get(EventGetRequest request, Class<T> clazz) throws ZApiException {
        request.setAuth(this.auth);
        String responseJson = sendRequest(JSON.toJSONString(request, SerializerFeature.NotWriteDefaultValue));
        List<T> response = JSON.parseArray(JSON.parseObject(responseJson).getJSONArray("result").toString(), clazz);
        return response;
    }

    public <T> T acknowledge(EventAcknowledgeRequest request, Class<T> clazz) throws ZApiException {
        request.setAuth(this.auth);
        String responseJson = sendRequest(JSON.toJSONString(request, SerializerFeature.NotWriteDefaultValue));
        T response = JSON.parseObject(JSON.parseObject(responseJson).getJSONObject("result").toString(), clazz);
        return response;
    }

}
