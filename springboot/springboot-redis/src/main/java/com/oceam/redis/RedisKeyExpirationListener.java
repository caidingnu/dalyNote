package com.oceam.redis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * CDN
 * 2020/05/02 16:10
 */
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {
    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 针对 redis 数据失效事件，进行数据处理
     *
     * @param message
     * @param pattern
     */

    @Override
    public void onMessage(Message message, byte[] pattern) {
// 获取到失效的 key，进行取消订单业务处理
        String expiredKey = message.toString();
        System.out.println(expiredKey+"失效了");
    }
}
