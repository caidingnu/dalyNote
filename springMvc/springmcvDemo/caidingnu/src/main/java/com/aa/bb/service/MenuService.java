package com.aa.bb.service;

import com.aa.bb.dao.MenuMapper;
import com.aa.bb.model.Menu;
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

    public Menu find(int id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    public int update(Menu menu) {
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

    public int delete(int id) {
        return menuMapper.deleteByPrimaryKey(id);
    }
}