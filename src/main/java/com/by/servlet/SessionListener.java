package com.by.servlet;

import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    /*private static int activeSessions = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        synchronized (this){
            activeSessions++;
        }
        System.out.println("新会话创建,当前活动会话数: " + activeSessions);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        synchronized (this){
            activeSessions--;
        }
        System.out.println("会话销毁,当前活动会话数: " + activeSessions);
    }*/

    private static final String ONLINE_USER_COUNT_KEY = "online_user_count";

    @Resource(name="stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        stringRedisTemplate.opsForValue().increment(ONLINE_USER_COUNT_KEY, 1);
        System.out.println("新会话创建,当前活动会话数: " + stringRedisTemplate.opsForValue().get(ONLINE_USER_COUNT_KEY));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        stringRedisTemplate.opsForValue().decrement(ONLINE_USER_COUNT_KEY);
        System.out.println("会话销毁,当前活动会话数: " + stringRedisTemplate.opsForValue().get(ONLINE_USER_COUNT_KEY));
    }
}
