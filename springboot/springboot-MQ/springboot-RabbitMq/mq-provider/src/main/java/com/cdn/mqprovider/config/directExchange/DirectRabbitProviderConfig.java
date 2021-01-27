package com.cdn.mqprovider.config.directExchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息提供者的配置
 * （直连型交换机）
 *
 *
 * 那么直连交换机既然是一对一，那如果咱们配置多台监听绑定到同一个直连交互的同一个队列
 * 可以看到是实现了轮询的方式对消息进行消费，而且不存在重复消费。
 *
 */
@Configuration
public class DirectRabbitProviderConfig {

//    =========================================  初始化队列===========================================
    //队列 起名：TestDirectQueue
    @Bean
    public Queue testDirectQueue() {
//        name :第一个为对队列名称
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        //   return new Queue("TestDirectQueue",true,true,false);
        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue("TestDirectQueueProvider", true);
    }
    @Bean
    public Queue testDirectQueue2() {
        return new Queue("TestDirectQueueProvider2", true);
    }

//################################################ 初始化交换机 =======================================================
    //Direct交换机 起名：TestDirectExchange
    @Bean
    DirectExchange testDirectExchange() {
        //  return new DirectExchange("TestDirectExchange",true,true);
        return new DirectExchange("TestDirectExchangeProvider", true, false);
    }

//    ================================== 交换机和队列绑定========================================================
    /**
     * 1.如果是fanout(扇型交换机)的模式的话，就没有后面的with路由关键字,扇型交换机，这个交换机没有路由键概念，就算你绑了路由键也是无视的。 这个交换机在接收到消息后，会直接转发到绑定到它上面的所有队列
     * 2.如果是topic模式的话是支持通配符的
     * 1.如果是fanout的模式的话，就没有后面的with路由关键字
     * 　　2.如果是topic模式的话是支持通配符的
     * 　　　　通配符规则：
     * ​ 　　　　 `#`：匹配一个或多个词
     * ​ 　　　　 `*`：匹配不多不少恰好1个词
     * 　　　　举例：
     * ​ 　　　　 `audit.#`：能够匹配`audit.irs.corporate`队列 或者 `audit.irs`队列
     * ​ 　　　　 `audit.*`：只能匹配`audit.irs`队列
     * ---------------------------------------------------------------------------
     * 绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
     */
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(testDirectQueue()).to(testDirectExchange()).with("TestDirectRoutingKey");
    }
    @Bean
    Binding bindingDirect2() {
        return BindingBuilder.bind(testDirectQueue2()).to(testDirectExchange()).with("TestDirectRoutingKey2");
    }
//###########################################################################################################################

//    消息推送到server，找到交换机了，但是没找到队列 【配置中文测试的，不是必要_】
//    种情况就是需要新增一个交换机，但是不给这个交换机绑定队列，我来简单地在DirectRabitConfig里面新增一个直连交换机，名叫‘lonelyDirectExchange’，但没给它做任何绑定配置操作：
    @Bean
    DirectExchange lonelyDirectExchange() {
        return new DirectExchange("lonelyDirectExchangeProvider");
    }


}