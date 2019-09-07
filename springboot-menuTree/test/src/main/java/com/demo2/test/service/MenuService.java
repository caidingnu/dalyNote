package com.demo2.test.service;

import com.demo2.test.entity.Menu;
import com.demo2.test.entity.MenuExample;

import java.util.List;

/**
 * desc:
 * author caidingnu
 * create 2019/9/7
 * version 1.0.0
 */
public interface MenuService {

    List<Menu> selectByExample(MenuExample example);

    Menu selectByPrimaryKey(Integer id);

    List<Menu> selectByPid(Integer pid);
}
