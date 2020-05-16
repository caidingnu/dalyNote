package com.cdn.quartzspringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cdn.quartzspringboot.mapper")
public class QuartzSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzSpringbootApplication.class, args);
    }

}
