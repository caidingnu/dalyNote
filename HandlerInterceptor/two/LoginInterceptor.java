package com.rx.erp.interceptor;

import com.rx.erp.exception.MyException;
import com.rx.erp.service.common.impl.TaskManageImpl;
import com.rx.erp.utils.StrUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * desc：
 * author CDN
 * create 2019-12-19 09:08
 * version 1.0.0
 */
public class LoginInterceptor implements HandlerInterceptor {
    private Logger dataLogger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TaskManageImpl taskManageImpl;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (taskManageImpl.judgeEnvironment()) {
            return true;
        }
        HttpSession session = request.getSession();
        Object user = session.getAttribute("userInfo");
        if (StrUtils.isNullOrEmpty(user)) {
            dataLogger.error("未登录");
            throw new MyException("未登录");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
