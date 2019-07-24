package com.example.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPubSub;

/**
 * @author：liupengfei
 * @createTime: 2019-07-23 16:31
 * @discription:
 * @package: com.example.redis
 * @project: redis
 **/
public class RedisMsgSubListener extends JedisPubSub {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    // 取得订阅的消息后的处理
    public void onMessage(String channel, String message) {
        logger.info(channel + "=" + message);
        System.out.println(channel + "=" + message);
    }

    @Override
    // 取得按表达式的方式订阅的消息后的处理
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println(pattern + "=" + channel + "=" + message);
    }

    @Override
    // 初始化订阅时候的处理
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println(channel + "=" + subscribedChannels);
    }

    @Override
    // 取消订阅时候的处理
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println(channel + "=" + subscribedChannels);
    }

    @Override
    // 取消按表达式的方式订阅时候的处理
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        System.out.println(pattern + "=" + subscribedChannels);
    }

    @Override
    // 初始化按表达式的方式订阅时候的处理
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println(pattern + "=" + subscribedChannels);
    }

}
