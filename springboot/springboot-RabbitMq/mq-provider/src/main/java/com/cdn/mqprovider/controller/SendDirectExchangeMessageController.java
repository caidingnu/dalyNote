package com.cdn.mqprovider.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 **/
@RestController
public class SendDirectExchangeMessageController {

    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "我是队列1!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", "直接交换机"+messageData);
        map.put("createTime", createTime);


        Map<String, Object> map2 = new HashMap<>();
        map2.put("msg","我是队列2");

        //将消息携带绑定键值：TestDirectRouting TestDirectExchangeProvider
        rabbitTemplate.convertAndSend("TestDirectExchangeProvider", "TestDirectRoutingKey", map);


        rabbitTemplate.convertAndSend("TestDirectExchangeProvider", "TestDirectRoutingKey2", map2);
        return "ok";
    }


}