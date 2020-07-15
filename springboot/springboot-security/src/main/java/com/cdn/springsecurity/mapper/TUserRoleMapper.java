package com.cdn.springsecurity.mapper;

import com.cdn.springsecurity.entity.TUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author cdn
 * @since 2020-05-17
 */
public interface TUserRoleMapper extends BaseMapper<TUserRole> {

    //
    @Select("SELECT DISTINCT t_role.role_name FROM t_role left join t_user_role on t_role.id=t_user_role.role_id  where t_user_role.user_id=#{userId}")
    List<String> selectRoleByUserId(Integer userId);

}
