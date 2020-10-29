package com.bjpowernode.app.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *  管理,IndexController,所需要的变量: pageSize,
 *  而该参数 应该由application.properties,管理,
 *  现在就会有一个问题, application.properties,如何与
 *  该类联系?
 *
 *   1: 要使用该容器对象,就必须将该类加入到容器中
 *      注解该类: @ConfigurationProperties
 *      方法: 1) 在入口函数 ,扫描包:
 *               @ConfigurationPropertiesScan(basePackages = "com.bjpowernode.app.properties")
 *           2) @EnableConfigurationProperties
 *
 *   2: 配置该类 : 配置前缀  @ConfigurationProperties(prefix = "com.bjpowernode")
 *      配置该类 : 配置后缀
 */


@ConfigurationProperties(prefix = "com.bjpowernode")
public class PageSizeProperties {

    private int pageSize;


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
