package com.spring.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * desc：
 * author CDN
 * create 2019-06-09 23:34
 * version 1.0.0
 */
@Data
@AllArgsConstructor
public class Menu {
    private String uuid;
    private String menun_name;
    private String id;
    private String pid;
    private String create_time;
    private String update_time;
}
