package com.study.config.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * desc：
 * author CDN
 * create 2019-11-03 17:45
 * version 1.0.0
 */
@Component
@PropertySource(value = "classpath:student.properties",encoding = "gbk")
@ConfigurationProperties(prefix = "student")
public class Student {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
