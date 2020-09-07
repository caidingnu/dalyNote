package com.cdn.one.Controller.config;

import com.cdn.one.Controller.Constanst;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * desc： 缓存配置
 *
 * @author CDN
 * date 2020/09/07 23:12
 */
@Configuration
public class RedisCacheCustomizer {

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        //解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);


        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofDays(1))//缓存1天
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                .disableCachingNullValues();
        // 设置一个初始化的缓存空间set集合
        Set<String> cacheNames = new HashSet<>();
        cacheNames.add(Constanst.CACHE_1DAY);
        cacheNames.add(Constanst.CACHE_30MINS);
        cacheNames.add(Constanst.CACHE_1HOUR);
        cacheNames.add(Constanst.CACHE_10SECOND);
        // 对每个缓存空间应用不同的配置
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
//        configMap.put("my-redis-cache1", cacheConfiguration);
//        configMap.put("user", cacheConfiguration.entryTtl(Duration.ofSeconds(120)));

        configMap.put(Constanst.CACHE_1DAY, cacheConfiguration.entryTtl(Duration.ofMinutes(15)));
        configMap.put(Constanst.CACHE_30MINS, cacheConfiguration.entryTtl(Duration.ofMinutes(30)));
        configMap.put(Constanst.CACHE_1HOUR, cacheConfiguration.entryTtl(Duration.ofMinutes(60)));
        configMap.put(Constanst.CACHE_10SECOND, cacheConfiguration.entryTtl(Duration.ofMinutes(180)));
//        使用自定义的缓存配置初始化一个cacheManager //
//        注意这两句的调用顺序，一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
        RedisCacheManager cacheManager = RedisCacheManager.builder(factory).initialCacheNames(cacheNames).withInitialCacheConfigurations(configMap).build();
        return cacheManager;
    }
}
