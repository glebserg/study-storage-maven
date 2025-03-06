package org.example.storage;

import org.junit.jupiter.api.Test;

import org.example.storage.params.Location;
import org.example.storage.params.Strategy;
import org.example.storage.lfu.LFUHDDStorage;
import org.example.storage.lfu.LFURAMStorage;

import static org.junit.jupiter.api.Assertions.*;


public class ParameterizedStorageTests {
    @Test
    void testGetLFUHDD() {
        assertInstanceOf(
                LFUHDDStorage.class,
                ParameterizedStorage.<String, String>builder().strategy(Strategy.LFU).location(Location.HDD).build());
    }

    @Test
    void testGetLFURAM() {
        assertInstanceOf(
                LFURAMStorage.class,
                ParameterizedStorage.<String, String>builder().strategy(Strategy.LFU).location(Location.RAM).build());
    }
}
