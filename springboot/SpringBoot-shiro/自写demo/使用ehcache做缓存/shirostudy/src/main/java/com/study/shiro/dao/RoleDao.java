package com.study.shiro.dao;


import com.study.shiro.entity.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleDao {

    @Select(value = "select r.* from t_user u,t_user_role ur,t_role r where u.id=ur.user_id and ur.role_id=r.id and u.user_name=#{userName}")
    List<Role> getRolesByUserName(String userName);

}
