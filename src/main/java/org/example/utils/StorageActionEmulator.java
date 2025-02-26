package org.example.utils;

import org.example.storage.CacheStorageInterface;

import java.util.ArrayList;
import java.util.Random;

public class StorageActionEmulator {

    private final CacheStorageInterface storage;

    public StorageActionEmulator(CacheStorageInterface storage) {
        this.storage = storage;
    }

    public void putSyntheticData(int count) {
        for (int i = 0; i < count + 1; i++) {
            this.storage.put("key_" + i, "value");
        }
    }

    public void readData(int count) {
        ArrayList allKeys = this.storage.getKeys();
        if (!allKeys.isEmpty()) {
            Random random = new Random();
            for (int i = 0; i < count; i++) {
                int randomIndex = random.nextInt(allKeys.size());
                Object key = allKeys.get(randomIndex);
                this.storage.get(key);
            }
        }

    }
}
