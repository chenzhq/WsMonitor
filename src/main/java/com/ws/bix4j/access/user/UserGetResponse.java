package com.ws.bix4j.access.user;

import com.ws.bix4j.access.ZResponse;
import com.ws.bix4j.access.ZResult;
import com.ws.bix4j.bean.UserDO;

import java.util.List;

/**
 * @Date 2017/4/5
 * @Author chen
 */
public class UserGetResponse extends ZResponse {
    private List<UserDO> result;

    @Override
    public List<UserDO> getResult() {
        return result;
    }

    public void setResult(List<UserDO> result) {
        this.result = result;
    }
}
