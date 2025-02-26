package org.example.storage;

import java.util.ArrayList;

public interface CacheStorageInterface<K, V> {

    public void put(K key, V value);
    public V get(K key);
    public void remove(K key);
    public void clear();
    public ArrayList<K> getKeys();
}
