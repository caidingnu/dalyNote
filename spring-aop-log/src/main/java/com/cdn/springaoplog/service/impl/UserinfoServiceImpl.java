package com.cdn.springaoplog.service.impl;

import com.cdn.springaoplog.dao.UserinfoDao;
import com.cdn.springaoplog.entity.UserinfoPo;
import com.cdn.springaoplog.service.UserinfoService;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author itar
 * @email wuhandzy@gmail.com
 * @date 2018-12-21 05:39:43
 * @since jdk 1.8
 */
@Service("userinfoServiceImpl")
public class UserinfoServiceImpl implements UserinfoService {
    /*<AUTOGEN--BEGIN>*/

    @Autowired
    public UserinfoDao userinfoDao;


    @Override
    public Page<UserinfoPo> selectPaged(RowBounds rowBounds) {
        return userinfoDao.selectPaged(rowBounds);
    }

    @Override
    public UserinfoPo selectByPrimaryKey(Integer userid) {
        return userinfoDao.selectByPrimaryKey(userid);
    }

    @Override
    public Integer deleteByPrimaryKey(Integer userid) {
        return userinfoDao.deleteByPrimaryKey(userid);
    }

    @Override
    public Integer insert(UserinfoPo userinfo) {
        return userinfoDao.insert(userinfo);
    }

    @Override
    public Integer insertSelective(UserinfoPo userinfo) {
        return userinfoDao.insertSelective(userinfo);
    }

    @Override
    public Integer insertSelectiveIgnore(UserinfoPo userinfo) {
        return userinfoDao.insertSelectiveIgnore(userinfo);
    }

    @Override
    public Integer updateByPrimaryKeySelective(UserinfoPo userinfo) {
        return userinfoDao.updateByPrimaryKeySelective(userinfo);
    }

    @Override
    public Integer updateByPrimaryKey(UserinfoPo userinfo) {
        return userinfoDao.updateByPrimaryKey(userinfo);
    }

    @Override
    public Integer batchInsert(List<UserinfoPo> list) {
        return userinfoDao.batchInsert(list);
    }

    @Override
    public Integer batchUpdate(List<UserinfoPo> list) {
        return userinfoDao.batchUpdate(list);
    }

    /**
     * 存在即更新
     *
     * @param map
     * @return
     */
    @Override
    public Integer upsert(UserinfoPo userinfo) {
        return userinfoDao.upsert(userinfo);
    }

    /**
     * 存在即更新，可选择具体属性
     *
     * @param map
     * @return
     */
    @Override
    public Integer upsertSelective(UserinfoPo userinfo) {
        return userinfoDao.upsertSelective(userinfo);
    }

    @Override
    public List<UserinfoPo> query() {
        return userinfoDao.query();
    }


    @Override
    public Long queryTotal() {
        return userinfoDao.queryTotal();
    }

    @Override
    public Integer deleteBatch(List<Integer> list) {
        return userinfoDao.deleteBatch(list);
    }

    /*<AUTOGEN--END>*/

}
