package by.incubator.autopark.parsers;

import by.incubator.autopark.entity.RentEntity;
import by.incubator.autopark.entity.TypeEntity;
import by.incubator.autopark.entity.VehicleEntity;
import by.incubator.autopark.entity.service.RentsService;
import by.incubator.autopark.entity.service.TypesService;
import by.incubator.autopark.entity.service.VehiclesService;
import by.incubator.autopark.infrastructure.core.annotations.Autowired;

import java.util.List;

public class ParserVehicleFromDb implements ParserVehicleInterface {
    @Autowired
    private TypesService typesService;
    @Autowired
    private VehiclesService vehiclesService;
    @Autowired
    private RentsService rentsService;

    public List<TypeEntity> loadTypesList() {
        return typesService.getAll(TypeEntity.class);
    }

    public List<RentEntity> loadRentsList() {
        return rentsService.getAll(RentEntity.class);
    }

    public List<VehicleEntity> loadVehiclesList() {
        return vehiclesService.getAll(VehicleEntity.class);
    }
}
