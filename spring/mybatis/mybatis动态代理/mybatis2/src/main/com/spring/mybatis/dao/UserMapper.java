package spring.mybatis.dao;

import org.springframework.stereotype.Service;
import spring.mybatis.pojo.Menu;

/**
 * desc:
 * author caidingnu
 * create 2019/6/10
 * version 1.0.0
 */
public interface UserMapper {

     Menu selectById(String uuid);
}
