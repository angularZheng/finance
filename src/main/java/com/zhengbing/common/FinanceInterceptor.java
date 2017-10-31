package com.zhengbing.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhengbing on 2017-10-31.
 */
public class FinanceInterceptor implements HandlerInterceptor{

    Logger logger = LoggerFactory.getLogger( FinanceInterceptor.class);
    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object o ) throws Exception {

        return false;
    }

    @Override
    public void postHandle( HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView ) throws Exception {

    }

    @Override
    public void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object o, Exception e ) throws Exception {

    }
}
