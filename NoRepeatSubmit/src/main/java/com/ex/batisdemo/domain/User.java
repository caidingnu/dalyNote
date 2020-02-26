package com.ex.batisdemo.domain;

import java.io.Serializable;

/**
 * 招标平台用户登录表
 *
 * @author itar
 * @mail wuhandzy@gmail.com
 * @date 2020-02-26 04:42:14
 * @since jdk1.8
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Integer userId;
    /**
     *
     */
    private String name;
    /**
     *
     */
    private String password;
    /**
     *
     */
    private String nickName;
    /**
     *
     */
    private Integer age;

    /**
     *
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     *
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     *
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     */
    public String getName() {
        return name;
    }

    /**
     *
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     *
     */
    public String getNickName() {
        return nickName;
    }

    /**
     *
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     *
     */
    public Integer getAge() {
        return age;
    }
}
