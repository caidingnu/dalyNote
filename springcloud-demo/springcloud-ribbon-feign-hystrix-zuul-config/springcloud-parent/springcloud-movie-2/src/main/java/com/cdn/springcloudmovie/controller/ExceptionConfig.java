package com.cdn.springcloudmovie.controller;

import com.thoughtworks.xstream.core.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public String baseErrorHandler(HttpServletRequest request, Exception e) {
        if (e instanceof NullPointerException){
            return "redirect:http://www.taobao.com";
        }
        return "redirect:http://www.baidu.com";
    }

}