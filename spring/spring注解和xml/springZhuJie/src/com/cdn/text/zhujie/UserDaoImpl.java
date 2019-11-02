package com.cdn.text.zhujie;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * desc：
 * author CDN
 * create 2019-06-09 13:46
 * version 1.0.0
 */
@Component(value = "userDaoImpl")
//相当于xml的 <bean  id="userDaoImpl" class="com.cdn.text.zhujie.UserDaoImpl" init-method="init_method" destroy-method="destory">
public class UserDaoImpl implements UserDao {

    @Value("蔡定努")  //属性注入DI  相当于xml的 <property name="name" value="蔡定努"></property>
    private String name;

    @Override
    public void save() {
        System.out.println("Dao中保存用户方法执行了--------" + name);
    }
}
