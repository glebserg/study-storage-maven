package org.example.storage.lfu;

import org.example.storage.CacheStorageInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CacheStorageLFURAM<K, V> implements CacheStorageInterface<K, V> {

    private final int maxSize = 10;
    private final Map<K, LFUUnit<V>> data = new HashMap<>();

    @Override
    public void put(K key, V value) {
        if (this.data.size() >= this.maxSize) {
            this.data.entrySet().stream()
                    .min((unit1, unit2) -> unit1.getValue().getCount().compareTo(unit2.getValue().getCount()))
                    .map(Map.Entry::getKey)
                    .ifPresent(this.data::remove);
        }
        this.data.put(key, this.data.getOrDefault(key, LFUUnit.<V>builder().value(value).count(0).build()));
    }

    @Override
    public V get(K key) {
        LFUUnit<V> result = this.data.get(key);
        if (result != null) {
            result.setCount(result.getCount() + 1);
            return result.getValue();
        }
        return null;
    }

    @Override
    public void remove(K key) {
        this.data.remove(key);
    }

    @Override
    public void clear() {
        this.data.clear();
    }

    @Override
    public ArrayList<K> getKeys() {
        return new ArrayList<>(data.keySet());
    }


}
