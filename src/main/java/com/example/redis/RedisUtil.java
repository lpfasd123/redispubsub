package com.example.redis;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * @author：liupengfei
 * @createTime: 2019-07-23 16:32
 * @discription:
 * @package: com.example.redis
 * @project: redis
 **/
@Component
public class RedisUtil implements InitializingBean {

    @Autowired
    JedisConnectionFactory jedisConnectionFactory;

    private Jedis jedisPub;
    private Jedis jedisSub;



    private static final String key = "myWay";

    /**
     * 发布一个消息
     *
     * @param message
     */
    public void publishMsg(String message) {
        try {
            jedisPub.publish(key, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 接收消息。在main方法调用后，会一直执行下去。当有发布对应消息时，就会在jedisPubSub中接收到！
     */
    public void subscribeMsg() {
        try {
            RedisMsgSubListener listener = new RedisMsgSubListener();
            jedisSub.subscribe(listener, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        this.jedisSub = (Jedis) jedisConnectionFactory.getConnection().getNativeConnection();
        this.jedisPub = (Jedis) jedisConnectionFactory.getConnection().getNativeConnection();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                subscribeMsg();
            }
        });
        thread.start();
    }
}
