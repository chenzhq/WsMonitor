package com.ws.bix4j.access.hostgroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ws.bix4j.access.ZApiMethod;
import com.ws.bix4j.bean.HostGroupDO;
import com.ws.bix4j.exception.ZApiException;

import java.util.List;


/**
 * Created by zkf on 2017/5/18.
 */
public class HostGroup extends ZApiMethod {
    public HostGroup(String apiUrl, String auth) {
        super(apiUrl,auth);
    }

    public List<HostGroupDO> get(HostGroupGetRequest HostGroupGetRequest) throws ZApiException {
        return get(HostGroupGetRequest, HostGroupDO.class);
    }

    public <T> List<T> get(HostGroupGetRequest request, Class<T> clazz) throws ZApiException {
        request.setAuth(this.auth);
        String responseJson = sendRequest(JSON.toJSONString(request, SerializerFeature.NotWriteDefaultValue));
        List<T> response = JSON.parseArray(JSON.parseObject(responseJson).getJSONArray("result").toString(), clazz);
        return response;
    }

    //返回主机组数量
    public int count(HostGroupGetRequest request) throws ZApiException {
        if (!request.getParams().isCountOutput()) {
            request.getParams().setCountOutput(true);
        }
        request.setAuth(this.auth);

        String responseJson = sendRequest(JSON.toJSONString(request, SerializerFeature.NotWriteDefaultValue));
        return Integer.parseInt(JSONObject.parseObject(responseJson).get("result").toString());
    }

}
