package com.ws.bix4j.access.user;

import com.ws.bix4j.access.ZResponse;

/**
 * @Date 2017/4/6
 * @Author chen
 */
public class UserLogoutResponse extends ZResponse<Boolean> {
    private boolean result;


    @Override
    public Boolean getResult() {
        return result;
    }
}
