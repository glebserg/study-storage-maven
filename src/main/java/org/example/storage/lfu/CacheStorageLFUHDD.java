package org.example.storage.lfu;

import org.example.storage.CacheStorageInterface;
import org.example.storage.params.CacheStorageStrategy;
import org.example.storage.utils.ReadFromFileByStrategy;
import org.example.storage.utils.WriteToFileByStrategy;

import java.util.ArrayList;
import java.util.Map;

public class CacheStorageLFUHDD<K, V> implements CacheStorageInterface<K, V> {

    private final String filePath = "cacheStorageDump.bin";
    private final int maxSize = 10;

    private final ReadFromFileByStrategy fileReader = ReadFromFileByStrategy.builder()
            .strategy(CacheStorageStrategy.LFU)
            .filePath(this.filePath)
            .build();

    private final WriteToFileByStrategy fileWriter = WriteToFileByStrategy.builder()
            .strategy(CacheStorageStrategy.LFU)
            .filePath(this.filePath)
            .build();

    @Override
    public void put(K key, V value) {
        Map<K, LFUUnit<V>> data = this.fileReader.read();
        if (data.size() >= this.maxSize) {
            data.entrySet().stream()
                    .min((unit1, unit2) -> unit1.getValue().getCount().compareTo(unit2.getValue().getCount()))
                    .map(Map.Entry::getKey)
                    .ifPresent(data::remove);
        }
        data.put(key, data.getOrDefault(key, LFUUnit.<V>builder().value(value).count(0).build()));
        this.fileWriter.write(data);

    }

    @Override
    public V get(K key) {
        Map<K, LFUUnit<V>> data = this.fileReader.read();
        LFUUnit<V> result = data.get(key);
        if (result != null) {
            result.setCount(result.getCount() + 1);
            this.fileWriter.write(data);
            return result.getValue();
        }
        return null;
    }

    @Override
    public void remove(K key) {
        Map<K, LFUUnit<V>> data = this.fileReader.read();
        data.remove(key);
        this.fileWriter.write(data);
    }

    @Override
    public void clear() {
        Map<K, LFUUnit<V>> data = this.fileReader.read();
        data.clear();
        this.fileWriter.write(data);
    }

    @Override
    public ArrayList<K> getKeys() {
        Map<K, LFUUnit<V>> data = this.fileReader.read();
        return new ArrayList<>(data.keySet());
    }
}
