package com.cdn.common;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
@Slf4j
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.cdn.common.mapper")
public class CommonApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(CommonApplication.class, args);
        if (run.isActive()) {
            log.info("\n======================启动成功=================");
        }else {
            System.out.println("+++++ 失败 +++++");
        }
    }

}
