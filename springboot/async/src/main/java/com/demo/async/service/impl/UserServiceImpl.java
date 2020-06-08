package com.demo.async.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.async.entity.User;
import com.demo.async.mapper.UserMapper;
import com.demo.async.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cdn
 * @since 2020-06-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    @Async
    public Future<List<User>> muList() {
        List<User> users = userMapper.selectList(null);
        return new AsyncResult<>(users);
    }
}
