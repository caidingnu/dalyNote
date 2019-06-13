package com.oceam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class SpringbootMybatisDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisDemoApplication.class, args);
        System.out.println("启动成功");
    }

}
