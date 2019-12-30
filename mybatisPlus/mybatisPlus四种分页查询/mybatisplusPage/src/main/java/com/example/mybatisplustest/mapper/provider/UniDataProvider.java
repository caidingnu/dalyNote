package com.example.mybatisplustest.mapper.provider;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.mybatisplustest.entity.UniData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * desc：
 * author CDN
 * create 2019-12-27 22:20
 * version 1.0.0
 */
public class UniDataProvider {



    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2019/12/27
     */
    public String providerPage(IPage<UniData> page,Map map) {
        SQL sql = new SQL();
        sql.SELECT("user.name,user.password,user.nick_name as nickName,user.age,role.role_id as roleId,role.role " +
                "FROM user INNER JOIN role ON `user`.user_id = role.role_id ");
        if (map.get("userId")!=null && map.get("userId") !=""){
            sql.WHERE("user.user_id = #{map.userId}");
        }
        return sql.toString();
    }
}
