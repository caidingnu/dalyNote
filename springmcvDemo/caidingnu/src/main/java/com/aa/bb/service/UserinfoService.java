package com.aa.bb.service;

import com.aa.bb.dao.UserinfoMapper;
import com.aa.bb.model.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by CodeX4J.
 */
@Service
public class UserinfoService {
    @Autowired
    private UserinfoMapper userinfoMapper;

    public int add(Userinfo userinfo) {
        return userinfoMapper.insert(userinfo);
    }

    public Userinfo find(int id) {
        return userinfoMapper.selectByPrimaryKey(id);
    }

    public int update(Userinfo userinfo) {
        return userinfoMapper.updateByPrimaryKeySelective(userinfo);
    }

    public int delete(int id) {
        return userinfoMapper.deleteByPrimaryKey(id);
    }
}