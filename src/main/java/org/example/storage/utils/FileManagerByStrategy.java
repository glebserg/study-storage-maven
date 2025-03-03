package org.example.storage.utils;

import java.util.Map;
import lombok.Builder;
import lombok.NonNull;
import org.example.storage.params.CacheStorageStrategy;


@Builder
public class FileManagerByStrategy {
    @NonNull
    private CacheStorageStrategy strategy;

    @NonNull
    private String filePath;


    public Map read() {
        return switch (strategy) {
            case LFU -> LFUFileStrategy.builder().filePath(this.filePath).build().read();
            default -> throw new IllegalStateException("Unsupported strategy: " + strategy);
        };
    }

    public void write(Map data) {
        switch (strategy) {
            case LFU -> LFUFileStrategy.builder().filePath(this.filePath).build().write(data);
            default -> throw new IllegalStateException("Unsupported strategy: " + strategy);
        }

    }
}
