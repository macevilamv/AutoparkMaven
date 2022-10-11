package by.incubator.autopark.parsers.impl;

import by.incubator.autopark.entity.OrderEntity;
import by.incubator.autopark.entity.service.OrdersService;
import by.incubator.autopark.infrastructure.core.annotations.Autowired;
import by.incubator.autopark.parsers.interfaces.BreakingParserInterface;
import by.incubator.autopark.vehicle.Driveable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BreakingsParserFromDb implements BreakingParserInterface {
    @Autowired
    OrdersService service;

    @Override
    public void writeBreakingsToStorage(Map<String, Integer> defectsStatistics, Driveable vehicle) {
        service.save(createOrderEntity(getLine(defectsStatistics, vehicle)));
    }

    @Override
    public List<String> readLineFromStorage() {
        List<String> buffer = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        service.getAll(OrderEntity.class).stream().forEach(el -> {
            builder.append(el.getVehicleId() + el.getBreakings());
            buffer.add(builder.toString());
            buffer.clear();
        });

        return buffer;
    }

    @Override
    public void clean(Driveable vehicle) {
       OrderEntity deletedEntity = service.get(vehicle.getId());
        service.delete(deletedEntity);
    }

    private OrderEntity createOrderEntity(String csvOrderString) {
        Long vehicleId = Long.parseLong(csvOrderString.substring(0,1));
        String order = csvOrderString.substring(1);

        return new OrderEntity(vehicleId, order);
    }

    private String getLine(Map<String, Integer> defectsStatistics, Driveable vehicle) {
        String line = String.valueOf(vehicle.getId());

        for (Map.Entry<String, Integer> entry:
                defectsStatistics.entrySet()) {
            line = line + " " + entry.getKey() + " " + entry.getValue() + ", ";
        }
        line = line + "\n";

        return line;
    }
}
