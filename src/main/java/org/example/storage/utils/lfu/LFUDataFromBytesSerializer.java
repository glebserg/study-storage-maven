package org.example.storage.utils.lfu;

import org.example.storage.lfu.LFUUnit;

import java.io.*;
import java.util.Map;

public class LFUDataFromBytesSerializer {

    public static <K, V> Map<K, LFUUnit<V>> deserialize(byte[] data) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            return (Map<K, LFUUnit<V>>) objectInputStream.readObject();
        }
    }

}
