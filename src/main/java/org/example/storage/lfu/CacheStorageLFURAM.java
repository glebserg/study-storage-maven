package org.example.storage.lfu;

import org.example.storage.CacheStorageInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class CacheStorageLFURAM<K, V> implements CacheStorageInterface<K, V> {

    final private int maxSize = 10;
    final private HashMap<K, V> data = new HashMap<>();
    final private HashMap<K, Integer> counter = new HashMap<>();

    private K getKeyWithMinCount() {
        ArrayList<K> allKeys = this.getKeys();
        K keyWithMinCount = allKeys.get(0);
        int minCount = this.counter.get(keyWithMinCount);
        for (K key : allKeys) {
            int tmpCount = this.counter.get(key);
            if (tmpCount < minCount) {
                minCount = tmpCount;
                keyWithMinCount = key;
            }
            if (minCount == 0) {
                break;
            }
        }
        return keyWithMinCount;
    }

    private void overflow() {
        K candidate = this.getKeyWithMinCount();
        this.remove(candidate);
    }

    @Override
    public void put(K key, V value) {
        if (this.data.size() >= this.maxSize) {
            this.overflow();
        }
        this.data.put(key, value);
        this.counter.put(key, this.counter.getOrDefault(key, 0));
    }

    @Override
    public V get(K key) {
        V result = this.data.get(key);
        if (result != null) {
            this.counter.put(key, this.counter.getOrDefault(key, 0) + 1);
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

    @Override
    public ArrayList<K> getKeys() {
        return new ArrayList<>(data.keySet());
    }


}
