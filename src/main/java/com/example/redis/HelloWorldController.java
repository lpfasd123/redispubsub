package com.example.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author：liupengfei
 * @createTime: 2019-07-23 16:27
 * @discription:
 * @package: com.example.redis
 * @project: redis
 **/
@RestController
@RequestMapping("/hello")
public class HelloWorldController {
    @Autowired
    RedisUtil redisUtil;

    @PostMapping("/")
    public void sendMessage(@RequestBody Map<String, String> map){
        String context = map.get("context") + System.currentTimeMillis();
        redisUtil.publishMsg(context);
        System.out.println("send : " + context);
        System.out.println("--------------------------------------------------------");
    }
}