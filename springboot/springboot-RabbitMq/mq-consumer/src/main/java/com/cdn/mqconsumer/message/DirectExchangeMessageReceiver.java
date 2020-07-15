package com.cdn.mqconsumer.message;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 直连交换机消息接收
 *
 *
 * ☆☆☆☆☆  RabbitListener 注解放在类上就需要使用RabbitHandler注解放在方法上进行配合使用，
 *
 * 根据传递过来的参数类型判断那个方法执行，如果传递过来的是String类型的，就去找带有@RabbitHandler注解的方法，参数是String的
 */
@Component
//@RabbitListener(queues = "TestDirectQueueProvider")//监听的队列名称 TestDirectQueueProvider
public class DirectExchangeMessageReceiver {

    //RabbitListener 注解放在类上就需要使用RabbitHandler注解放在方法上进行配合使用，
//    @RabbitHandler
    @RabbitListener(queues = "TestDirectQueueProvider")//监听的队列名称 TestDirectQueueProvider
    public void process(Map testMessage) {
        System.out.println("DirectReceiver1消费者收到消息  : " + testMessage.toString());
    }



    @RabbitListener(queues = "TestDirectQueueProvider2")//监听的队列名称 TestDirectQueueProvider2
    public void process2(Map testMessage) {
        System.out.println("TestDirectQueueProvider2消费者收到消息  : " + testMessage.toString());
    }



}