package com.oceam.redis;

import com.oceam.entity.UserInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author
 * @history 2018年8月30日 新建
 * @since JDK1.8
 */
@RestController
public class RedisController {


    @Resource
    RedisUtil redisUtil;


    /**
     * 存取值，设置10s有效时间
     * 存list
     *
     * @return
     */
    @RequestMapping("/getRedis")
    public Object getRedis() {
        List<Integer> list = new ArrayList<Integer>() {{
            add(1);
            add(1);
            add(1);
            add(1);
            add(1);
            add(1);
            add(1);
            add(1);
            add(1);
            add(1);
        }};
        redisUtil.set("a", list.toString(), 10L);
        System.out.println(redisUtil.get("a"));

        return redisUtil.get("a");
    }


    /**
     * desc:  存储对象
     * param:
     * return:
     * author: CDN
     * date: 2019/6/13
     */
    @RequestMapping("setObj")
    public Object setObj() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserid(33);
        userInfo.setUsername("刘备");
        userInfo.setPhone("123456");
        userInfo.setSex("男");
        userInfo.setPhone("11111");
        redisUtil.set("user", userInfo, 100L);
        System.out.println(redisUtil.getExpire("user"));
        return redisUtil.get("user");
    }


}