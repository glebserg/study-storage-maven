package org.example.storage.utils;

import lombok.Builder;
import lombok.NonNull;
import org.example.storage.lfu.LFUUnit;
import org.example.storage.params.CacheStorageStrategy;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

@Builder
public class WriteToFileByStrategy {
    @NonNull
    private CacheStorageStrategy strategy;

    @NonNull
    private String filePath;


    private <K, V> void writeLFU(Map<K, LFUUnit<V>> data) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(data);
            byte[] rawData = byteArrayOutputStream.toByteArray();
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            fileOutputStream.write(rawData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(Map data) {
        switch (strategy) {
            case LFU -> this.writeLFU(data);
            default -> throw new IllegalStateException("Unsupported strategy: " + strategy);
        }
        ;
    }
}
