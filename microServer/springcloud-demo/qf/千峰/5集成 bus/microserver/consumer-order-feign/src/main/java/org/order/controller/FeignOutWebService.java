package org.order.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * desc：使用url地址请求
 *
 * @author CDN
 * date 2020/03/07 19:03
 */
//name是无效的，但是必填
@FeignClient(name ="aaa" ,url = "http://www.baidu.com")
public interface FeignOutWebService {

    @RequestMapping(value = "/")    //调用 http://www.baidu.com/  服务
    String getContent();

}
