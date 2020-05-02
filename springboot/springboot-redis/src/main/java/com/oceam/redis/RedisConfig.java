package com.oceam.redis;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis配置类
 *
 * @author zhangzhixiang
 * @date 2019年06月19日
 */
@Configuration
public class RedisConfig {

    @Bean("redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 将刚才的redis连接工厂设置到模板类中
        template.setConnectionFactory(redisConnectionFactory);
        // 设置key的序列化器
        template.setKeySerializer(new StringRedisSerializer());
        // 设置value的序列化器
        //使用Jackson 2，将对象序列化为JSONJackson2JsonRedisSerializer
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        //json转对象类，不设置默认的会将json转成hashmap
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        return template;
    }

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        监听所有的库
        container.setConnectionFactory(connectionFactory);
        //下面这种方式是灵活配置，针对每个库(0-14)的失效key做处理
        container.addMessageListener(new RedisExpiredListener(), new PatternTopic("__keyevent@5__:expired"));
        return container;
    }
}
