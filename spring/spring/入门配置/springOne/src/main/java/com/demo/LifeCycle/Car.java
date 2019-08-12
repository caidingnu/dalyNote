package com.demo.LifeCycle;

import java.util.List;

/**
 * desc：
 * author CDN
 * create 2019-07-26 23:12
 * version 1.0.0
 */
public class Car {

    private String name;

    private int price;



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

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
