package com.oceam.entity;

public class UserInfo {
    private Integer userid;

    private String username;

    private int phone;

    private String sex;

    public UserInfo(Integer userid, String username, int phone, String sex) {
        this.userid = userid;
        this.username = username;
        this.phone = phone;
        this.sex = sex;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", phone=" + phone +
                ", sex='" + sex + '\'' +
                '}';
    }
}