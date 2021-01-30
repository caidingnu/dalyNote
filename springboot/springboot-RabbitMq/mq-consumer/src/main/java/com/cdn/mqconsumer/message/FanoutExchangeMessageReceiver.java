package com.cdn.mqconsumer.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author CDN
 * @desc  扇形交换机消息接收
 * @date 2020/07/15 17:43
 */
@Component
public class FanoutExchangeMessageReceiver {


    @RabbitListener(queues = "fanout.A")
    public void processm(Map testMessage) {
        System.out.println("fanout.A消费者收到消息  : " + testMessage.toString());
    }
    @RabbitListener(queues = "fanout.B")
    public void processmb(Map testMessage) {
        System.out.println("fanout.B消费者收到消息  : " + testMessage.toString());
    }
    @RabbitListener(queues = "fanout.C")
    public void processmc(Map testMessage) {
        System.out.println("fanout.C消费者收到消息  : " + testMessage.toString());
    }

}
