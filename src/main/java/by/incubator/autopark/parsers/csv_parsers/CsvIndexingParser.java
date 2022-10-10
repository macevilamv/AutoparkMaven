package by.incubator.autopark.parsers.csv_parsers;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CsvIndexingParser {
    static final Map<String, Integer> CSV_INDEX = new HashMap<>();
    private static final String CSV_CONFIG_PATH = "src/main/resources/properties/csv-indexing.properties";

    static {
        try {
            initProperties();
        } catch (IOException e) {
            throw new RuntimeException("Error: \"csv-indexing.properties\" file reading has failed.", e);
        }
    }

    private static void initProperties() throws IOException {
        try (FileReader fileReader =
                     new FileReader(CSV_CONFIG_PATH)
        ) {
            Properties properties = new Properties();
            properties.load(fileReader);

            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                CSV_INDEX.put((String) entry.getKey(), Integer.parseInt((String) entry.getValue()));
            }
        }
    }
}
