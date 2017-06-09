package com.ws.bix4j.access.item;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ws.bix4j.access.ZApiMethod;
import com.ws.bix4j.exception.ZApiException;

/**
 * Created by pc on 2017/6/9.
 */
public class Item extends ZApiMethod {

    public Item(String apiUrl, String auth) {
        super(apiUrl, auth);
    }

    //获取response对象
    public ItemGetResponse get(ItemGetRequest itemGetRequest) throws ZApiException {
        itemGetRequest.setAuth(this.auth);
        String responseJson = sendRequest(JSON.toJSONString(itemGetRequest, SerializerFeature.NotWriteDefaultValue));
        ItemGetResponse itemGetResponse = JSON.parseObject(responseJson,ItemGetResponse.class);
        return itemGetResponse;
    }

    //获取数量
    public int count(ItemGetRequest itemGetRequest) throws ZApiException {
        itemGetRequest.setAuth(this.auth);
        if (!itemGetRequest.getParams().isCountOutput()) {
            itemGetRequest.getParams().setCountOutput(true);
        }
        String responseJson = sendRequest(JSON.toJSONString(itemGetRequest, SerializerFeature.NotWriteDefaultValue));
        return Integer.parseInt(JSONObject.parseObject(responseJson).get("result").toString());
    }
}
