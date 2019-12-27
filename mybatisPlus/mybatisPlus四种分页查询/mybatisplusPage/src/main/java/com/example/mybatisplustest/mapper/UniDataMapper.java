package com.example.mybatisplustest.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplustest.entity.UniData;
import com.example.mybatisplustest.mapper.provider.UniDataProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.Map;

/**
 * desc:
 * author caidingnu
 * create 2019/12/27
 * version 1.0.0
 */
public interface UniDataMapper {

    /**
     * 注解方式分页
     *
     * @param page
     * @param map
     * @return
     */
    @Select("SELECT user.name,user.password,USER.nick_name as nickName,USER.age,role.role_id as roleId,role.role " +
            "FROM user INNER JOIN role ON `user`.user_id = role.role_id where user.user_id = #{map.userId}")
    IPage<UniData> annotationsPage(IPage<UniData> page, @Param("map") Map map);

    /**
     * provider 方式分页
     * ☆☆☆☆☆☆☆ 重点： page必须放在第一个参数 ☆☆☆☆☆☆☆☆
     * @param page
     * @param map
     * @return
     */
    @SelectProvider(type = UniDataProvider.class, method = "providerPage")
    IPage<UniData> providerPage(IPage<UniData> page, @Param("map") Map map);

    /**
     *  xml 方式分页
     * @param page
     * @param map
     * @return
     */
    IPage<UniData> xmlPage(IPage<UniData> page, @Param("map") Map map);
}
