package com.demo.async.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.async.entity.User;

import java.util.List;
import java.util.concurrent.Future;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cdn
 * @since 2020-06-08
 */
public interface IUserService extends IService<User> {

    Future<List<User>> muList();

}
