package org.example.storage;

import java.util.Set;

public interface CacheStorageInterface<K, V> {

    public void put(K key, V value);
    public V get(K key);
    public void remove(K key);
    public void clear();
    public Set<K> getKeys();
}
