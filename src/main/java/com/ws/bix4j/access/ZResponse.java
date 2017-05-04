package com.ws.bix4j.access;

/**
 * @Date 2017/4/4
 * @Author chen
 */
public abstract class ZResponse<T> {
    private int id;
    private String jsonrpc;

    private T result;

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

    public abstract T getResult();

    ;
}
