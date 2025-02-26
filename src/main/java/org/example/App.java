package org.example;


import org.example.storage.CacheStorage;
import org.example.storage.CacheStorageInterface;
import org.example.storage.params.CacheStorageStrategy;
import org.example.storage.params.CacheStorageLocation;
import org.example.utils.StorageActionEmulator;

public class App {
    public static void main(String[] args) {
        CacheStorageInterface<String, String> storageObj = CacheStorage.<String,String>builder()
                .strategy(CacheStorageStrategy.LFU)
                .location(CacheStorageLocation.RAM)
                .build();
        StorageActionEmulator emulator = new StorageActionEmulator(storageObj);
        emulator.putSyntheticData(10);
//        emulator.readData(1000);
//        emulator.putSyntheticData(100);
    }
}
