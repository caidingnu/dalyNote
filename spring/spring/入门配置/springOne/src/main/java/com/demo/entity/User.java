package com.demo.entity;

import com.demo.LifeCycle.Car;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * desc： 复杂类型注入
 * author CDN
 * create 2019-07-26 23:24
 * version 1.0.0
 */
public class User {
    private String name;

    List<String> list;

    List<Car> carList;

    Set<String> set;

    Map<String, String> map;

    Map<String, Car> carMap;

    public void setCarMap(Map<String, Car> carMap) {
        this.carMap = carMap;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setList(List<String> list) {
        this.list = list;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", list=" + list +
                ", carList=" + carList +
                ", set=" + set +
                ", map=" + map +
                ", carMap=" + carMap +
                '}';
    }
}
