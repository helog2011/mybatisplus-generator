package com.helog.generator.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author helog
 * @version 1.0
 * @Description:
 * @since 2018/09/17
 */
public class CommonInterceptor implements HandlerInterceptor {
    public CommonInterceptor() {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        String appContext = request.getContextPath();
        String base = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + appContext;
        request.getSession().setAttribute("base", base);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
