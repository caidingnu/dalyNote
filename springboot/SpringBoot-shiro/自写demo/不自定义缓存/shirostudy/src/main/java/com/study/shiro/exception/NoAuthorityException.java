package com.study.shiro.exception;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
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


    @ExceptionHandler(value = Exception.class)//处理访问方法时权限不足问题
    public Map<String, Object> defaultErrorHandler(HttpServletRequest req, Exception e) {
        Map<String, Object> resMap = new HashMap<>();
        if (e instanceof MyException) {
            MyException xdException = (MyException) e;
            resMap.put("code", xdException.getCode());
            resMap.put("msg", e.getMessage());
            return resMap;
        } else if (e instanceof DataAccessException) {
            resMap.put("code", "98");
            //resMap.put("msg","数据库错误，请联系管理员。"); //正式环境
            resMap.put("msg", e.getMessage());    //调试环境
            return resMap;
        } else if (e instanceof MaxUploadSizeExceededException) {
            resMap.put("code", "98");
            resMap.put("msg", "文件超过20MB！");
            return resMap;
        } else if (e instanceof FileNotFoundException) {
            resMap.put("code", "98");
            resMap.put("msg", "文件不存在！");
            return resMap;
        } else if (e instanceof AuthorizationException) {
            resMap.put("success", false);
            resMap.put("msg", "无权限！");
//            此处可以返回也可以跳转到无权限页面
            return resMap;
        }else {
            resMap.put("code", "99");
            resMap.put("msg", e);
            return resMap;
        }
    }

}
