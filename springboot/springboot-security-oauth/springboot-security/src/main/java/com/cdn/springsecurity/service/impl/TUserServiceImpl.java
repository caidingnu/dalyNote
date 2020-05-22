package com.cdn.springsecurity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdn.springsecurity.entity.TUser;
import com.cdn.springsecurity.mapper.TUserMapper;
import com.cdn.springsecurity.service.ITUserService;
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
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {

}
