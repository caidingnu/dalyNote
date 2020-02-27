package com.stu.aoppermission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.sql.DataSource;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class AoppermissionApplication {

    public static void main(String[] args) {
        SpringApplication.run(AoppermissionApplication.class, args);
        System.out.println("———————————————启动成功———————————————");
    }

}
