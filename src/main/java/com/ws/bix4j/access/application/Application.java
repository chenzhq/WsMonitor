package com.ws.bix4j.access.application;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ws.bix4j.access.ZApiMethod;
import com.ws.bix4j.exception.ZApiException;

/**
 * Created by pc on 2017/6/7.
 */
public class Application extends ZApiMethod {
    public Application (String apiUrl, String auth) {
        super(apiUrl, auth);
    }

    public ApplicationGetResponse get(ApplicationGetRequest applicationGetRequest) throws ZApiException{
        applicationGetRequest.setAuth(this.auth);
        String responseJson = sendRequest(JSON.toJSONString(applicationGetRequest, SerializerFeature.NotWriteDefaultValue));
        ApplicationGetResponse applicationGetResponse = JSON.parseObject(responseJson,ApplicationGetResponse.class);
        return applicationGetResponse;
    }

    //返回监控点数量
    public int count(ApplicationGetRequest request) throws ZApiException {
        if (!request.getParams().isCountOutput()) {
            request.getParams().setCountOutput(true);
        }
        request.setAuth(this.auth);

        String responseJson = sendRequest(JSON.toJSONString(request, SerializerFeature.NotWriteDefaultValue));
        return Integer.parseInt(JSONObject.parseObject(responseJson).get("result").toString());
    }
}
