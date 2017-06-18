package com.ws.bix4j.access.application;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ws.bix4j.access.ZApiMethod;
import com.ws.bix4j.bean.ApplicationDO;
import com.ws.bix4j.exception.ZApiException;

import java.util.List;

/**
 * Created by pc on 2017/6/7.
 */
public class Application extends ZApiMethod {
    public Application (String apiUrl, String auth) {
        super(apiUrl, auth);
    }

    public List<ApplicationDO> get(ApplicationGetRequest ApplicationGetRequest) throws ZApiException {
        return get(ApplicationGetRequest, ApplicationDO.class);
    }

    public <T> List<T> get(ApplicationGetRequest request, Class<T> clazz) throws ZApiException {
        request.setAuth(this.auth);
        String responseJson = sendRequest(JSON.toJSONString(request, SerializerFeature.NotWriteDefaultValue));
        List<T> response = JSON.parseArray(JSON.parseObject(responseJson).getJSONArray("result").toString(), clazz);
        return response;
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
