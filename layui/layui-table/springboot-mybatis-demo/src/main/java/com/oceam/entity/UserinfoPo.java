package com.oceam.entity;

import java.io.Serializable;

/**
 * @author itar
 * @mail wuhandzy@gmail.com
 * @date 2018-12-21 05:39:43
 * @since jdk1.8
 */
public class UserinfoPo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 阿三
     */
    private Integer userid;
    /**
     * 哇去问驱蚊器
     */
    private String username;
    /**
     * 企鹅企鹅去
     */
    private String phone;
    /**
     * 企鹅企鹅去
     */
    private String sex;

    /**
     * 阿三
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 阿三
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 哇去问驱蚊器
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 哇去问驱蚊器
     */
    public String getUsername() {
        return username;
    }

    /**
     * 企鹅企鹅去
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 企鹅企鹅去
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 企鹅企鹅去
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 企鹅企鹅去
     */
    public String getSex() {
        return sex;
    }
}
