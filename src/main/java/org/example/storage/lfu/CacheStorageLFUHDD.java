package org.example.storage.lfu;
import org.example.storage.CacheStorageInterface;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CacheStorageLFUHDD<K, V> implements CacheStorageInterface<K, V> {

    final private String filePath = "data.json";
    final private int maxSize = 10;
    final private HashMap<K, V> data = new HashMap<>();
    final private HashMap<K, Integer> counter = new HashMap<>();

    @Override
    public void put(K key, V value) {
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
    public ArrayList<K> getKeys() {
        return new ArrayList<>(data.keySet());
    }
}
