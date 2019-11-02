package com.demo.LifeCycle;

import org.springframework.util.StringUtils;

/**
 * desc：
 * author CDN
 * create 2019-07-26 22:42
 * version 1.0.0
 */
public class LifeDaoImpl implements LifeDao {

    private int age;
    private String sex;
    private Car car;

    public void setCar(Car car) {
        this.car = car;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * desc:  初始化的方法
     * param:
     * author: CDN
     * date: 2019/7/26
     */
    public void initMethod() {
        System.out.println("初始化方法，类被实例化的时候执行--------");
    }

    @Override
    public void my(String text) {
        if (StringUtils.isEmpty(text)) {
            text = "实现接口的方法";
        }
        System.out.println(text + age + "---" + sex + "--" + car);
    }

    /**
     * desc: 销毁的方法
     * param:
     * author: CDN
     * date: 2019/7/26
     */
    public void distory() {
        System.out.println("销毁--------");
    }


}
