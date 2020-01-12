package com.example.mybatisplustest.service.impl;

import com.example.mybatisplustest.entity.User;
import com.example.mybatisplustest.mapper.UserMapper;
import com.example.mybatisplustest.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2019-12-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
