package com.ohaotian.ssoclientrest.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created: luQi
 * Date:2018-5-8 17:31
 */
public class Test {

    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();

        /**
         * 通过InvocationHandlerImpl的构造器生成一个InvocationHandler对象,
         * 需要传入被代理对象作为参数
         */
        InvocationHandler handler = new InvocationHandlerImpl(realSubject);

        ClassLoader loader = realSubject.getClass().getClassLoader();
        Class[] interfaces = realSubject.getClass().getInterfaces();

        // 需要指定类装载器、一组接口及调用处理器生成动态代理类实例

        Subject subject = (Subject) Proxy.newProxyInstance(loader, interfaces, handler);

        System.out.println("动态代理对象的类型：" + subject.getClass().getName());
        subject.hello("Tom");
        subject.bye();
    }
}
