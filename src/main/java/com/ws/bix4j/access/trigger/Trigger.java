package com.ws.bix4j.access.trigger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ws.bix4j.access.ZApiMethod;
import com.ws.bix4j.exception.ZApiException;

/**
 * Created by pc on 2017/6/8.
 */
public class Trigger extends ZApiMethod {

    public Trigger(String apiUrl, String auth) {
        super(apiUrl, auth);
    }

    public TriggerGetResponse get(TriggerGetRequest triggerGetRequest) throws ZApiException{
        triggerGetRequest.setAuth(this.auth);
        String responseJson = sendRequest(JSON.toJSONString(triggerGetRequest, SerializerFeature.NotWriteDefaultValue));
        TriggerGetResponse triggerGetResponse = JSON.parseObject(responseJson
        ,TriggerGetResponse.class);
        return triggerGetResponse;
    }

    //返回触发器数量
    public int count(TriggerGetRequest triggerGetRequest) throws ZApiException {
        if(!triggerGetRequest.getParams().isCountOutput()) {
            triggerGetRequest.getParams().setCountOutput(true);
        }
        triggerGetRequest.setAuth(this.auth);
        String responseJson = sendRequest(JSON.toJSONString(triggerGetRequest,SerializerFeature.NotWriteDefaultValue));
        return Integer.parseInt(JSONObject.parseObject(responseJson).get("result").toString());

    }
}
