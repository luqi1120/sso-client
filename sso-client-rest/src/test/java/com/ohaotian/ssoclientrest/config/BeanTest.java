package com.ohaotian.ssoclientrest.config;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created: luQi
 * Date:2018-5-18 10:01
 */
@Component
//@Data
public class BeanTest {

//    private String username;
//    private String url;
//    private String password;

    public void sayHello() {
        System.out.println("TestBean sayHello...");
    }

//    public String toString() {
//        return "username:" + this.username + ",url:" + this.url + ",password:" + this.password;
//    }
//
//    public void start() {
//        System.out.println("TestBean 初始化。。。");
//    }
//
//    public void cleanUp() {
//        System.out.println("TestBean 销毁。。。");
//    }
}
