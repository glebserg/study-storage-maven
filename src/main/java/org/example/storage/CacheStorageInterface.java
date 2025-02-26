package org.example.storage;

public interface CacheStorageInterface<K, V> {

    public void put(K key, V value);
    public V get(K key);
    public void remove(K key);
    public void clear();
}
