package com.ohaotian.ssoclientrest.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created: luQi
 * Date:2018-5-8 17:28
 */
public class InvocationHandlerImpl implements InvocationHandler {

    private Object subject; // 这个就是我们要代理的真实对象，也就是真正执行业务逻辑的类

    public InvocationHandlerImpl(Object subject) {// 通过构造方法传入这个被代理对象
        this.subject = subject;
    }


    /**
     * 该方法负责集中处理动态代理类上的所有方法调用。 调用处理器根据这三个参数进行预处理或分派到委托类实例上反射执行
     *
     * @param proxy  最终生成的代理类实例
     * @param method 被调用的方法对象
     * @param args   调用上面method时传入的参数
     * @return method对应的方法的返回值
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("可以在调用实际方法前做一些事情");
        System.out.println("当前调用的方法是" + method.getName());
        // 当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        result = method.invoke(subject, args);// 需要指定被代理对象和传入参数
        System.out.println(method.getName() + "方法的返回值是" + result);
        System.out.println("可以在调用实际方法后做一些事情");
        System.out.println("------------------------");
        return result;// 返回method方法执行后的返回值
    }
}
