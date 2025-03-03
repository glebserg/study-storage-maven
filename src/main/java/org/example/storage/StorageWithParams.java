package org.example.storage;

import org.example.storage.lfu.CacheStorageLFUHDD;
import org.example.storage.lfu.CacheStorageLFURAM;
import org.example.storage.params.Location;
import org.example.storage.params.Strategy;
import lombok.Builder;

@Builder
public class StorageWithParams<K, V> {
    Strategy strategy;
    Location location;

    public static class StorageWithParamsBuilder<K, V> {
        public CacheStorage<K, V> build() {
            return switch (strategy) {
                case LFU -> switch (location) {
                    case RAM -> new CacheStorageLFURAM<K, V>();
                    case HDD -> new CacheStorageLFUHDD<K, V>();
                    default -> throw new IllegalStateException("Unsupported location: " + location);
                };
                default -> throw new IllegalStateException("Unsupported strategy: " + strategy);
            };
        }
    }
}
