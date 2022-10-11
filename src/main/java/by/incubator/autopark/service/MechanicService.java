package by.incubator.autopark.service;

import by.incubator.autopark.parsers.interfaces.BreakingParserInterface;
import by.incubator.autopark.vehicle.Driveable;
import by.incubator.autopark.infrastructure.core.annotations.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MechanicService implements Fixer {
    private final String[] DETAILS =
            {"Фильтр", "Втулка", "Вал", "Ось", "Свеча", "Масло", "ГРМ", "ШРУС"};
    private final int MAX_DEFECTS_NUM = 12;
    @Autowired
    private BreakingParserInterface parser;

    public MechanicService() {
    }

    @Override
    public Map<String, Integer> detectBreaking(Driveable vehicle) {
        Map<String, Integer> defectsStatistics = new HashMap<>();
        int defectsNumber = (int) (Math.random() * MAX_DEFECTS_NUM);

        initDefectsMap(defectsStatistics);
        writeToMap(defectsStatistics, defectsNumber);
        parser.writeBreakingsToStorage(defectsStatistics, vehicle);

        return defectsStatistics;
    }

    @Override
    public void repair(Driveable vehicle) {
        parser.clean(vehicle);
    }

    @Override
    public boolean isBroken(Driveable vehicle) {
        List<String> list = parser.readLineFromStorage();

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
