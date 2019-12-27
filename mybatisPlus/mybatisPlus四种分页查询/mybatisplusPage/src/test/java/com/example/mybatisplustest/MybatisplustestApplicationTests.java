package com.example.mybatisplustest;

import com.example.mybatisplustest.entity.UniData;
import com.example.mybatisplustest.mapper.UniDataMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisplustestApplicationTests {

    @Autowired
    UniDataMapper uniDataMapper;

    @Test
    void contextLoads() {
    }

}
