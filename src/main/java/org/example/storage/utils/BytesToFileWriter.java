package org.example.storage.utils;

import java.io.FileOutputStream;
import java.io.IOException;

public class BytesToFileWriter {
    public static void saveToFile(byte[] data, String filePath) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            fileOutputStream.write(data);
        }
    }
}
