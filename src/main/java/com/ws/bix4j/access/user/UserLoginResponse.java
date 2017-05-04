package com.ws.bix4j.access.user;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.access.ZResponse;
import com.ws.bix4j.access.ZResult;
import com.ws.bix4j.bean.UserDO;

/**
 * @Date 2017/4/5
 * @Author chen
 * 登录模式开启UserDate，返回的result包括了UserDO 以及 sessionid 和 userip
 */
public class UserLoginResponse extends ZResponse<UserLoginResponse.Result> {
    private Result result;

    public Result getResult() {
        return result;
    }

    public UserLoginResponse setResult(Result result) {
        this.result = result;
        return this;
    }

    public class Result extends UserDO implements ZResult{
        @JSONField(name = "sessionid")
        private String sessionId;
        @JSONField(name = "userip")
        private String userIp;

        public String getSessionId() {
            return sessionId;
        }

        public Result setSessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public String getUserIp() {
            return userIp;
        }

        public Result setUserIp(String userIp) {
            this.userIp = userIp;
            return this;
        }
    }
}
