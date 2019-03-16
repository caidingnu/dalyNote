package com.oceam.entity;

/**
 * 本类功能简述：
 * 〈〉
 *
 * @author caidingnu
 * @create 2019/3/10
 * @since 1.0.0
 */
public class Goods {
    private String id;
    private String goodName;
    private String goodPrice;
    private String goodPhone;
    private String goodAddress;
    private String goodNum;

    public Goods(String id, String goodName, String goodPrice, String goodPhone, String goodAddress, String goodNum) {
        this.id = id;
        this.goodName = goodName;
        this.goodPrice = goodPrice;
        this.goodPhone = goodPhone;
        this.goodAddress = goodAddress;
        this.goodNum = goodNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(String goodPrice) {
        this.goodPrice = goodPrice;
    }

    public String getGoodPhone() {
        return goodPhone;
    }

    public void setGoodPhone(String goodPhone) {
        this.goodPhone = goodPhone;
    }

    public String getGoodAddress() {
        return goodAddress;
    }

    public void setGoodAddress(String goodAddress) {
        this.goodAddress = goodAddress;
    }

    public String getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(String goodNum) {
        this.goodNum = goodNum;
    }
}
