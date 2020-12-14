package org.order.controller.serviceImpl;

import org.order.controller.service.FeignUserFallbackService;
import org.springframework.stereotype.Component;

/**
 * desc：  处理FeignUserService  中的方法断路器
 *
 * @author CDN
 * date 2020/03/07 23:21
 */
@Component
public class FeignUserFallbackServiceHystrixImpl implements FeignUserFallbackService {

    @Override
    public String getUserHellouser() {
        return "feign请求出错,熔断器返回值";
    }
}
