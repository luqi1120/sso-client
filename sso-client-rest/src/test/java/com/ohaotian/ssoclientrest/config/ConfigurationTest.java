package com.ohaotian.ssoclientrest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created: luQi
 * Date:2018-5-18 10:00
 */
@Configuration
@ComponentScan(basePackages = "com.ohaotian.ssoclientrest.config")
public class ConfigurationTest {

    public ConfigurationTest() {
        System.out.println("ConfigurationTest容器启动初始化。。。");
    }

//    @Bean
//    @Scope("prototype")
//    public BeanTest beanTest() {
//        return new BeanTest();
//    }
}
