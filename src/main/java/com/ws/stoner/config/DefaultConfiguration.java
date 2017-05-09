package com.ws.stoner.config;

import com.ws.stoner.listener.SessionListener;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chenzheqi on 2017/5/5.
 */
@Configuration
public class DefaultConfiguration {

    @Bean
    public SessionListener sessionListener() {
        return new SessionListener();
    }

    @Bean
    @ApplicationScope
    public Map<String ,String> sessionMap() {
        return new ConcurrentHashMap<String, String>();
    }
}
