package com.ex.batisdemo.impl;

import com.ex.batisdemo.dao.UserDao;
import com.ex.batisdemo.domain.User;
import com.ex.batisdemo.service.UserService;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService {
    /*<AUTOGEN--BEGIN>*/

    @Autowired
    public UserDao userDao;


    @Override
    public List<User> selectPaged() {
        return userDao.selectPaged();
    }

    @Override
    public User selectByPrimaryKey(Integer userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    @Override
    public Integer deleteByPrimaryKey(Integer userId) {
        return userDao.deleteByPrimaryKey(userId);
    }

    @Override
    public Integer insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public Integer insertSelective(User user) {
        return userDao.insertSelective(user);
    }

    @Override
    public Integer insertSelectiveIgnore(User user) {
        return userDao.insertSelectiveIgnore(user);
    }

    @Override
    public Integer updateByPrimaryKeySelective(User user) {
        return userDao.updateByPrimaryKeySelective(user);
    }

    @Override
    public Integer updateByPrimaryKey(User user) {
        return userDao.updateByPrimaryKey(user);
    }

    @Override
    public Integer batchInsert(List<User> list) {
        return userDao.batchInsert(list);
    }

    @Override
    public Integer batchUpdate(List<User> list) {
        return userDao.batchUpdate(list);
    }

    /**
     * 存在即更新
     *
     * @param map
     * @return
     */
    @Override
    public Integer upsert(User user) {
        return userDao.upsert(user);
    }

    /**
     * 存在即更新，可选择具体属性
     *
     * @param map
     * @return
     */
    @Override
    public Integer upsertSelective(User user) {
        return userDao.upsertSelective(user);
    }

    @Override
    public List<User> query(User user) {
        return userDao.query(user);
    }

    @Override
    public Long queryTotal() {
        return userDao.queryTotal();
    }

    @Override
    public Integer deleteBatch(List<Integer> list) {
        return userDao.deleteBatch(list);
    }

    /*<AUTOGEN--END>*/

}
