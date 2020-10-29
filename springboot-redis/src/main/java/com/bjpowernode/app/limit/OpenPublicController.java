package com.bjpowernode.app.limit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户接口管理")
@RestController
public class OpenPublicController {

   /* @Autowired
    RedisTemplate redisTemplate;*/

    @ApiOperation("rate测试")
    @RateLimit(number = 3, cycle = 10)
    @GetMapping("/rate")
    public void rate() {

        System.out.println("正常调用");
    }

    @ApiOperation("test")
    @GetMapping("/test")
    public void test(){

        System.out.println("已经转发过来了");
    }
}
