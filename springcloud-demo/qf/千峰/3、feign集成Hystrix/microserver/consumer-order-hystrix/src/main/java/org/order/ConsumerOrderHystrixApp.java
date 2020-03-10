package org.order;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 *
 * @author cdn
 */
@SpringBootApplication
@EnableEurekaClient
//@EnableHystrix //启用熔断功能， //开启熔断功能，当请求大量错误之后，直接调用熔断器方法，不会再去调用逻辑方法
@EnableCircuitBreaker
@SpringCloudApplication    //可以代替以上三个注解
public class ConsumerOrderHystrixApp {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderHystrixApp.class);
        System.out.println("Hello Hystrix!");
    }

    @Bean
    public RestTemplate getTem() {
        return new RestTemplate();
    }


    /**
     *   http://localhost:8701/actuator/hystrix.stream
     *   该bean只是为了通过这个url能够看到断路器的状态 【实质性不起什么作用】
     * @return
     */
//    @Bean
//    public ServletRegistrationBean hystrixMetricsStreamServlet() {
//        ServletRegistrationBean registration = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
//        registration.addUrlMappings("/actuator/hystrix.stream");
//        return registration;
//    }
}
