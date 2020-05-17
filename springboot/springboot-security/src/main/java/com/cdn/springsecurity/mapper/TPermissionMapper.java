package com.cdn.springsecurity.mapper;

import com.cdn.springsecurity.entity.TPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cdn
 * @since 2020-05-17
 */
public interface TPermissionMapper extends BaseMapper<TPermission> {

    @Select("select DISTINCT t_permission.permission_name from t_permission left join t_role_permission" +
            " on t_permission.id=t_role_permission.permission_id" +
            " left join t_role on t_role_permission.role_id = t_role.id" +
            " left join t_user_role on t_user_role.role_id =t_role.id" +
            " where t_user_role.user_id=#{userId}")
    Set<String> getAuthorityMarkByUserId(Integer userId);

}
