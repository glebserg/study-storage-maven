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
            if (strategy == null || location == null) {
                throw new IllegalStateException("Strategy and location must be set");
            }
            String combination = this.strategy.name() + "-" + this.location.name();
            switch (combination) {
                case "LFU-RAM":
                    return new CacheStorageLFURAM<K, V>();
                case "LFU-HDD":
                    return new CacheStorageLFUHDD<K, V>();
            }
            throw new IllegalStateException(
                    "Unsupported combination : " + this.strategy + ", " + this.location);
        }
    }
}
