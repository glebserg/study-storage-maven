package org.example.storage.utils;

import lombok.Builder;
import lombok.NonNull;
import org.example.storage.lfu.LFUUnit;

import java.io.*;
import java.nio.file.Files;
import java.util.Map;

@Builder
public class LFUFileStrategy<K, V> {

    @NonNull
    String filePath;

    private byte[] readFile() throws IOException {
        File file = new File(filePath);
        return Files.readAllBytes(file.toPath());
    }

    private void writeFile(byte[] rawData) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        fileOutputStream.write(rawData);
    }

    public Map<K, LFUUnit<V>> read() {
        try {
            byte[] rawData = this.readFile();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(rawData);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (Map<K, LFUUnit<V>>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(Map data) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(data);
            byte[] rawData = byteArrayOutputStream.toByteArray();
            this.writeFile(rawData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
