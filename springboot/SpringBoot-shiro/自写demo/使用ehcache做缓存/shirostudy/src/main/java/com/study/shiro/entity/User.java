package com.study.shiro.entity;


import java.io.Serializable;

public class User implements Serializable {


    private static final long serialVersionUID = 1L;

    private Integer id;

    private String userName;  //用户名

    private String password;  //密码

    private String remarks; // 备注

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
        super();
    }

}
