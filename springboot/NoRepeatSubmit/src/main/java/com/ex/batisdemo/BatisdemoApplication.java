package com.ex.batisdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ex.batisdemo.dao")
public class BatisdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatisdemoApplication.class, args);
    }

}
