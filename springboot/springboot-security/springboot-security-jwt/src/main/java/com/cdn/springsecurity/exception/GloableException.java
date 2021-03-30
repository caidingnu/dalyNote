package com.cdn.springsecurity.exception;

import com.cdn.springsecurity.entity.common.Result;
import com.cdn.springsecurity.service.impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * CDN
 * 2020/05/19 00:03
 */
@RestControllerAdvice
public class GloableException {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @ExceptionHandler(Exception.class)
    public Object allException(Exception e) {
        if (e instanceof UsernameNotFoundException) {
            System.out.println(e.getMessage());
            return Result.validateFailed(e.getMessage());
        }
        return null;
    }

}
