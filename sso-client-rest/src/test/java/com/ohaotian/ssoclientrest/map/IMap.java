package com.ohaotian.ssoclientrest.map;

/**
 * Created: luQi
 * Date:2018-5-17 16:32
 */
public interface IMap<K, V> {

    V put(K key, V value);

    V get(K key);

    interface Entry<K, V>{
        K getKey();

        V getValue();
    }
}
