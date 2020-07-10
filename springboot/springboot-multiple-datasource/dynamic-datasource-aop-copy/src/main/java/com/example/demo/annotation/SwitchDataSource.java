package com.example.demo.annotation;

import java.lang.annotation.*;

/**
 * 用于指明使用哪个数据源
 */
@Retention(RetentionPolicy.RUNTIME)//运行时保留
@Documented//生成到文档中
@Target(ElementType.METHOD)//作用范围是方法
public @interface SwitchDataSource {
    /**
     * value　使用的数据源的名称,默认为master
     * @return
     */
    String value() default "master";
}