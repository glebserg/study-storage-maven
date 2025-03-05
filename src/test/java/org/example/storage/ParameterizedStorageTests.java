package org.example.storage;

import org.junit.jupiter.api.Test;

import org.example.storage.params.Location;
import org.example.storage.params.Strategy;
import org.example.storage.lfu.LFUHDDStorage;

import static org.junit.jupiter.api.Assertions.*;


public class ParameterizedStorageTests {
    @Test
    void testGetLFUHDD() {
        assertInstanceOf(
                LFUHDDStorage.class,
                ParameterizedStorage.<String, String>builder().strategy(Strategy.LFU).location(Location.HDD).build());
    }
}
