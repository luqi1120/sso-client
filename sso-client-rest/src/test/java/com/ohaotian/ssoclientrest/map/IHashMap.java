package com.ohaotian.ssoclientrest.map;

/**
 * Created: luQi
 * Date:2018-5-17 16:32
 */
public class IHashMap<K, V> implements IMap<K, V> {

    private static int default_length = 16;

    private static double default_load = 0.75;

    private Entry<K, V>[] table;

    private int size;

    IHashMap() {
        this(default_length, default_load);
    }

    IHashMap(int default_length, double default_load) {
        this.default_length = default_length;
        this.default_load = default_load;

        table = new Entry[default_length];
    }

    @Override
    public V put(K key, V value) {
        int index = this.getIndex(key);
        Entry<K, V> e = table[index];
        if (e == null) {
            table[index] = new Entry(key, value, null, index);
            size++;
        } else {
            Entry newEntry = new Entry(key, value, e, index);
            table[index] = newEntry;
        }
        return table[index].getValue();
    }

    private int getIndex(K key) {
        int i = this.default_length - 3;
        return key.hashCode() % i;
    }

    @Override
    public V get(K key) {
        int index = this.getIndex(key);
        return table[index] == null ? null : table[index].getValue();
    }

    public int size() {
        return size;
    }

    class Entry<K, V> implements IMap.Entry<K, V> {

        K key;
        V value;
        Entry<K, V> next;
        int index;

        public Entry(K key, V value, Entry<K, V> next, int index) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.index = index;
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
