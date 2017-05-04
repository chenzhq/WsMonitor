package com.ws.bix4j.access.user;

import com.ws.bix4j.access.ZRequest;

/**
 * @Date 2017/4/5
 * @Author chen
 */
public class UserLoginRequest extends ZRequest {

    private Params params = new Params();

    public UserLoginRequest() {
        this.setMethod("user.login");
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }


    public class Params {
        private String password;
        private String user;
        private boolean userData;

        public String getPassword() {
            return password;
        }

        public Params setPassword(String password) {
            this.password = password;
            return this;
        }

        public String getUser() {
            return user;
        }

        public Params setUser(String user) {
            this.user = user;
            return this;
        }

        public boolean getUserData() {
            return userData;
        }

        public Params setUserData(boolean userData) {
            this.userData = userData;
            return this;
        }
    }

}
