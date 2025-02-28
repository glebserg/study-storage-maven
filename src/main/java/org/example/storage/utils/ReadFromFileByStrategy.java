package org.example.storage.utils;

import lombok.Builder;
import lombok.NonNull;
import org.example.storage.lfu.LFUUnit;
import org.example.storage.params.CacheStorageStrategy;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.util.Map;

@Builder
public class ReadFromFileByStrategy {

    @NonNull
    private CacheStorageStrategy strategy;

    @NonNull
    private String filePath;

    private byte[] getRawData() throws IOException {
        File file = new File(filePath);
        return Files.readAllBytes(file.toPath());
    }


    private <K, V> Map<K, LFUUnit<V>> getLFU() {
        try {
            byte[] rawData = this.getRawData();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(rawData);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (Map<K, LFUUnit<V>>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Map read() {
        return switch (strategy) {
            case LFU -> this.getLFU();
            default -> throw new IllegalStateException("Unsupported strategy: " + strategy);
        };
    }
}
