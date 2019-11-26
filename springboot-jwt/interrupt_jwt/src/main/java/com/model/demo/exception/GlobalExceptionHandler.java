package com.model.demo.exception;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.io.FileNotFoundException;
import java.util.HashMap;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public HashMap handleException(Exception e) {
        HashMap resMap;
        if (e instanceof MyException) {
            MyException xdException = (MyException) e;
            resMap = new HashMap();
            resMap.put("code", xdException.getCode());
            resMap.put("msg", e.getMessage());
            return resMap;
        } else if (e instanceof MaxUploadSizeExceededException) {
            resMap = new HashMap();
            resMap.put("code", "98");
            resMap.put("msg", "文件超过20MB！");
            return resMap;
        } else if (e instanceof FileNotFoundException) {
            resMap = new HashMap();
            resMap.put("code", "98");
            resMap.put("msg", "文件不存在！");
            return resMap;
        } else {
            resMap = new HashMap();
            resMap.put("code", "99");
            resMap.put("msg", e);   //调试环境
            return resMap;
        }
    }
}