package com.example.mybatisplustest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * 异常处理  -1  业务异常
 */
@ControllerAdvice
public class MyExceptionHandler  {


    private Logger dataLogger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public HashMap Handler(Exception e, HttpServletRequest request) {
        HashMap resMap;
        if (e instanceof MyException ) {
            MyException xdException = (MyException) e;
            resMap = new HashMap();
            resMap.put("code", xdException.getCode());
            resMap.put("msg", e.getMessage());
            return resMap;
        } else if (e instanceof DataAccessException) {
            dataLogger.error(e.getMessage(), e);
            resMap = new HashMap();
            resMap.put("code", "98");
            //resMap.put("msg","数据库错误，请联系管理员。"); //正式环境
            resMap.put("msg", e.getMessage());    //调试环境
            return resMap;
        } else if (e instanceof MaxUploadSizeExceededException) {
            dataLogger.error(e.getMessage(), "文件过大！");
            resMap = new HashMap();
            resMap.put("code", "98");
            resMap.put("msg", "文件超过20MB！");
            return resMap;
        } else if (e instanceof FileNotFoundException) {
            dataLogger.error(e.getMessage(), "文件不存在！");
            resMap = new HashMap();
            resMap.put("code", "98");
            resMap.put("msg", "文件不存在！");
            return resMap;
        } else {
            dataLogger.error(e.getMessage(), e);
            resMap = new HashMap();
            resMap.put("code", "99");
            //resMap.put("msg","系统错误，请联系管理员。");   //正式环境
            resMap.put("msg", e);   //调试环境
            return resMap;
        }
    }


}
