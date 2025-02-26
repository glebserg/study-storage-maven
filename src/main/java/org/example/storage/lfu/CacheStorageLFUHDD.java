package org.example.storage.lfu;
import org.example.storage.CacheStorageInterface;

public class CacheStorageLFUHDD<K,V> implements CacheStorageInterface<K,V> {
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
}
