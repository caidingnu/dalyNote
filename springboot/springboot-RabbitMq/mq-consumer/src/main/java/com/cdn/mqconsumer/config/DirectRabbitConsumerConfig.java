//package com.cdn.mqconsumer.config;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
///**
// * @author CDN
// * @desc
// * @date 2020/07/15 15:09
// * =================================
// * 消费者单纯的使用，其实可以不用添加这个配置，直接建后面的监听就好，
// * 使用注解来让监听器监听对应的队列即可。
// * 配置上了的话，其实消费者也是生成者的身份，也能推送该消息。
// * ===================================
// *
// */
//@Configuration
//public class DirectRabbitConsumerConfig {
//    //队列 起名：TestDirectQueue
//    @Bean(name = "TestDirectQueueConsumer")
//    public Queue testDirectQueue() {
//        return new Queue("TestDirectQueueConsumer",true);
//    }
//
//    //Direct交换机 起名：TestDirectExchange
//    @Bean(name = "TestDirectExchangeConsumer")
//    DirectExchange testDirectExchange() {
//        return new DirectExchange("TestDirectExchangeConsumer");
//    }
//
//    //绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
//    @Bean("bindingDirectc")
//    Binding bindingDirect() {
//        return BindingBuilder.bind(testDirectQueue()).to(testDirectExchange()).with("TestDirectRoutingConsumer");
//    }
//}
