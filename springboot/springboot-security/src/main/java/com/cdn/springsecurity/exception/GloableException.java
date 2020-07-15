package com.cdn.springsecurity.exception;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * CDN
 * 2020/05/19 00:03
 */
@RestControllerAdvice
public class GloableException {


    /**
     * desc:  暂时没用
     * param:
     * author: CDN
     * date: 2020/5/19
     */
//    @ExceptionHandler(Exception.class)
//    public Object allException(Exception e) {
//
//        if (e instanceof InternalAuthenticationServiceException) {
//            System.out.println(e.getMessage());
//        } else {
//
//        }
//
//
//        return null;
//    }

}
