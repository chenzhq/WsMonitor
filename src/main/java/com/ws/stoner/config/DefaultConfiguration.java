package com.ws.stoner.config;

import com.ws.stoner.listener.SessionListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

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
        return new ConcurrentHashMap<>();
    }


    @Bean
    public Java8TimeDialect conditionalCommentDialect() {
        return new Java8TimeDialect();
    }
}
