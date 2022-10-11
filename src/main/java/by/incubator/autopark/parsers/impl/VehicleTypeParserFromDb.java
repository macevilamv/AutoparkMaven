package by.incubator.autopark.parsers.impl;

import by.incubator.autopark.entity.RentEntity;
import by.incubator.autopark.entity.TypeEntity;
import by.incubator.autopark.entity.service.TypesService;
import by.incubator.autopark.infrastructure.core.annotations.Autowired;
import by.incubator.autopark.parsers.interfaces.ParserVehicleTypeInterface;
import by.incubator.autopark.rent.Rentable;
import by.incubator.autopark.vehicle.TypeInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleTypeParserFromDb implements ParserVehicleTypeInterface {
    @Autowired
    private TypesService typesService;

    @Override
    public List<TypeInterface> loadVehicleTypes() {
        List<TypeEntity> buffer = typesService.getAll(TypeEntity.class);
        List<TypeInterface> typesList =  buffer.stream().collect(Collectors.toList());

        return typesList;
    }
}
