package com.ws.bix4j.access.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ws.bix4j.exception.ZApiException;
import com.ws.bix4j.access.ZApiMethod;

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

    public UserGetResponse get(UserGetRequest request) throws ZApiException {
        request.setAuth(this.auth);

        UserGetResponse response;

        String responseJson = sendRequest(JSON.toJSONString(request, SerializerFeature.NotWriteDefaultValue));
        response = JSON.parseObject(responseJson, UserGetResponse.class);

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

    public void loginReturnInfo(UserLoginRequest request) throws ZApiException {
        String responseJson = sendRequest(JSON.toJSONString(request, SerializerFeature.NotWriteDefaultValue));

    }

    public UserLogoutResponse logout(UserLogoutRequest request) throws ZApiException {
        String requestString = JSON.toJSONString(request, SerializerFeature.WriteNullListAsEmpty);
        String responseJson = sendRequest(requestString);
        UserLogoutResponse response = JSON.parseObject(responseJson, UserLogoutResponse.class);
        return response;
    }
}
