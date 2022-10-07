package by.incubator.autopark.service;

import by.incubator.autopark.parsers.csv_parsers.BreakingParserFromCsvFile;
import by.incubator.autopark.vehicle.Vehicle;
import by.incubator.autopark.infrastructure.core.annotations.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MechanicService implements Fixer {
    private final String[] DETAILS =
            {"Фильтр", "Втулка", "Вал", "Ось", "Свеча", "Масло", "ГРМ", "ШРУС"};
    private final int MAX_DEFECTS_NUM = 12;
    @Autowired
    private BreakingParserFromCsvFile parser;

    public MechanicService() {
    }

    @Override
    public Map<String, Integer> detectBreaking(Vehicle vehicle) {
        Map<String, Integer> defectsStatistics = new HashMap<>();
        int defectsNumber = (int) (Math.random() * MAX_DEFECTS_NUM);

        initDefectsMap(defectsStatistics);
        writeToMap(defectsStatistics, defectsNumber);
        parser.writeMapToFile(defectsStatistics, vehicle);

        return defectsStatistics;
    }

    @Override
    public void repair(Vehicle vehicle) {
        List<String> list = parser.readLineFromFile();
        String regex = vehicle.getId() + ".*";

        list.removeIf(i -> i.matches(regex));
        parser.writeListToFile(list);
    }

    @Override
    public boolean isBroken(Vehicle vehicle) {
        List<String> list = parser.readLineFromFile();

        for (String string : list) {
            if (vehicle.getId() == string.charAt(0)) {
                return true;
            }
        }
        return false;
    }

    private void writeToMap(Map<String, Integer> defectsStatistics, int defectsNumber) {
        String defect;

        for (int i = 0; i < defectsNumber; i++) {
            defect = DETAILS[(int) (Math.random() * DETAILS.length)];
            defectsStatistics.computeIfPresent(defect, (key, oldValue) -> oldValue + 1);
        }
    }

    private void initDefectsMap(Map<String, Integer> defectsStatistics) {
        for (String string : DETAILS) {
            defectsStatistics.put(string, 0);
        }
    }
}
