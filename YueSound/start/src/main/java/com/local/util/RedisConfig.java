package com.local.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author hhs
 * @create 2022-07-05 9:50
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String,Object> redisTemplate (RedisConnectionFactory redisConnectionFactory){
        //创建redisTemplate对象
        RedisTemplate<String,Object> template=new RedisTemplate<>();
        //设置连接工厂
        template.setConnectionFactory(redisConnectionFactory);
        //设置key序列化工具
        template.setKeySerializer(RedisSerializer.string());
        //设置value序列化工具
        template.setValueSerializer(RedisSerializer.string());
        return template;
    }

}
