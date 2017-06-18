package com.ws.bix4j.access.trigger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ws.bix4j.access.ZApiMethod;
import com.ws.bix4j.bean.TriggerDO;
import com.ws.bix4j.exception.ZApiException;

import java.util.List;

/**
 * Created by pc on 2017/6/8.
 */
public class Trigger extends ZApiMethod {

    public Trigger(String apiUrl, String auth) {
        super(apiUrl, auth);
    }

    public List<TriggerDO> get(TriggerGetRequest triggerGetRequest) throws ZApiException {
        return get(triggerGetRequest, TriggerDO.class);
    }

    public <T> List<T> get(TriggerGetRequest triggerGetRequest, Class<T> clazz) throws ZApiException {
        triggerGetRequest.setAuth(this.auth);
        String responseJson = sendRequest(JSON.toJSONString(triggerGetRequest, SerializerFeature.NotWriteDefaultValue));
        String result = JSON.parseObject(responseJson).get("result").toString();
        return JSON.parseArray(result, clazz);

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
