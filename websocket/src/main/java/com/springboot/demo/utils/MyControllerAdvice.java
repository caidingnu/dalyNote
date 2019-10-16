package com.springboot.demo.utils;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;

/**
 * desc：全局异常处理
 * author CDN
 */
@ControllerAdvice
public class MyControllerAdvice {

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        Map<String ,Object> map = new HashMap();
        if (ex instanceof RuntimeException) {
            map.put("code", 98);
        }else {
            map.put("code", 500);
        }
        map.put("msg", ex.getMessage());
        return map;
    }



}