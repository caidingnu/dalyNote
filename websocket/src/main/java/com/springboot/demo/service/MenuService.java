package com.springboot.demo.service;

import com.springboot.demo.entity.Menu;

import java.util.HashMap;
import com.github.pagehelper.PageInfo;
import java.util.Map;

/**
 * desc:
 * author caidingnu
 * create 2019-10-16
 * version 1.0.0
 */
public interface MenuService {

    int insert(Map<String, Object> map);

    int deleteByCondition(Map<String, Object> map);

    int update(Menu obj);

    PageInfo<Menu> find(Map<String, Object> map);

    Menu findSingle(Map<String, Object> map);

    int deleteLogic(Map<String, Object> map);

    int count();
}
