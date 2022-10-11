package by.incubator.autopark.parsers.csv_parsers;

import by.incubator.autopark.entity.OrderEntity;
import by.incubator.autopark.parsers.interfaces.BreakingParserInterface;
import by.incubator.autopark.vehicle.Driveable;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BreakingParserFromCsvFile implements BreakingParserInterface {
    private static final String FILE_PATH = "src/main/resources/csv/orders.csv";

    public BreakingParserFromCsvFile() {

    }

    public List<OrderEntity> loadOrderEntities() {
        List<OrderEntity> orderEntities = new ArrayList<>();
        List<String> ordersStringsList = readLineFromStorage();

        for (String csv : ordersStringsList) {
            orderEntities.add(createOrderEntity(csv));
        }
        return orderEntities;
    }

    private OrderEntity createOrderEntity(String csvOrderString) {
        Long vehicleId = Long.parseLong(csvOrderString.substring(0,1));
        String order = csvOrderString.substring(1);

        return new OrderEntity(vehicleId, order);
    }

    @Override
    public void writeBreakingsToStorage(Map<String, Integer> defectsStatistics, Driveable vehicle) {
        try {
            Files.write(Paths.get(FILE_PATH),
                    getLine(defectsStatistics, vehicle).getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void clean(Driveable vehicle) {
        List<String> list = readLineFromStorage();
        String regex = vehicle.getId() + ".*";

        list.removeIf(i -> i.matches(regex));
        try {
            Files.write(Paths.get(FILE_PATH), list, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<String> readLineFromStorage() {
        List<String> buffer = null;

        try {
            buffer = Files.readAllLines(Paths.get(FILE_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return buffer;
    }

    private String getLine(Map<String, Integer> defectsStatistics, Driveable vehicle) {
        String line = String.valueOf(vehicle.getId());

        for (Map.Entry<String, Integer> entry:
                defectsStatistics.entrySet()) {
            line = line + ", " + entry.getKey() + ", " + entry.getValue();
        }
        line = line + "\n";

        return line;
    }
}
