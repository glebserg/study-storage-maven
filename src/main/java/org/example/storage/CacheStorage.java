package org.example.storage;

import org.example.storage.lfu.CacheStorageLFUHDD;
import org.example.storage.lfu.CacheStorageLFURAM;
import org.example.storage.params.CacheStorageLocation;
import org.example.storage.params.CacheStorageStrategy;


public class CacheStorage {
    CacheStorageStrategy strategy;
    CacheStorageLocation location;

    public static CacheStorageBuilder builder() {
        return new CacheStorageBuilder();
    }

    public static class CacheStorageBuilder {
        private CacheStorageStrategy strategy;
        private CacheStorageLocation location;

        public CacheStorageBuilder strategy(CacheStorageStrategy strategy) {
            this.strategy = strategy;
            return this;
        }

        public CacheStorageBuilder location(CacheStorageLocation location) {
            this.location = location;
            return this;
        }

        public CacheStorageInterface build() {

            if (this.strategy == null || this.location == null) {
                throw new IllegalStateException("Strategy and location must be set");
            }

            if (this.strategy == CacheStorageStrategy.LFU) {
                if (this.location == CacheStorageLocation.RAM) {
                    return new CacheStorageLFURAM<>();
                } else {
                    return new CacheStorageLFUHDD<>();
                }
            }
            throw new IllegalStateException(
                    "Unsupported combination of strategy and location: "
                    + this.strategy + ", " + this.location);
        }
    }
}
