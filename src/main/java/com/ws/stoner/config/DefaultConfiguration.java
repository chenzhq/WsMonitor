package com.ws.stoner.config;

import com.ws.stoner.listener.SessionListener;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * Created by chenzheqi on 2017/5/5.
 */
@Configurable
public class DefaultConfiguration {

    @Bean
    public SessionListener sessionListener() {
        return new SessionListener();
    }
}
