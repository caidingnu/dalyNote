package com.example.mybatisplustest.service.impl;

import com.example.mybatisplustest.entity.Role;
import com.example.mybatisplustest.mapper.RoleMapper;
import com.example.mybatisplustest.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2019-12-27
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
