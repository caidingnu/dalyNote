package com.cdn.springsecurity.service.impl;

import com.cdn.springsecurity.entity.TPermission;
import com.cdn.springsecurity.mapper.TPermissionMapper;
import com.cdn.springsecurity.service.ITPermissionService;
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
public class TPermissionServiceImpl extends ServiceImpl<TPermissionMapper, TPermission> implements ITPermissionService {

}
