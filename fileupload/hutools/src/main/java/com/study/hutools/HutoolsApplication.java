package com.study.hutools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class HutoolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HutoolsApplication.class, args);
        System.out.println("#################Hutools###############");
    }

}
