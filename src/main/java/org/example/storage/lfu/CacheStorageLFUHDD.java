package org.example.storage.lfu;
import org.example.storage.CacheStorageInterface;

import java.util.HashMap;
import java.util.Set;

public class CacheStorageLFUHDD<K,V> implements CacheStorageInterface<K,V> {

    final private int cacheSize = 10;
    final private HashMap<K,V> data = new HashMap<>();
    final private HashMap<K,Integer> counter = new HashMap<>();

    @Override
    public void put(Object key, Object value) {

    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public void remove(K key) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> getKeys() {
        return this.data.keySet();
    }
}
