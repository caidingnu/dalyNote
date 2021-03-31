package com.stu.aoppermission.serviceImpl;

import com.stu.aoppermission.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * desc：
 * create 2020-02-27 16:39
 * version 1.0.0
 *
 * @author cdn
 */
@Service
public class UserServiceImpl implements UserService {

//    管理员名单或者权限集合，此处为了方便，常规应该写在数据库
    private List<String> admins = Arrays.asList("zs", "ls", "ww");

    @Override
    public boolean isAdmin(String currentUser) {
        return admins.contains(currentUser);
    }
}
