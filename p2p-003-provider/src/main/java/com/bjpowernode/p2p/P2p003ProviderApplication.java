package com.bjpowernode.p2p;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan(basePackages = "com.bjpowernode.p2p.dao")
@EnableDubboConfiguration
public class P2p003ProviderApplication {

    public static void main(String[] args) {

        SpringApplicationBuilder bu = new SpringApplicationBuilder(P2p003ProviderApplication.class);
        ConfigurableApplicationContext context = bu.web(WebApplicationType.SERVLET).run(args);

        Object reportDao = context.getBean("reportDao");
        System.out.println(reportDao);
    }

}
