package com.cdn.springclouduser.dao;

import org.springframework.stereotype.Component;

/**
 * 功能简述：
 * 本来主要是实现feign的熔断器
 *
 * @author caidingnu
 * @create 2019-04-28 23:01
 * @since 1.0.0
 */
@Component
public class SchedualServiceHiHystric implements FeignTestDao {

    @Override
    public String hi(String message) {
        return "Sorry" + message;
    }
}
