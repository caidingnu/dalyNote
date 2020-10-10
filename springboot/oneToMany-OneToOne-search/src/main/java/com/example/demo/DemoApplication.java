package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.example.demo.dao")
public class DemoApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);

    }

}
