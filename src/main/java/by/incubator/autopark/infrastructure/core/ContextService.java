package by.incubator.autopark.infrastructure.core;

import by.incubator.autopark.entity.RentEntity;
import by.incubator.autopark.entity.TypeEntity;
import by.incubator.autopark.entity.VehicleEntity;
import by.incubator.autopark.infrastructure.core.impl.ApplicationContext;
import by.incubator.autopark.infrastructure.core.impl.ObjectFactoryImpl;
import by.incubator.autopark.parsers.csv_parsers.BreakingParserFromCsvFile;
import by.incubator.autopark.parsers.csv_parsers.RentsParserFromCsvFile;
import by.incubator.autopark.parsers.csv_parsers.VehicleParserFromCsvFile;
import by.incubator.autopark.parsers.csv_parsers.VehicleTypeParserFromCsvFile;
import by.incubator.autopark.parsers.impl.BreakingsParserFromDb;
import by.incubator.autopark.parsers.impl.ParserVehicleFromDb;
import by.incubator.autopark.parsers.impl.RentParserFromDb;
import by.incubator.autopark.parsers.impl.VehicleTypeParserFromDb;
import by.incubator.autopark.parsers.interfaces.BreakingParserInterface;
import by.incubator.autopark.parsers.interfaces.ParserRentInterface;
import by.incubator.autopark.parsers.interfaces.ParserVehicleInterface;
import by.incubator.autopark.parsers.interfaces.ParserVehicleTypeInterface;
import by.incubator.autopark.rent.Rent;
import by.incubator.autopark.rent.Rentable;
import by.incubator.autopark.service.Fixer;
import by.incubator.autopark.service.MechanicService;
import by.incubator.autopark.vehicle.Driveable;
import by.incubator.autopark.vehicle.TypeInterface;
import by.incubator.autopark.vehicle.Vehicle;
import by.incubator.autopark.vehicle.VehicleType;

import java.util.HashMap;
import java.util.Map;

public class ContextService {
    ApplicationContext context;
    public ApplicationContext getContext() {
        this.context = new ApplicationContext("by", getConfiguredInterfaceToImplementations());

        return context;
    }

    public Map<Class<?>, Class<?>> getConfiguredInterfaceToImplementations() {
       Map<Class<?>, Class<?>> interfaceToImplementations = new HashMap<>();
       configure(interfaceToImplementations);

       return interfaceToImplementations;
    }
    private void configure(Map<Class<?>, Class<?>> interfaceToImplementations ) {
        interfaceToImplementations.put(Rentable.class, RentEntity.class);
        interfaceToImplementations.put(Driveable.class, VehicleEntity.class);
        interfaceToImplementations.put(TypeInterface.class, TypeEntity.class);
        interfaceToImplementations.put(ParserVehicleInterface.class, ParserVehicleFromDb.class);
        interfaceToImplementations.put(ParserRentInterface.class, RentParserFromDb.class);
        interfaceToImplementations.put(ParserVehicleTypeInterface.class, VehicleTypeParserFromDb.class);
        interfaceToImplementations.put(BreakingParserInterface.class, BreakingsParserFromDb.class);
        interfaceToImplementations.put(Fixer.class, MechanicService.class);
    }
}
