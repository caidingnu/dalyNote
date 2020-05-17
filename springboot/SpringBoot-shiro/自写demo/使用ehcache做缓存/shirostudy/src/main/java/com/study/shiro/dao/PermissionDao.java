package com.study.shiro.dao;

import com.study.shiro.entity.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao  {

    @Select(value = "select p.* from t_user u,t_user_role ur,t_role_permission rp,t_permission p where u.id=ur.user_id and ur.role_id=rp.role_id and rp.permission_id=p.id and u.user_name=#{userName}")
    List<Permission> getPermissionsByUserName(String userName);

}
