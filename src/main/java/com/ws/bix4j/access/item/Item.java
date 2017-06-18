package com.ws.bix4j.access.item;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ws.bix4j.access.ZApiMethod;
import com.ws.bix4j.bean.ItemDO;
import com.ws.bix4j.exception.ZApiException;

import java.util.List;

/**
 * Created by pc on 2017/6/9.
 */
public class Item extends ZApiMethod {

    public Item(String apiUrl, String auth) {
        super(apiUrl, auth);
    }

    public List<ItemDO> get(ItemGetRequest problemGetRequest) throws ZApiException {
        return get(problemGetRequest, ItemDO.class);
    }

    public <T> List<T> get(ItemGetRequest request, Class<T> clazz) throws ZApiException {
        request.setAuth(this.auth);
        String responseJson = sendRequest(JSON.toJSONString(request, SerializerFeature.NotWriteDefaultValue));
        List<T> response = JSON.parseArray(JSON.parseObject(responseJson).getJSONArray("result").toString(), clazz);
        return response;
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
