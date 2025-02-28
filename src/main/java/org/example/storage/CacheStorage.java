package org.example.storage;

import org.example.storage.lfu.CacheStorageLFUHDD;
import org.example.storage.lfu.CacheStorageLFURAM;
import org.example.storage.params.CacheStorageLocation;
import org.example.storage.params.CacheStorageStrategy;
import lombok.Builder;

@Builder
public class CacheStorage<K, V> {
    CacheStorageStrategy strategy;
    CacheStorageLocation location;

    public static class CacheStorageBuilder<K, V> {
        public CacheStorageInterface<K, V> build() {
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
