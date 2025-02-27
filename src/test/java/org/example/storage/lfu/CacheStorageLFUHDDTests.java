package org.example.storage.lfu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CacheStorageLFUHDDTests {

    private CacheStorageLFUHDD<String, String> cache;

    @BeforeEach
    void setUp() {
        cache = new CacheStorageLFUHDD<>();
    }

    @Test
    void testPutAndGet() {
        cache.put("key1", "value1");
        assertEquals("value1", cache.get("key1"));
    }

    @Test
    void testPutOverMaxSize() {
        for (int i = 0; i < 15; i++) {
            cache.put("key" + i, "value" + i);
        }
        assertEquals(10, cache.getKeys().size());
        assertNull(cache.get("key0")); // key0 должен быть удален
        assertNotNull(cache.get("key14")); // key14 должен остаться
    }

    @Test
    void testGetNonExistentKey() {
        assertNull(cache.get("nonExistentKey"));
    }

    @Test
    void testRemove() {
        cache.put("key1", "value1");
        cache.remove("key1");
        assertNull(cache.get("key1"));
    }

    @Test
    void testClear() {
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.clear();
        assertEquals(0, cache.getKeys().size());
    }

    @Test
    void testGetKeys() {
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        ArrayList<String> keys = cache.getKeys();
        assertTrue(keys.contains("key1"));
        assertTrue(keys.contains("key2"));
        assertEquals(2, keys.size());
    }

}