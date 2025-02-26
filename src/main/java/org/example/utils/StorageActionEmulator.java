package org.example.utils;
import org.example.storage.CacheStorageInterface;

import java.util.Random;
import java.util.Set;

public class StorageActionEmulator {

    private final CacheStorageInterface storage;

    public StorageActionEmulator(CacheStorageInterface storage){
        this.storage = storage;
    }

    public void createSyntheticData(int count){
        for (int i = 1; i < count; i++) {
            this.storage.put("key_"+i, "value");
        }
    }

    public void readData(int count) {
        Set<Object> allKeys = this.storage.getKeys();

//        Random random = new Random();
//        for (int i = 0; i < count; i++) {
//            int randomIndex = random.nextInt(allKeys.size());
//            Object key = allKeys[randomIndex];
//            this.storage.get(key);
//        }
    }
}
