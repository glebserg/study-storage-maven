package org.example.storage.lfu;

import org.example.storage.CacheStorageInterface;

import java.util.HashMap;

public class CacheStorageLFURAM<K,V> implements CacheStorageInterface<K,V> {
    final private HashMap<K,V> data = new HashMap<>();
    final private HashMap<K,Integer> counter = new HashMap<>();

    @Override
    public void put(K key, V value) {
        this.data.put(key, value);
    }

    @Override
    public V get(K key) {
        V result = this.data.get(key);
        if (result != null) {
            counter.put(key, counter.getOrDefault(key, 0) + 1);
        }
        return result;
    }

    @Override
    public void remove(K key) {
        this.data.remove(key);
        this.counter.remove(key);
    }

    @Override
    public void clear() {
        this.data.clear();
        this.counter.clear();
    }


}
