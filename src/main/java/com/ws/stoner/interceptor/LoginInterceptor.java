package com.ws.stoner.interceptor;

import com.ws.stoner.constant.CookieConsts;
import com.ws.stoner.utils.CookieUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.ws.stoner.constant.CookieConsts.ZBX_SESSION;

/**
 * Created by chenzheqi on 2017/5/5.
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();

        if(uri.endsWith("/login/") || uri.endsWith("/login")) {
            if(request.getSession() != null && request.getSession().getAttribute(ZBX_SESSION) != null) {
                response.sendRedirect(request.getContextPath() + "/dashboard");
                return false;
            } else {
                return true;
            }
        } else if (uri.endsWith("/logout/") || uri.endsWith("/logout")) {
            if (request.getSession() != null && request.getSession().getAttribute(ZBX_SESSION) == null) {
                //已经自动登出，old session已经销毁
                CookieUtils.remove(response, CookieConsts.ZBX_SESSION);
                CookieUtils.remove(response, CookieConsts.USER_ID);
                response.sendRedirect(request.getContextPath() + "/login");
                return false;
            } else {
                return true;
            }
        }

        if(request.getSession() != null && request.getSession().getAttribute(ZBX_SESSION) != null) {
           return true;
        }

        response.sendRedirect(request.getContextPath() + "/login");
        return false;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
