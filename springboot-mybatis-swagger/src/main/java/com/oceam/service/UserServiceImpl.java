package com.oceam.service;

/**
 * Author: caidingnu
 * Date:   2018/10/28
 * Time：  1:03
 * Content:
 */

import com.oceam.entity.UserInfo;
import com.oceam.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements IUserService {

//    @Autowired  此处会报错，改为@Resource注入
    @Resource
    private UserInfoMapper userDao;

    @Override
    public List findAllUser() {
        return userDao.findAllUser();
    }

    @Override
    public UserInfo selectByPrimaryKey(Integer userid) {
        return userDao.selectByPrimaryKey(userid);
    }
}