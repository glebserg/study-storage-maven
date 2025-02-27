package org.example.storage.utils.lfu;

import org.example.storage.lfu.LFUUnit;
import org.example.storage.utils.BytesFromFileReader;
import org.example.storage.utils.BytesToFileWriter;

import java.io.IOException;
import java.util.Map;

public class LFUDataFileWriter {

    public static <K,V> void  writeData(Map<K, LFUUnit<V>> data,String filePath) throws IOException, ClassNotFoundException {
        byte[] byteArray = LFUDataToBytesSerializer.serialize(data);
        BytesToFileWriter.saveToFile(byteArray, filePath);
    }
}
