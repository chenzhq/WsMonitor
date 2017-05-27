package com.ws.stoner.config;

import com.ws.bix4j.ZApi;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by chenzheqi on 2017/4/26.
 */
@Configuration
public class ZabbixConfiguration {

    private final static String URL = "http://145.170.23.28/zabbix/api_jsonrpc.php";

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION,
            proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ZApi zApi() {
        return new ZApi(URL);
    }

    @Bean
    public ZApi logoutZApi() {
        return new ZApi(URL);
    }
}
