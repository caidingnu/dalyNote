package com.syudy.freemarker.entity;

/**
 * desc：
 * author CDN
 * create 2019-11-19 22:48
 * version 1.0.0
 */
public class Student {


    private int id;

    private String name;

    private int price;

    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Student(int id, String name, int price, String address) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.address = address;
    }
}
