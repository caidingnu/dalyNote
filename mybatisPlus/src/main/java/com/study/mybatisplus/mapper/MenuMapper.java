package com.study.mybatisplus.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.study.mybatisplus.entity.Menu;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2019-10-31
 */
public interface MenuMapper extends BaseMapper<Menu> {


    @Select("select * from menu inner join menu2 on menu.id=menu2.menu_id")
    List<Map<String,Object>>  inner();

}
