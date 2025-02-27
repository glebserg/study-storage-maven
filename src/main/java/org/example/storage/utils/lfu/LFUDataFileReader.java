package org.example.storage.utils.lfu;

import org.example.storage.lfu.LFUUnit;
import org.example.storage.utils.BytesFromFileReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LFUDataFileReader {

    public static <K,V> Map<K, LFUUnit<V>>  readData(String filePath) throws ClassNotFoundException {
        try {
            byte[] rawData = BytesFromFileReader.readFromFile(filePath);
            return LFUDataFromBytesSerializer.deserialize(rawData);
        }
        catch (IOException e) {
            return new HashMap<>();
        }

    }
}
