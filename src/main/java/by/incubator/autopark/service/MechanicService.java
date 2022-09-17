package by.incubator.autopark.service;

import by.incubator.autopark.vehicle.Vehicle;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MechanicService implements Fixer {
    private static final String[] DETAILS =
            {"Фильтр", "Втулка", "Вал", "Ось", "Свеча", "Масло", "ГРМ", "ШРУС"};
    private static final int MAX_DEFECTS_NUM = 12;
    private static final String filePath = "src/main/resources/csv/orders.csv";

    @Override
    public Map<String, Integer> detectBreaking(Vehicle vehicle) {
        Map<String, Integer> defectsStatistics = new HashMap<>();
        int defectsNumber = (int) (Math.random() * MAX_DEFECTS_NUM);

        initDefectsMap(defectsStatistics);
        writeToMap(defectsStatistics, defectsNumber);
        writeMapToFile(defectsStatistics, vehicle);

        return defectsStatistics;
    }

    @Override
    public void repair(Vehicle vehicle) {
        List<String> list = readLineFromFile(filePath);
        String regex = vehicle.getId() + ".*";

        list.removeIf(i -> i.matches(regex));
        writeListToFile(list);
    }

    @Override
    public boolean isBroken(Vehicle vehicle) {
        List<String> list = readLineFromFile(filePath);

        for (String string : list) {
            if (vehicle.getId() == string.charAt(0)) {
                return true;
            }
        }
        return false;
    }

    private static void writeToMap(Map<String, Integer> defectsStatistics, int defectsNumber) {
        String defect;

        for (int i = 0; i < defectsNumber; i++) {
            defect = DETAILS[(int) (Math.random() * DETAILS.length)];
            defectsStatistics.computeIfPresent(defect, (key, oldValue) -> oldValue + 1);
        }
    }

    private static void initDefectsMap(Map<String, Integer> defectsStatistics) {
        for (String string : DETAILS) {
            defectsStatistics.put(string, 0);
        }
    }

    private static void writeMapToFile(Map<String, Integer> defectsStatistics, Vehicle vehicle) {
        try {
            Files.write(Paths.get(filePath),
                    getLine(defectsStatistics, vehicle).getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeListToFile(List<String> list) {
        try {
            Files.write(Paths.get(filePath), list, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readLineFromFile(String filePath) {
        List<String> buffer = null;

        try {
            buffer = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return buffer;
    }

    private static String getLine(Map<String, Integer> defectsStatistics, Vehicle vehicle) {
        String line = String.valueOf(vehicle.getId());

        for (Map.Entry<String, Integer> entry:
                defectsStatistics.entrySet()) {
            line = line + ", " + entry.getKey() + ", " + entry.getValue();
        }
        line = line + "\n";

        return line;
    }
}
