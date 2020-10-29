package com.bjpowernode.app.limit;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

public class RateLimitServiceImpl implements RateLimitService {

    private StringRedisTemplate redisTemplate;

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Override
    public Boolean limit(String ip, String uri, RateLimit rateLimit) {
        System.out.println("默认的实现,请自定义实现类覆盖当前实现");
        String key = "rate:" + ip + ":" + uri;
        // 缓存中存在key，在限定访问周期内已经调用过当前接口
        String str = redisTemplate.opsForValue().get(key);
        int ret = Integer.parseInt(str);
        String rett = String.valueOf(ret + 1);
        if (redisTemplate.hasKey(key)) {
            // 访问次数自增1
            redisTemplate.opsForValue().set(key,rett);
            // 超出访问次数限制
            if (Integer.parseInt((String) redisTemplate.opsForValue().get(key)) > rateLimit.number()) {
                return false;
            }
            // 未超出访问次数限制，不进行任何操作，返回true
        } else {
            // 第一次设置数据,过期时间为注解确定的访问周期
            redisTemplate.opsForValue().set(key, "1", rateLimit.cycle(), TimeUnit.SECONDS);
        }
        return true;
    }
}
