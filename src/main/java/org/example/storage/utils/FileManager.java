package org.example.storage.utils;

import lombok.Builder;
import lombok.NonNull;
import org.example.storage.Unit;

import java.io.*;
import java.nio.file.Files;
import java.util.Map;

@Builder
public class FileManager<K, U extends Unit> {

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

    private byte[] convertToByteArray(Map<K, U> data) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(data);
        return byteArrayOutputStream.toByteArray();
    }

    private Map<K, U> convertToMap(byte[] rawData) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(rawData);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return (Map<K, U>) objectInputStream.readObject();
    }

    public Map<K, U> read() {
        try {
            byte[] rawData = this.readFile();
            return this.convertToMap(rawData);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(Map data) {
        try {
            byte[] rawData = this.convertToByteArray(data);
            this.writeFile(rawData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
