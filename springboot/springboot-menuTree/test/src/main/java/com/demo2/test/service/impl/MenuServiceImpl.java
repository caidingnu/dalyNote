package com.demo2.test.service.impl;

import com.demo2.test.entity.Menu;
import com.demo2.test.entity.MenuExample;
import com.demo2.test.mapper.MenuMapper;
import com.demo2.test.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * desc：
 * author CDN
 * create 2019-09-07 20:26
 * version 1.0.0
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> selectByExample(MenuExample example) {
        return menuMapper.selectByExample(example);
    }

    @Override
    public Menu selectByPrimaryKey(Integer id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Menu> selectByPid(Integer pid) {
        return menuMapper.selectByPid(pid);
    }
}
