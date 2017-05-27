package com.ws.stoner.model.dto;

/**
 * Created by chenzheqi on 2017/5/10.
 */
public class LoginDTO {
    private boolean isLoginSuccess;
    private String sessionId;
    private UserInfoDTO userInfoDTO;

    public LoginDTO(boolean isLoginSuccess) {
        this.isLoginSuccess = isLoginSuccess;
    }

    public boolean isLoginSuccess() {
        return isLoginSuccess;
    }

    public LoginDTO setLoginSuccess(boolean loginSuccess) {
        isLoginSuccess = loginSuccess;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public LoginDTO setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public UserInfoDTO getUserInfoDTO() {
        return userInfoDTO;
    }

    public LoginDTO setUserInfoDTO(UserInfoDTO userInfoDTO) {
        this.userInfoDTO = userInfoDTO;
        return this;
    }
}
