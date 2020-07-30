package com.cdn.jwtshiro;

import com.cdn.jwtshiro.jwt.JwtFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.cdn.jwtshiro.mapper")
public class JwtShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtShiroApplication.class, args);
    }

}
