package com.ws.bix4j.access.host;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ws.bix4j.access.ZApiMethod;
import com.ws.bix4j.bean.HostDO;
import com.ws.bix4j.exception.ZApiException;

import java.util.List;

/**
 * Created by chenzheqi on 2017/4/28.
 */
public class Host extends ZApiMethod{

    public Host(String apiUrl, String auth) {
        super(apiUrl, auth);
    }

    public List<HostDO> get(HostGetRequest HostGetRequest) throws ZApiException {
        return get(HostGetRequest, HostDO.class);
    }

    public <T> List<T> get(HostGetRequest request, Class<T> clazz) throws ZApiException {
        request.setAuth(this.auth);
        String responseJson = sendRequest(JSON.toJSONString(request, SerializerFeature.NotWriteDefaultValue));
        List<T> response = JSON.parseArray(JSON.parseObject(responseJson).getJSONArray("result").toString(), clazz);
        return response;
    }
    

    //返回主机数量
    public int count(HostGetRequest request) throws ZApiException {
        if (!request.getParams().isCountOutput()) {
            request.getParams().setCountOutput(true);
        }
        request.setAuth(this.auth);

        String responseJson = sendRequest(JSON.toJSONString(request, SerializerFeature.NotWriteDefaultValue));
        return Integer.parseInt(JSONObject.parseObject(responseJson).get("result").toString());
    }

}
