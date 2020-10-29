package com.bjpowernode.app.service.impl;

import com.bjpowernode.app.dao.ReportDao;
import com.bjpowernode.app.service.LoanService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;


@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    ReportDao reportDao;

    @Autowired
    RedisTemplate redisTemplate;


    @Override
    public double avgLoanRate() {

        /*//把key进行字符串序列化，让我们可以在redis数据库中看到具体的字符
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //把value进行字符串序列化，让我们可以在redis数据库中看到具体的字符
        redisTemplate.setValueSerializer(new StringRedisSerializer());*/

        // 1:在redis中获取对象
        Double avgLoanRate = (Double) redisTemplate.opsForValue().get("avgLoanRate");

        // 2:判断对象是否为空
        //  错误:avgLoanRate == null
        if (!ObjectUtils.allNotNull(avgLoanRate)) {
            synchronized (this) { // 锁住,不给,其他对象连接数据库, 最终目的,redis不能被击穿,连接到mysql
                avgLoanRate = (Double)redisTemplate.opsForValue().get("avgLoanRate");
                if (!ObjectUtils.allNotNull(avgLoanRate)) {
                    // 3:redis,没有该数据,连接mysql
                    avgLoanRate = reportDao.avgLoanRate();

                    System.out.println("进入数据库获取数据");

                    // 4:连接mysql,将数据存入,redis
                    redisTemplate.opsForValue().set("avgLoanRate", avgLoanRate, 30, TimeUnit.SECONDS);
                    return avgLoanRate;
                }
            }
        }

        return avgLoanRate;
    }
}
