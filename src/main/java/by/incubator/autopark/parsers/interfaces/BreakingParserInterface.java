package by.incubator.autopark.parsers.interfaces;

import by.incubator.autopark.vehicle.Driveable;

import java.util.List;
import java.util.Map;

public interface BreakingParserInterface {
    void writeBreakingsToStorage(Map<String, Integer> defectsStatistics, Driveable vehicle);
    List<String> readLineFromStorage();
    void clean(Driveable vehicle);
}
