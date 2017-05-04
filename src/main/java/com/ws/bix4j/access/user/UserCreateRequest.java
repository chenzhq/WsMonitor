package com.ws.bix4j.access.user;

import com.alibaba.fastjson.annotation.JSONField;
import com.ws.bix4j.access.ZRequest;
import com.ws.bix4j.bean.MediaDO;
import com.ws.bix4j.bean.UserDO;
import com.ws.bix4j.bean.UserGroupDO;

import java.util.List;

/**
 * @Date 2017/4/6
 * @Author chen
 */
public class UserCreateRequest extends ZRequest {
    public UserCreateRequest() {
        this.setMethod("user.create");
    }

    private Params params = new Params();

    public Params getParams() {
        return params;
    }

    public void setParams(UserDO params) {
        this.params = (Params)params;
    }

    public UserCreateRequest setPassword(String passwd) {
        this.params.setPasswd(passwd);
        return this;
    }

    public UserCreateRequest setUserGrps(List<UserGroupDO> userGrps) {
        this.params.setUsrGrps(userGrps);
        return this;
    }

    public UserCreateRequest setUserMedias(List<MediaDO> userMedias) {
        this.params.setUserMedias(userMedias);
        return this;
    }


    public static class Params extends UserDO {

        private String passwd;
        @JSONField(name = "usrgrps")
        private List<UserGroupDO> usrGrps;
        @JSONField(name = "user_medias")
        private List<MediaDO> userMedias;

        public String getPasswd() {
            return passwd;
        }

        public void setPasswd(String passwd) {
            this.passwd = passwd;
        }

        public List<UserGroupDO> getUsrGrps() {
            return usrGrps;
        }

        public void setUsrGrps(List<UserGroupDO> usrGrps) {
            this.usrGrps = usrGrps;
        }

        public List<MediaDO> getUserMedias() {
            return userMedias;
        }

        public void setUserMedias(List<MediaDO> userMedias) {
            this.userMedias = userMedias;
        }
    }
}
