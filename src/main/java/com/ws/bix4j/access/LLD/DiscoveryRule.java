package com.ws.bix4j.access.LLD;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ws.bix4j.access.ZApiMethod;
import com.ws.bix4j.exception.ZApiException;

import java.util.List;

/**
 * Created by zhongkf on 2018/1/30
 */
public class DiscoveryRule extends ZApiMethod {

    public DiscoveryRule(String apiUrl, String auth) {
        super(apiUrl, auth);
    }

    public <T> List<T> get(DiscoGetRequest request, Class<T> clazz) throws ZApiException {
        request.setAuth(this.auth);
        String responseJson = sendRequest(JSON.toJSONString(request, SerializerFeature.NotWriteDefaultValue));
        List<T> response = JSON.parseArray(JSON.parseObject(responseJson).getJSONArray("result").toString(), clazz);
        return response;
    }
}
