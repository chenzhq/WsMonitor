package com.ws.bix4j;

import com.ws.bix4j.access.application.Application;
import com.ws.bix4j.access.host.Host;
import com.ws.bix4j.access.hostgroup.HostGroup;
import com.ws.bix4j.access.item.Item;
import com.ws.bix4j.access.problem.Problem;
import com.ws.bix4j.access.trigger.Trigger;
import com.ws.bix4j.access.user.*;
import com.ws.bix4j.exception.ZApiException;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by chen on 2017/4/1.
 */
public class ZApi {

    private URI uri;

    private volatile String auth;

    public ZApi() {
    }

    public ZApi(URI uri) {
        this.uri = uri;
    }

    public ZApi(String url) {
        try {
            uri = new URI(url.trim());
        } catch (URISyntaxException e) {
            throw new RuntimeException("url 不合法", e);
        }
    }

    public UserLoginResponse login(String username, String password) throws ZApiException {
        User user = new User(this.uri.toString());

        UserLoginRequest request = new UserLoginRequest();
        request.getParams().setUser(username).setPassword(password).setUserData(true);

        UserLoginResponse response = user.login(request);
        this.auth = response.getResult().getSessionId();
        return response;
    }

    public UserLogoutResponse logout() throws ZApiException {
        User user = new User(this.uri.toString());

        UserLogoutRequest request = new UserLogoutRequest();
        request.setAuth(auth);
        UserLogoutResponse response = user.logout(request);
        return response;

    }

    public void cacheLogin(String zbxSessionId) {
       this.auth = zbxSessionId;
    }

    public void cacheLogout(String zbsSessionId) throws ZApiException {
        this.auth = zbsSessionId;
        logout();
    }

    public User User() {
        return new User(uri.toString(), auth);
    }
    public Host Host() {
        return new Host(uri.toString(), auth);
    }

    public HostGroup Group() {
        return new HostGroup(uri.toString(), auth);
    }
    public Application Application() {
        return new Application(uri.toString(), auth);
    }
    public Trigger Trigger() {
        return new Trigger(uri.toString(), auth);
    }
    public Item Item() {
        return new Item(uri.toString(), auth);
    }
    public Problem Problems() {
        return new Problem(uri.toString(), auth);
    }
}
