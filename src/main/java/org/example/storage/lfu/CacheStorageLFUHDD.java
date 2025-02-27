package org.example.storage.lfu;

import org.example.storage.CacheStorageInterface;
import org.example.storage.utils.lfu.LFUDataFileReader;
import org.example.storage.utils.lfu.LFUDataFileWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class CacheStorageLFUHDD<K, V> implements CacheStorageInterface<K, V> {

    private final String filePath = "cacheStorageDump.bin";
    private final int maxSize = 10;


    @Override
    public void put(K key, V value) {
        try {
            Map<K, LFUUnit<V>> data = LFUDataFileReader.readData(this.filePath);
            if (data.size() >= this.maxSize) {
                data.entrySet().stream()
                        .min((unit1, unit2) -> unit1.getValue().getCount().compareTo(unit2.getValue().getCount()))
                        .map(Map.Entry::getKey)
                        .ifPresent(data::remove);
            }
            data.put(key, data.getOrDefault(key, LFUUnit.<V>builder().value(value).count(0).build()));
            LFUDataFileWriter.writeData(data, this.filePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public V get(K key) {
        try {
            Map<K, LFUUnit<V>> data = LFUDataFileReader.readData(this.filePath);
            LFUUnit<V> result = data.get(key);
            if (result != null) {
                result.setCount(result.getCount() + 1);
                LFUDataFileWriter.writeData(data, this.filePath);
                return result.getValue();
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void remove(K key) {
        try {
            Map<K, LFUUnit<V>> data = LFUDataFileReader.readData(this.filePath);
            data.remove(key);
            LFUDataFileWriter.writeData(data, this.filePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clear() {
        try {
            Map<K, LFUUnit<V>> data = LFUDataFileReader.readData(this.filePath);
            data.clear();
            LFUDataFileWriter.writeData(data, this.filePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<K> getKeys() {
        try {
            Map<K, LFUUnit<V>> data = LFUDataFileReader.readData(this.filePath);
            return new ArrayList<>(data.keySet());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
