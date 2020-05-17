package com.cdn.springsecurity.service.impl;

import com.cdn.springsecurity.entity.TUserRole;
import com.cdn.springsecurity.mapper.TUserRoleMapper;
import com.cdn.springsecurity.service.ITUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cdn
 * @since 2020-05-17
 */
@Service
public class TUserRoleServiceImpl extends ServiceImpl<TUserRoleMapper, TUserRole> implements ITUserRoleService {

}
