package org.example;


import org.example.storage.CacheStorage;
import org.example.storage.CacheStorageInterface;
import org.example.storage.params.CacheStorageStrategy;
import org.example.storage.params.CacheStorageLocation;

public class App {
    public static void main(String[] args) {
        CacheStorageInterface<String, String> storageObj = CacheStorage.<String,String>builder()
                .strategy(CacheStorageStrategy.LFU)
                .location(CacheStorageLocation.RAM)
                .build();

        storageObj.put("key1", "1");
        System.out.println(storageObj.getKeys());
    }
}
