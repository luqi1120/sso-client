package com.ohaotian.ssoclientrest.service;


/**
 * Created: luQi
 * Date:2018-5-8 14:19
 */
public class MyHashMap<K, V> implements MyMap<K, V> {

    private static int length_default = 16; // 默认长度

    private static double load_default = 0.75; // 加载因子

    private Entry<K, V>[] table; // 定义数组,用来保存entry对象

    private int size;

    // 构造函数
    MyHashMap(int length_default, double load_default) {
        this.length_default = length_default;
        this.load_default = load_default;

        table = new Entry[length_default]; // 定义一个默认数组，长度就是传过来的长度
    }


    MyHashMap() {
        this(length_default, load_default);
    }


    @Override
    public V put(K key, V value) {

        int index = this.getIndex(key); // 计算索引下标

        Entry<K, V> e = table[index];  //根据这个下标判断该数据是否有数据

        if (e == null) {
            table[index] = new Entry(key, value, null, index);
            size++;  //数组长度加1
        } else {
            Entry newEntry = new Entry(key, value, e, index);
            table[index] = newEntry;
        }

        return table[index].getValue();
    }

    private int getIndex(K key) {
        int i = this.length_default - 3;  // 16-3=13 13为质数, 这里用取模计算下标
        return key.hashCode() % i;
    }

    @Override
    public V get(K key) {

        int index = this.getIndex(key);  //得到要放的数据的位置:也就是数组的下标
        return table[index] == null ? null : table[index].getValue();
    }

    public int size() {
        return size;
    }

    class Entry<K, V> implements MyMap.Entry<K, V> {

        K key;

        V value;

        Entry<K, V> next;

        int index;//记录下标

        Entry(K k, V v, Entry<K, V> n, int inx) {
            key = k;
            value = v;
            index = inx;
            next = n;//数组第一个元素的下一个元素
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }
    }
}
