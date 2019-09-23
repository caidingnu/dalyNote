package com.cun.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * desc：
 * author CDN
 * create 2019-09-23 20:01
 * version 1.0.0
 */
@RestControllerAdvice
public class NoAuthorityException {


    @ExceptionHandler(value = AuthorizationException.class)//处理访问方法时权限不足问题
    public  Map<String, Object> defaultErrorHandler(HttpServletRequest req, Exception e) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", false);
        map.put("msg", "无权限！");

        return map;
    }

}
