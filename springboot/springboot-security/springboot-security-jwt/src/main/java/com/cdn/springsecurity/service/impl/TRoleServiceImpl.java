package com.cdn.springsecurity.service.impl;

import com.cdn.springsecurity.entity.TRole;
import com.cdn.springsecurity.mapper.TRoleMapper;
import com.cdn.springsecurity.service.ITRoleService;
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
public class TRoleServiceImpl extends ServiceImpl<TRoleMapper, TRole> implements ITRoleService {

}
