package com.ocr.mytest.aop;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * desc:
 * author caidingnu
 * create 2019/10/20
 * version 1.0.0
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})//作用于参数或方法上
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(A.class)
public @interface CdnLog {

    /**
     * 日志名称
     *
     * @return
     */
    String description() default "";

    /**
     * 日志类型
     *
     * @return
     */
    String type() default "1";
}
