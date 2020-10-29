package com.bjpowernode.app.limit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RateLimitBeanConfig {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Bean
    @ConditionalOnMissingBean(RateLimitService.class)
    public RateLimitService rateLimitService() {
        RateLimitServiceImpl rateLimitServiceImpl = new RateLimitServiceImpl();
        rateLimitServiceImpl.setRedisTemplate(redisTemplate);
        return rateLimitServiceImpl;
    }
}
