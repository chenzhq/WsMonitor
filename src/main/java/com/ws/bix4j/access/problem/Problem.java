package com.ws.bix4j.access.problem;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ws.bix4j.access.ZApiMethod;
import com.ws.bix4j.bean.ProblemDO;
import com.ws.bix4j.exception.ZApiException;

import java.util.List;

/**
 * Created by chenzheqi on 2017/5/22.
 */
public class Problem extends ZApiMethod {
    public Problem(String apiUrl, String auth) {
        super(apiUrl, auth);
    }

    public List<ProblemDO> get(ProblemGetRequest problemGetRequest) throws ZApiException {
        return get(problemGetRequest, ProblemDO.class);
    }

    public <T> List<T> get(ProblemGetRequest request, Class<T> clazz) throws ZApiException {
        request.setAuth(this.auth);
        String responseJson = sendRequest(JSON.toJSONString(request, SerializerFeature.NotWriteDefaultValue));
        List<T> response = JSON.parseArray(JSON.parseObject(responseJson).getJSONArray("result").toString(), clazz);
        return response;
    }

    public int count(ProblemGetRequest request) throws ZApiException {
        if (!request.getParams().isCountOutput()) {
            request.getParams().setCountOutput(true);
        }
        request.setAuth(this.auth);

        String responseJson = sendRequest(JSON.toJSONString(request, SerializerFeature.NotWriteDefaultValue));
        JSONObject jsonObject = JSONObject.parseObject(responseJson);
        return (int) jsonObject.get("result");
    }
}
