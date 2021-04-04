package com.cdn.springsecurity.service;

import com.cdn.springsecurity.entity.TUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cdn
 * @since 2020-05-17
 */
public interface ITUserService extends IService<TUser>, UserDetailsService {
    public TUser getCurrUser();
}
