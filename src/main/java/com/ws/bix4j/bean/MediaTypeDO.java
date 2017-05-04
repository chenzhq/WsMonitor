package com.ws.bix4j.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Date 2017/4/5
 * @Author chen
 */
public class MediaTypeDO {
    @JSONField(name = "mediatypeid")
    private String mediaTypeId;
    private String description;
    private int type;
    @JSONField(name = "exec_path")
    private String execPath;
    @JSONField(name = "gsm_modem")
    private String gsmModem;
    private String passwd;
    @JSONField(name = "smtp_email")
    private String smtpEmail;
    @JSONField(name = "smtp_helo")
    private String smtpHelo;
    @JSONField(name = "smtp_server")
    private String smtpServer;
    private int status;
    private String username;
    @JSONField(name = "exec_params")
    private String execParams;

    public String getMediaTypeId() {
        return mediaTypeId;
    }

    public void setMediaTypeId(String mediaTypeId) {
        this.mediaTypeId = mediaTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getExecPath() {
        return execPath;
    }

    public void setExecPath(String execPath) {
        this.execPath = execPath;
    }

    public String getGsmModem() {
        return gsmModem;
    }

    public void setGsmModem(String gsmModem) {
        this.gsmModem = gsmModem;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getSmtpEmail() {
        return smtpEmail;
    }

    public void setSmtpEmail(String smtpEmail) {
        this.smtpEmail = smtpEmail;
    }

    public String getSmtpHelo() {
        return smtpHelo;
    }

    public void setSmtpHelo(String smtpHelo) {
        this.smtpHelo = smtpHelo;
    }

    public String getSmtpServer() {
        return smtpServer;
    }

    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getExecParams() {
        return execParams;
    }

    public void setExecParams(String execParams) {
        this.execParams = execParams;
    }
}
