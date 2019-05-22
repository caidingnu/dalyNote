package com.cdn.springclouduser.controller;

import com.cdn.springclouduser.dao.FeignTestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 功能简述：
 * feign方式调用user服务
 *
 * @author caidingnu
 * @create 2019-04-08 00:19
 * @since 1.0.0
 */
@RestController
public class FeignTestController {
    @Resource
    FeignTestDao feignTestDao;

    /**
     * @Description:
     * @Param:
     * @return:
     * @Author: caidingnu
     * @Date: 2019/4/8
     */
    @RequestMapping("getMovieFeign")
    public String getUserFeign() {
        String message = "我是feign参数";
        String result = feignTestDao.hi(message);
        System.out.println(result);
        return result;
    }

}
