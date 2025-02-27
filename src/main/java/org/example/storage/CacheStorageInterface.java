package org.example.storage;

import java.util.List;

public interface CacheStorageInterface<K, V> {

    void put(K key, V value);
    V get(K key);
    void remove(K key);
    void clear();
    List<K> getKeys();
}
