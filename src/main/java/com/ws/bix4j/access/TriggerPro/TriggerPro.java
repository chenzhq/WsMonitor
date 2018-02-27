package com.ws.bix4j.access.TriggerPro;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ws.bix4j.access.ZApiMethod;
import com.ws.bix4j.exception.ZApiException;

import java.util.List;

/**
 * Created by pc on 2017/6/8.
 */
public class TriggerPro extends ZApiMethod {

    public TriggerPro(String apiUrl, String auth) {
        super(apiUrl, auth);
    }


    public <T> List<T> get(TriggerProGetRequest triggerProGetRequest, Class<T> clazz) throws ZApiException {
        triggerProGetRequest.setAuth(this.auth);
        String responseJson = sendRequest(JSON.toJSONString(triggerProGetRequest, SerializerFeature.NotWriteDefaultValue));
        String result = JSON.parseObject(responseJson).get("result").toString();
        return JSON.parseArray(result, clazz);

    }

    //返回触发器数量
    public int count(TriggerProGetRequest triggerProGetRequest) throws ZApiException {
        if(!triggerProGetRequest.getParams().isCountOutput()) {
            triggerProGetRequest.getParams().setCountOutput(true);
        }
        triggerProGetRequest.setAuth(this.auth);
        String responseJson = sendRequest(JSON.toJSONString(triggerProGetRequest,SerializerFeature.NotWriteDefaultValue));
        return Integer.parseInt(JSONObject.parseObject(responseJson).get("result").toString());

    }
}
