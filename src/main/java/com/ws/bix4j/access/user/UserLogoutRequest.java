package com.ws.bix4j.access.user;

import com.ws.bix4j.access.ZRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2017/4/6
 * @Author chen
 */
public class UserLogoutRequest extends ZRequest {
    private List params = new ArrayList();
    public UserLogoutRequest() {
        this.setMethod("user.logout");
    }

    public List getParams() {
        return params;
    }

    public void setParams(List params) {
        this.params = params;
    }
}
