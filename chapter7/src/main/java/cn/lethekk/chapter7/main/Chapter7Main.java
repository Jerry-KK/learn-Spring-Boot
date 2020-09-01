package cn.lethekk.chapter7.main;

import cn.lethekk.chapter7.config.RedisConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

public class Chapter7Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);
        redisTemplate.opsForValue().set("chapter7key","chapter7value");
        redisTemplate.opsForHash().put("chapter7hash","field","hvalue");
    }
}
