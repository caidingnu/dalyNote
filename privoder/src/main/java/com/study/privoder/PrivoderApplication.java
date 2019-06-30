package com.study.privoder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.study.privoder.mapper")
@EnableTransactionManagement
public class PrivoderApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrivoderApplication.class, args);
        System.out.println("启动成功");
    }

}
