package com.ws.stoner.listener;

import com.ws.bix4j.ZApi;
import com.ws.bix4j.exception.ZApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;

import static com.ws.stoner.constant.CookieConsts.REMEMBER_ME;

/**
 * Created by chenzheqi on 2017/5/5.
 */
@Component
public class SessionListener implements HttpSessionListener{
    private static final Logger logger = LoggerFactory.getLogger(SessionListener.class);
    @Autowired
    private ZApi logoutZApi;
    @Autowired
    private Map<String, String> sessionMap;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        logger.debug("session created {}", httpSessionEvent.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        try {
            if(sessionMap.get(httpSessionEvent.getSession().getId()) != null
                    && httpSessionEvent.getSession().getAttribute(REMEMBER_ME) == null) {
                String destroyedSessionId = httpSessionEvent.getSession().getId();
                logoutZApi.cacheLogout(sessionMap.get(destroyedSessionId));
                sessionMap.remove(destroyedSessionId);
            }
        } catch (ZApiException e) {
            e.printStackTrace();
        }
        logger.debug("session destroyed {}", httpSessionEvent.getSession().getId());
    }
}
