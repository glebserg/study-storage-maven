package org.example.storage.lfu;

import org.example.storage.CacheStorage;
import org.example.storage.utils.FileManager;

import java.util.ArrayList;
import java.util.Map;

public class LFUHDDStorage<K, V> implements CacheStorage<K, V> {

    private final String filePath = "cacheStorageDump.bin";
    private final int maxSize = 10;

    private final FileManager<K, LFUUnit> fileManager = FileManager.<K,LFUUnit>builder()
            .filePath(this.filePath)
            .build();


    @Override
    public void put(K key, V value) {
        Map<K, LFUUnit> data = this.fileManager.read();
        if (data.size() >= this.maxSize) {
            data.entrySet().stream()
                    .min((unit1, unit2) -> unit1.getValue().getCount().compareTo(unit2.getValue().getCount()))
                    .map(Map.Entry::getKey)
                    .ifPresent(data::remove);
        }
        data.put(key, data.getOrDefault(key, LFUUnit.<V>builder().value(value).count(0).build()));
        this.fileManager.write(data);

    }

    @Override
    public V get(K key) {
        Map<K, LFUUnit> data = this.fileManager.read();
        LFUUnit<V> result = data.get(key);
        if (result != null) {
            result.setCount(result.getCount() + 1);
            this.fileManager.write(data);
            return result.getValue();
        }
        return null;
    }

    @Override
    public void remove(K key) {
        Map<K, LFUUnit> data = this.fileManager.read();
        data.remove(key);
        this.fileManager.write(data);
    }

    @Override
    public void clear() {
        Map<K, LFUUnit> data = this.fileManager.read();
        data.clear();
        this.fileManager.write(data);
    }

    @Override
    public ArrayList<K> getKeys() {
        Map<K, LFUUnit> data = this.fileManager.read();
        return new ArrayList<>(data.keySet());
    }
}
