package com.ws.bix4j.access.group;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ws.bix4j.access.ZApiMethod;
import com.ws.bix4j.exception.ZApiException;


/**
 * Created by zkf on 2017/5/18.
 */
public class Group extends ZApiMethod{
    public Group(String apiUrl, String auth ) {
        super(apiUrl,auth);
    }

    public GroupGetResponse get(GroupGetRequest groupGetRequest) throws ZApiException {
        groupGetRequest.setAuth(this.auth);
        String responseJson = sendRequest(JSON.toJSONString(groupGetRequest, SerializerFeature.NotWriteDefaultValue));
        GroupGetResponse groupGetResponse = JSON.parseObject(responseJson, GroupGetResponse.class);
        return groupGetResponse;
    }
}
