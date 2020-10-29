package com.bjpowernode.app.limit;

import java.lang.annotation.*;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimit {

    /** 周期,单位是秒 */
    int cycle() default 10;

    /** 请求次数 */
    int number() default 2;

    /** 默认提示信息 */
    String msg() default "请勿重复点击";

}
