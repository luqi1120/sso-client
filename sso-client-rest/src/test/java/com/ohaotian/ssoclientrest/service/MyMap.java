package com.ohaotian.ssoclientrest.service;

/**
 * Created: luQi
 * Date:2018-5-8 14:33
 */
public interface MyMap<K,V> {

    V put(K k,V v);

    V get(K k);

    interface Entry<K,V>{
        K getKey();

        V getValue();
    }
}
