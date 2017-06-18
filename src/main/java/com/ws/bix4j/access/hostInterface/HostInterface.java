package com.ws.bix4j.access.hostInterface;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ws.bix4j.access.ZApiMethod;
import com.ws.bix4j.bean.HostInterfaceDO;
import com.ws.bix4j.exception.ZApiException;

import java.util.List;


/**
 * Created by zkf on 2017/5/18.
 */
public class HostInterface extends ZApiMethod {
    public HostInterface(String apiUrl, String auth) {
        super(apiUrl,auth);
    }

    public List<HostInterfaceDO> get(HostInterfaceGetRequest HostInterfaceGetRequest) throws ZApiException {
        return get(HostInterfaceGetRequest, HostInterfaceDO.class);
    }

    public <T> List<T> get(HostInterfaceGetRequest request, Class<T> clazz) throws ZApiException {
        request.setAuth(this.auth);
        String responseJson = sendRequest(JSON.toJSONString(request, SerializerFeature.NotWriteDefaultValue));
        List<T> response = JSON.parseArray(JSON.parseObject(responseJson).getJSONArray("result").toString(), clazz);
        return response;
    }

    //返回主机组数量
    public int count(HostInterfaceGetRequest request) throws ZApiException {
        if (!request.getParams().isCountOutput()) {
            request.getParams().setCountOutput(true);
        }
        request.setAuth(this.auth);

        String responseJson = sendRequest(JSON.toJSONString(request, SerializerFeature.NotWriteDefaultValue));
        return Integer.parseInt(JSONObject.parseObject(responseJson).get("result").toString());
    }

}
