package by.incubator.autopark.parsers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileParser {
    public static List<String> readFromFile(File file) throws IOException {
        List<String> buffer = new ArrayList<>();
        String string = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((string = reader.readLine()) != null) {
                buffer.add(string);
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Error: cannot read the file", e);
        }
        return buffer;
    }
}
