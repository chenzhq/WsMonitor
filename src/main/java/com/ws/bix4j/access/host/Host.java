package com.ws.bix4j.access.host;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ws.bix4j.ZApiException;
import com.ws.bix4j.access.ZApiMethod;
import com.ws.bix4j.access.ZResponse;

import java.util.List;

/**
 * Created by chenzheqi on 2017/4/28.
 */
public class Host extends ZApiMethod{

    public Host(String apiUrl, String auth) {
        super(apiUrl, auth);
    }

    public HostGetResponse get(HostGetRequest hostGetRequest) throws ZApiException {
        hostGetRequest.setAuth(this.auth);
        String responseJson = sendRequest(JSON.toJSONString(hostGetRequest, SerializerFeature.NotWriteDefaultValue));
        HostGetResponse hostGetResponse = JSON.parseObject(responseJson, HostGetResponse.class);
        return hostGetResponse;
    }

}
