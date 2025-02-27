package org.example.storage.utils.lfu;

import org.example.storage.lfu.LFUUnit;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

public class LFUDataToBytesSerializer {
    public static <K, V> byte[] serialize(Map<K, LFUUnit<V>> data) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(data);
            return byteArrayOutputStream.toByteArray();
        }
    }
}
