package by.incubator.autopark.entity.service;

import by.incubator.autopark.entity.VehicleEntity;
import by.incubator.autopark.infrastructure.core.annotations.Autowired;
import by.incubator.autopark.infrastructure.core.annotations.InitMethod;
import by.incubator.autopark.infrastructure.orm.EntityManager;
import by.incubator.autopark.parsers.csv_parsers.VehicleParserFromCsvFile;
import lombok.SneakyThrows;

import java.util.List;

public class VehiclesService {
    @Autowired
    EntityManager entityManager;
    @Autowired
    VehicleParserFromCsvFile parser;
    List<VehicleEntity> vehicleEntityList;
    @SneakyThrows
    @InitMethod
    public void init() {
        this.vehicleEntityList = parser.loadVehicleEntities();
    }

    public void loadVehicleEntitiesIntoDatabase() {
        vehicleEntityList.stream().forEach(this::save);
    }

    public VehicleEntity get(Long id) {
        return entityManager.get(id, VehicleEntity.class).get();
    }

    public List<VehicleEntity> getAll(Class<VehicleEntity> clazz) {
        return entityManager.getAll(clazz);
    }

    public Long save(VehicleEntity vehicle) {
        return entityManager.save(vehicle);
    }

    public void delete(Object obj) {
        entityManager.delete(obj);
    }
}
