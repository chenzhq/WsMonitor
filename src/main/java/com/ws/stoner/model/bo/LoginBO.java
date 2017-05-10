package com.ws.stoner.model.bo;

/**
 * Created by chenzheqi on 2017/5/10.
 */
public class LoginBO {
    private boolean isLoginSuccess;
    private String sessionId;

    public LoginBO(boolean isLoginSuccess) {
        this.isLoginSuccess = isLoginSuccess;
    }

    public boolean isLoginSuccess() {
        return isLoginSuccess;
    }

    public LoginBO setLoginSuccess(boolean loginSuccess) {
        isLoginSuccess = loginSuccess;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public LoginBO setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }
}
