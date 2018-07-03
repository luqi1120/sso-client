package com.ohaotian.ssoclientrest.service;

/**
 * Created: luQi
 * Date:2018-5-8 13:30
 */
public class Singleton {


    /**
     * 懒汉，线程安全
     */
    /*private static Singleton instance;

    private Singleton() {}

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }*/

    /**
     * 饿汉
     */
    /*private static Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }*/


    /**
     * 静态内部类
     */
    /*private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    private Singleton() {}

    public static final Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }*/

    /**
     * 枚举
     */
    /*public enum Singleton {
        INSTANCE;
        public void whateverMethod() {

        }
    }*/

    /**
     * 双重锁单例
     */
    private volatile static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
