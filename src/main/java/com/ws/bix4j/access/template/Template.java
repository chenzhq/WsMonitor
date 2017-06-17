package com.ws.bix4j.access.template;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ws.bix4j.access.ZApiMethod;
import com.ws.bix4j.bean.HostDO;
import com.ws.bix4j.exception.ZApiException;

import java.util.List;

/**
 * Created by chenzheqi on 2017/6/16.
 */
public class Template extends ZApiMethod{

    public Template(String apiUrl, String auth) {
        super(apiUrl, auth);
    }

    public List<HostDO> get(TemplateGetRequest TemplateGetRequest) throws ZApiException {
        return get(TemplateGetRequest, HostDO.class);
    }

    public <T> List<T> get(TemplateGetRequest request, Class<T> clazz) throws ZApiException {
        request.setAuth(this.auth);
        String responseJson = sendRequest(JSON.toJSONString(request, SerializerFeature.NotWriteDefaultValue));
        List<T> response = JSON.parseArray(JSON.parseObject(responseJson).getJSONArray("result").toString(), clazz);
        return response;
    }
    

    //返回主机数量
    public int count(TemplateGetRequest request) throws ZApiException {
        if (!request.getParams().isCountOutput()) {
            request.getParams().setCountOutput(true);
        }
        request.setAuth(this.auth);

        String responseJson = sendRequest(JSON.toJSONString(request, SerializerFeature.NotWriteDefaultValue));
        return Integer.parseInt(JSONObject.parseObject(responseJson).get("result").toString());
    }

}
