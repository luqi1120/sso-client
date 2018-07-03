package com.ohaotian.ssoclientrest.proxy;

/**
 * Created: luQi
 * Date:2018-5-8 17:24
 */
public class RealSubject implements Subject {

    @Override
    public void hello(String name) {
        System.out.println("hello "+name);
    }

    @Override
    public String bye() {
        System.out.println("bye");
        return "bye";
    }
}
