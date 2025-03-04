package org.example.storage;

import lombok.NonNull;
import org.example.storage.lfu.HDDStorage;
import org.example.storage.lfu.RAMStorage;
import org.example.storage.params.Location;
import org.example.storage.params.Strategy;
import lombok.Builder;

@Builder
public class StorageWithParams<K, V> {
    @NonNull
    Strategy strategy;

    @NonNull
    Location location;

    public static class StorageWithParamsBuilder<K, V> {
        public CacheStorage<K, V> build() {
            return switch (strategy) {
                case LFU -> switch (location) {
                    case RAM -> new RAMStorage<K, V>();
                    case HDD -> new HDDStorage<K, V>();
                    default -> throw new IllegalStateException("Unsupported location: " + location);
                };
                default -> throw new IllegalStateException("Unsupported strategy: " + strategy);
            };
        }
    }
}
