package com.stu.aoppermission.anno;

import java.lang.annotation.*;

/**
 * desc： 自定义注解
 * create 2020-02-27 16:27
 * version 1.0.0
 *
 * @author cdn
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {
    String authorities() default "默认值";
}
