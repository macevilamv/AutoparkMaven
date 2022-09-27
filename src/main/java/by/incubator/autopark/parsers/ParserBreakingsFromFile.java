package by.incubator.autopark.parsers;

import by.incubator.autopark.vehicle.Vehicle;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;

public class ParserBreakingsFromFile {
    private static final String FILE_PATH = "src/main/resources/csv/orders.csv";

    public ParserBreakingsFromFile() {

    }

    public void writeMapToFile(Map<String, Integer> defectsStatistics, Vehicle vehicle) {
        try {
            Files.write(Paths.get(FILE_PATH),
                    getLine(defectsStatistics, vehicle).getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeListToFile(List<String> list) {
        try {
            Files.write(Paths.get(FILE_PATH), list, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> readLineFromFile() {
        List<String> buffer = null;

        try {
            buffer = Files.readAllLines(Paths.get(FILE_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return buffer;
    }

    private String getLine(Map<String, Integer> defectsStatistics, Vehicle vehicle) {
        String line = String.valueOf(vehicle.getId());

        for (Map.Entry<String, Integer> entry:
                defectsStatistics.entrySet()) {
            line = line + ", " + entry.getKey() + ", " + entry.getValue();
        }
        line = line + "\n";

        return line;
    }
}
