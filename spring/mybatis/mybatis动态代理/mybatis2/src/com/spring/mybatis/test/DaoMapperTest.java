package com.spring.mybatis.test;

import com.spring.mybatis.dao.UserMapper;
import com.spring.mybatis.pojo.Menu;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * desc：动态代理开发
 * author CDN
 * create 2019-06-10 13:19
 * version 1.0.0
 */

public class DaoMapperTest {
    private SqlSessionFactory sqlSessionFactory;

    /**
     * desc:
     * param:
     * author: CDN
     * date: 2019/6/10
     */
    @Test
    public void aa() throws IOException {

//        1. 创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 2. 加载SqlMapConfig.xml配置文件
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfigDongtai.xml");
        // 3. 创建SqlSessionFactory对象
        this.sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
// 4. 创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Menu menu = userMapper.selectById("32");

        System.out.println(menu.toString());

    }


}
