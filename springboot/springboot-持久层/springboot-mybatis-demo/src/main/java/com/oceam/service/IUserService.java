package com.oceam.service;


import com.oceam.entity.UserInfo;

import java.util.List;

public interface IUserService {
    List findAllUser();

    UserInfo selectByPrimaryKey(Integer userid);
}
