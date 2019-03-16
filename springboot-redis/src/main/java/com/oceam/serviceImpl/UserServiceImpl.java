package com.oceam.serviceImpl;

/**
 * Author: caidingnu
 * Date:   2018/10/28
 * Time：  1:03
 * Content:
 */

import com.oceam.entity.UserInfo;
import com.oceam.mapper.UserInfoMapper;
import com.oceam.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {


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