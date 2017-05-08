package com.ws.bix4j.access;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ws.bix4j.ZApiException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Date 2017/4/4
 * @Author chen
 */
public class ZApiMethod {

    private static Logger logger = LoggerFactory.getLogger(ZApiMethod.class);

    protected String apiUrl;
    protected String auth;

    protected ZApiMethod(String apiUrl, String auth) {
        this.apiUrl = apiUrl;
        this.auth = auth;
    }

    public ZApiMethod() {
    }

    protected String sendRequest(String requestJson) throws ZApiException {

        logger.debug("api request json：\n" + requestJson);

        // HTTP POST
        HttpResponse httpResponse;
        HttpPost httpPost = new HttpPost(apiUrl);

        String responseBody = null;
        try {
            httpPost.setHeader("Content-Type", "application/json-rpc");
            httpPost.setEntity(new StringEntity(requestJson));

            CloseableHttpClient client = HttpClients.createDefault();
            httpResponse = client.execute(httpPost);
            responseBody = EntityUtils.toString(httpResponse.getEntity());

        } catch (Exception e) {
            throw new ZApiException("HTTP request error");
        }

        // HTTP 状态错误
        if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            throw new ZApiException("HTTP error : " + responseBody);
        }


        // API 错误
        JSONObject responseJO = JSON.parseObject(responseBody);
        if (responseJO.containsKey("error")) {
            String message = "API error:" + responseJO.getString("error");
            message += "\nRequest:" + requestJson;
            throw new ZApiException(message);
        }


        // check id
        JSONObject requestJO = JSON.parseObject(requestJson);
        if (!requestJO.getInteger("id").equals(responseJO.getInteger("id"))) {
            throw new ZApiException("id not match");
        }


        logger.debug("api response json：\n" + responseBody);

        return responseBody;
    }
}
