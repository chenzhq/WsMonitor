package com.ws.bix4j.access.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ws.bix4j.access.ZApiMethod;
import com.ws.bix4j.bean.UserDO;
import com.ws.bix4j.exception.ZApiException;

import java.util.List;

/**
 * @Date 2017/4/4
 * @Author chen
 */
public class User extends ZApiMethod {

    public User(String apiUrl, String auth) {
        super(apiUrl, auth);
    }

    public User(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public List<UserDO> get(UserGetRequest UserGetRequest) throws ZApiException {
        return get(UserGetRequest, UserDO.class);
    }

    public <T> List<T> get(UserGetRequest request, Class<T> clazz) throws ZApiException {
        request.setAuth(this.auth);
        String responseJson = sendRequest(JSON.toJSONString(request, SerializerFeature.NotWriteDefaultValue));
        List<T> response = JSON.parseArray(JSON.parseObject(responseJson).getJSONArray("result").toString(), clazz);
        return response;
    }

    public UserCreateResponse create(UserCreateRequest request) throws ZApiException {
        request.setAuth(this.auth);

        String responseJson = sendRequest(JSON.toJSONString(request, SerializerFeature.NotWriteDefaultValue));
        UserCreateResponse response = JSON.parseObject(responseJson, UserCreateResponse.class);
        return response;
    }

    public UserLoginResponse login(UserLoginRequest request) throws ZApiException {
        UserLoginResponse response;
        String responseJson = sendRequest(JSON.toJSONString(request, SerializerFeature.NotWriteDefaultValue));
        response = JSON.parseObject(responseJson, UserLoginResponse.class);
        return response;
    }


    public UserLogoutResponse logout(UserLogoutRequest request) throws ZApiException {
        String requestString = JSON.toJSONString(request, SerializerFeature.WriteNullListAsEmpty);
        String responseJson = sendRequest(requestString);
        UserLogoutResponse response = JSON.parseObject(responseJson, UserLogoutResponse.class);
        return response;
    }
}
