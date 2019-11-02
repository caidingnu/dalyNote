package com.study.mybatisplus.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.study.mybatisplus.entity.Menu;
import com.study.mybatisplus.mapper.MenuMapper;
import com.study.mybatisplus.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2019-10-31
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Map<String, Object>> inner() {
        return menuMapper.inner();
    }
}
