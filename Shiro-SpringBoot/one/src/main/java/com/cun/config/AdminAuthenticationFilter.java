package com.cun.config;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
 * 解决一个账户在一个地方登陆之后，再次登陆无法打开登陆页面问题
 */
@Configuration
public class AdminAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if(isLoginRequest(request, response) && isLoginSubmission(request, response)){
            return false;
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }
}