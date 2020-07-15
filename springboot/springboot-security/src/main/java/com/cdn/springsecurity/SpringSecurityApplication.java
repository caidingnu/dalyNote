package com.cdn.springsecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cdn.springsecurity.mapper")
public class SpringSecurityApplication  {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }


}
