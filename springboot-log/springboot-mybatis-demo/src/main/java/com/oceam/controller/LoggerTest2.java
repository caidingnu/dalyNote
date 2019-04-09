package com.oceam.controller;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author gaozhao
 * @创建时间 2018/8/6
 * @描述
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest2 {

    @Test
    public void contextLoads() {
        String name = "imooc";
        String password = "123456";
        log.debug("debug...");
        log.info("name:" + name + ",password:" + password);
        log.info("name:{},password:{}", name, password);
        log.error("error...");
    }


    @Test
    public void main() {
        try {
            int a = 1;
            int b = 0;
            System.out.println(1 / 0);
            log.info("------------------------------");
        } catch (Exception e) {
            log.error("_-------------错误-----------------");
            log.error(String.valueOf(e));
        }
    }


}


