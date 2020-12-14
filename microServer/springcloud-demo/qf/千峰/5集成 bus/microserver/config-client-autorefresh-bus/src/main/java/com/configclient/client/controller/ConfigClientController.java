package com.configclient.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc：      客户端手动刷新
 *
 * @author CDN
 * date 2020/03/09 21:48
 */
@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${spring.application.name}")
    String applicationName;
    @Value("${name}")
    String name;



    /**
     * desc:  获取注册中心的 application.name
     * param:
     * return:
     * author: CDN
     * date: 2020/3/9
     */
    @RequestMapping("appname")
    public String getAppName() {

        return applicationName;
    }


    /**
     * 测试 配置中心刷新
     *
     * @return
     */
    @RequestMapping("name")
    public String getName() {

        return name;
    }


}
