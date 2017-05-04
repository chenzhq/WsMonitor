package com.ws.bix4j.access.user;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.access.ZResponse;
import com.ws.bix4j.access.ZResult;

import java.util.List;

/**
 * @Date 2017/4/7
 * @Author chen
 */
public class UserCreateResponse extends ZResponse<ZResult> {
    private Result result = new Result();

    @Override
    public ZResult getResult() {
        return result;
    }

    public List<String> getUserIds() {
        return result.getUserIds();
    }

    public void setResult(Result result) {
        this.result = result;
    }

    private class Result implements ZResult {
        @JSONField(name = "userids")
        private List<String> userIds;

        public List<String> getUserIds() {
            return userIds;
        }

        public void setUserIds(List<String> userIds) {
            this.userIds = userIds;
        }
    }

}
