package com.ocr.mytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MytestApplication {
    public static void main(String[] args) {
        SpringApplication.run(MytestApplication.class, args);
    }

}
