package com.cdn.mqconsumer.message;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.Map;

/**
 * 直连交换机消息接收
 * <p>
 * <p>
 * ☆☆☆☆☆  RabbitListener 注解放在类上就需要使用RabbitHandler注解放在方法上进行配合使用，
 * <p>
 * 根据传递过来的参数类型判断那个方法执行，如果传递过来的是String类型的，就去找带有@RabbitHandler注解的方法，参数是String的
 */
@Component
//@RabbitListener(queues = "TestDirectQueueProvider")//监听的队列名称 TestDirectQueueProvider
public class DirectExchangeMessageReceiver {

    //RabbitListener 注解放在类上就需要使用RabbitHandler注解放在方法上进行配合使用，
//    @RabbitHandler
    @RabbitListener(queues = "TestDirectQueueProvider")//监听的队列名称 TestDirectQueueProvider
    public void process(Map testMessage, Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            System.out.println("DirectReceiver1消费者收到消息  : " + testMessage.toString());
            System.out.println(deliveryTag + "---009");
            //确认签收
            channel.basicAck(deliveryTag, true);
        } catch (Exception e) {
            //签收失败
            /**
             * deliveryTag:该消息的index
             * multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
             * requeue：被拒绝的是否重新入队列
             */
//            channel.basicNack 与 channel.basicReject 的区别在于basicNack可以拒绝多条消息，而basicReject一次只能拒绝一条消息
            //如果重新放进队列中还是会放在队列头部，继续消费者消费，如果一直消费一直错误就会产生堆积问题，理性使用
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);


            channel.basicReject(deliveryTag, true);

        }
    }


    @RabbitListener(queues = "TestDirectQueueProvider2")//监听的队列名称 TestDirectQueueProvider2
    public void process2(Map testMessage) {
        System.out.println("TestDirectQueueProvider2消费者收到消息  : " + testMessage.toString());
    }


}