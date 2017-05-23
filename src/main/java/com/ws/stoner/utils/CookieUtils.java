package com.ws.stoner.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by chenzheqi on 2017/5/23.
 */
public class CookieUtils {
    public static void add(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name,value);
        if(maxAge > 0) {
            cookie.setMaxAge(maxAge);
        }
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static void add(HttpServletResponse response, String name, String value) {
        add(response, name, value, -1);
    }

    public static void remove(HttpServletResponse response, String name) {
        Cookie delCookie = new Cookie(name, null);
        delCookie.setMaxAge(0);
        delCookie.setPath("/");
        response.addCookie(delCookie);
    }

    public static String getValue(HttpServletRequest request, String name) {
        Cookie cookie = getCookie(request, name);
        return null != cookie ? cookie.getValue() : null;
    }

    public static Cookie getCookie(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = readCookieMap(request);
        if (null != cookieMap && cookieMap.containsKey(name)) {
            return cookieMap.get(name);
        }
        return null;
    }

    private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
