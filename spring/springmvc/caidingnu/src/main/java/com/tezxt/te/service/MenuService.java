package com.tezxt.te.service;

import com.tezxt.te.dao.MenuMapper;
import com.tezxt.te.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by CodeX4J.
 */
@Service
public class MenuService {
    @Autowired
    private MenuMapper menuMapper;

    public int add(Menu menu) {
        return menuMapper.insert(menu);
    }

    public Menu find(String id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    public int update(Menu menu) {
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

    public int delete(String id) {
        return menuMapper.deleteByPrimaryKey(id);
    }
}