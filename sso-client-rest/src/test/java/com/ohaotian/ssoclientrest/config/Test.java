package com.ohaotian.ssoclientrest.config;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created: luQi
 * Date:2018-5-18 10:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    private BeanTest beanTest;

//    public static void main(String[] args) {
//
//        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationTest.class);
//
//        //BeanTest beanTest = (BeanTest)context.getBean("beanTest");
//        beanTest.sayHello();
//    }

    @org.junit.Test
    public void test1() {
        //ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationTest.class);
        beanTest.sayHello();
    }
}
