package com.bjpowernode.app.configuration;


import com.bjpowernode.app.properties.PageSizeProperties;
import com.bjpowernode.app.service.PageInfo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *  该类相当于 applicationContext.xml , 一个创建,管理对象的容器 ,参考:RedisAutoConfiguration
 *
 *  注解解读: @Configuration 相当于 @Component,用创建容器,区别:@Configuration 可以使用代理
 *           @ConditionalOnClass  表示,有 PageSizeProperties类,才加载该类
 *           @EnableConfigurationProperties 表示, 在该容器中加入 PageSizeProperties 类
 */

@Configuration()
@ConditionalOnClass({PageSizeProperties.class})
@EnableConfigurationProperties({PageSizeProperties.class})
public class PageInfoAutoConfiguration {

    @Bean  // 表示创建 pageSizeProperties对象
    public PageInfo pageInfo(PageSizeProperties pageSizeProperties){

        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(pageSizeProperties.getPageSize());
        return pageInfo;
    }
}
