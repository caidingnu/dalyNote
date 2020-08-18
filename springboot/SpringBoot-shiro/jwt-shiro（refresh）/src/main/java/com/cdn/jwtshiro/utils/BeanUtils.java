package com.cdn.jwtshiro.utils;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletRequest;

/**
 * @author CDN
 * @desc
 * @date 2020/07/30 17:27
 */
public class BeanUtils {

    /**
     * 获取Bean 对象
     * @param clazz
     * @param request
     * @param <T>
     * @return
     */
    public static  <T> T getBean(Class<T> clazz, ServletRequest request){
        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return applicationContext.getBean(clazz);
    }

}
