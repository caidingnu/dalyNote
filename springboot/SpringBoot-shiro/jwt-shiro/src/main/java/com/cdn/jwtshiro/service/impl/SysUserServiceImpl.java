package com.cdn.jwtshiro.service.impl;

import com.cdn.jwtshiro.entity.SysUser;
import com.cdn.jwtshiro.mapper.SysUserMapper;
import com.cdn.jwtshiro.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author author
 * @since 2020-07-30
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
