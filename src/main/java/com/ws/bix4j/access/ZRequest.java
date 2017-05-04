package com.ws.bix4j.access;

import java.util.Random;

/**
 * @Date 2017/4/5
 * @Author chen
 */
public abstract class ZRequest<T> {
    private int id = new Random().nextInt(Integer.MAX_VALUE);
    private String jsonrpc = "2.0";
    private String method;
    private String auth;
    private T params;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public abstract T getParams();
}
