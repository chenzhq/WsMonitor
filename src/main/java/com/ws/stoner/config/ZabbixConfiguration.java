package com.ws.stoner.config;

import com.ws.bix4j.ZApi;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${zabbix.ip}")
    private String URL;

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
