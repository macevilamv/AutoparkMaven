package by.incubator.autopark.parsers.impl;

import by.incubator.autopark.entity.VehicleEntity;
import by.incubator.autopark.entity.service.VehiclesService;
import by.incubator.autopark.infrastructure.core.annotations.Autowired;
import by.incubator.autopark.parsers.interfaces.ParserVehicleInterface;
import by.incubator.autopark.vehicle.Driveable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ParserVehicleFromDb implements ParserVehicleInterface {
    @Autowired
    private VehiclesService vehiclesService;

    public List<Driveable> loadVehicles() {
        List<VehicleEntity> buffer = vehiclesService.getAll(VehicleEntity.class);
        List<Driveable> driveableList = buffer.stream().collect(Collectors.toList());

        return driveableList;
    }
}
