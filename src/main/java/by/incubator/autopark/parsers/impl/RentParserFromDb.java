package by.incubator.autopark.parsers.impl;

import by.incubator.autopark.entity.RentEntity;
import by.incubator.autopark.entity.TypeEntity;
import by.incubator.autopark.entity.service.RentsService;
import by.incubator.autopark.infrastructure.core.annotations.Autowired;
import by.incubator.autopark.parsers.interfaces.ParserRentInterface;
import by.incubator.autopark.rent.Rentable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RentParserFromDb implements ParserRentInterface {
    @Autowired
    private RentsService rentsService;

    @Override
    public List<Rentable> loadRents() {
        List<RentEntity> buffer = rentsService.getAll(RentEntity.class);
        List<Rentable> rentableList = buffer.stream().collect(Collectors.toList());

        return rentableList;
    }
}
