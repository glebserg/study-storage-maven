package org.example.storage;

import lombok.NonNull;
import org.example.storage.lfu.LFUHDDStorage;
import org.example.storage.lfu.LFURAMStorage;
import org.example.storage.params.Location;
import org.example.storage.params.Strategy;
import lombok.Builder;

@Builder
public class ParameterizedStorage<K, V> {
    @NonNull
    private Strategy strategy;

    @NonNull
    private Location location;

    public static class ParameterizedStorageBuilder<K, V> {
        public CacheStorage<K, V> build() {
            return switch (strategy) {
                case LFU -> switch (location) {
                    case RAM -> new LFURAMStorage<K, V>();
                    case HDD -> new LFUHDDStorage<K, V>();
                    default -> throw new IllegalStateException("Unsupported location: " + location);
                };
                default -> throw new IllegalStateException("Unsupported strategy: " + strategy);
            };
        }
    }
}
