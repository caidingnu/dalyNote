package com.springboot.demo.mapper;


import com.springboot.demo.entity.Menu;
import org.apache.ibatis.annotations.*;
import com.springboot.demo.mapper.provider.MenuProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * desc：
 * author CDN
 * create 2019-10-16
 * version 1.0.0
 */
public interface MenuMapper {

    @InsertProvider(type = MenuProvider.class, method = "insert")
    int insert(Map<String, Object> map);

    @DeleteProvider(type = MenuProvider.class, method = "deleteByCondition")
    int deleteByCondition(Map<String, Object> map);

    @UpdateProvider(type = MenuProvider.class, method = "update")
    int update(Menu obj);

    @SelectProvider(type = MenuProvider.class, method = "find")
    List<Menu> find(Map<String, Object> map);

    @SelectProvider(type = MenuProvider.class, method = "findSingle")
    Menu findSingle(Map<String, Object> map);

    @UpdateProvider(type = MenuProvider.class, method = "logicDelete")
    int logicDelete(Map<String, Object> map);

    @Select("select count(*) from menu")
    int count();
}
