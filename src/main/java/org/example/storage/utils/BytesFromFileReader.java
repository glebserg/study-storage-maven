package org.example.storage.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class BytesFromFileReader {
    public static byte[] readFromFile(String filePath) throws IOException {
        File file = new File(filePath);
        return Files.readAllBytes(file.toPath());
    }
}
