package com.bjpowernode.p2p.service.impl.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.bjpowernode.p2p.dao.ReportDao;
import com.bjpowernode.p2p.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Service(interfaceClass = com.bjpowernode.p2p.service.user.UserService.class)
public class UserServiceImpl implements UserService {

    @Autowired
    ReportDao reportDao;
/*
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;*/

    @Override
    public Integer userCount() {

/*
        // 1: 获取数据
        Integer userCount = (Integer) redisTemplate.opsForValue().get("userCount");

        // 2: 判断是否要连接数据库
        if (!ObjectUtils.allNotNull(userCount)) {
            // 这里,还是会有很多,userCounter= null的值穿过, 因为多个线程,每个线程的变量不一样, 就是synchornied,
            // 锁定的只是单个线程的值
            synchronized (this){
                // 只有到,synchornied里,才能保证,变量是一致的
                userCount = (Integer) redisTemplate.opsForValue().get("userCount");
                if (!ObjectUtils.allNotNull(userCount)){
                    userCount = reportDao.userCount();
                    redisTemplate.opsForValue().set("userCount",userCount,30, TimeUnit.SECONDS);
                    return userCount;
                }
                else {
                    return userCount;
                }
            }
        }

        return userCount();*/

        return reportDao.userCount();
    }
}
