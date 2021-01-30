package com.cdn.mqconsumer.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author CDN
 * @desc 主题交换机消息接收
 * @date 2020/07/15 17:19
 */
@Component
public class TopicExchangeMessageReceiver {


    @RabbitListener(queues = "topic.woman")
    public void process(Map testMessage) {
        System.out.println("TopicTotalReceiverwoman消费者收到消息  : " + testMessage.toString());
    }

    @RabbitListener(queues = "topic.man")
    public void processm(Map testMessage) {
        System.out.println("TopicTotalReceiverman消费者收到消息  : " + testMessage.toString());
    }


}
