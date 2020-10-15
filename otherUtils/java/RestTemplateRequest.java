package com.study.mybatisplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * desc：   利用RestTemplate调用其他服务的借口
 * author CDN
 * create 2019-11-06 17:21
 * version 1.0.0
 */
@RestController
@Configuration
public class RestTemplateRequest {
    @Autowired
    private RestTemplate restTemplate;


    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {

        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);//单位为ms
        factory.setConnectTimeout(5000);//单位为ms
        return factory;
    }

    /**
     * desc:
     * param: []
     * return:
     * author: CDN
     * date: 2019/11/6
     */
    @RequestMapping("qq")
    public Object aa(){
        return startHttpMethod("http://127.0.0.1:8087/api/admin/v1/bid/list?pageIndex=0&pageSize=10",null,"post");
    }


    public String startHttpMethod(String ReqAdderss, List<Map<String, String>> argMapList, String httpMethod) {
        Map<String, HttpMethod> httpMethodMap = new HashMap<>();
        httpMethodMap.put("HEAD", HttpMethod.HEAD);
        httpMethodMap.put("GET", HttpMethod.GET);
        httpMethodMap.put("POST", HttpMethod.POST);
        httpMethodMap.put("PUT", HttpMethod.PUT);
        httpMethodMap.put("PATCH", HttpMethod.PATCH);
        httpMethodMap.put("DELETE", HttpMethod.DELETE);
        httpMethodMap.put("OPTIONS", HttpMethod.OPTIONS);
        httpMethodMap.put("TRACE", HttpMethod.TRACE);
        if (httpMethodMap.get(httpMethod.toUpperCase()) == null) {
            return "不是支持的请求方式";
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        headers.set(HttpHeaders.ACCEPT_CHARSET, StandardCharsets.UTF_8.toString());
//  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
////添加请求的参数
//        params.add("pageIndex", "0");             //必传
//        params.add("pageSize", "10");           //选传
        if (null !=argMapList && argMapList.size()>0){
            for (Map<String, String> stringStringMap : argMapList) {
                for (String s : stringStringMap.keySet()) {
                    params.add(s,stringStringMap.get(s));
                }
            }
        }
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
//  执行HTTP请求
        ResponseEntity<String> response = restTemplate.exchange(ReqAdderss, httpMethodMap.get(httpMethod.toUpperCase()), requestEntity, String.class);  //最后的参数需要用String.class  使用其他的会报错
        return response.getBody();
    }


}
