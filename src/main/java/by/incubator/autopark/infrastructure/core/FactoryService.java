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
import by.incubator.autopark.vehicle.Driveable;
import by.incubator.autopark.vehicle.TypeInterface;
import by.incubator.autopark.vehicle.Vehicle;
import by.incubator.autopark.vehicle.VehicleType;

import java.util.HashMap;
import java.util.Map;

public class FactoryService {
   private Map<Class<?>, Class<?>> interfaceToImplementations = new HashMap<>();
   private ApplicationContext context = new ApplicationContext("by", interfaceToImplementations);


    public ObjectFactory getObjectFactory() {
        configure();
        ObjectFactory factory = new ObjectFactoryImpl(context);

        return new ObjectFactoryImpl(context);
    }

    public void configure() {
        interfaceToImplementations.put(Rentable.class, RentEntity.class);
        interfaceToImplementations.put(Driveable.class, VehicleEntity.class);
        interfaceToImplementations.put(TypeInterface.class, TypeEntity.class);
        interfaceToImplementations.put(ParserVehicleInterface.class, ParserVehicleFromDb.class);
        interfaceToImplementations.put(ParserRentInterface.class, RentParserFromDb.class);
        interfaceToImplementations.put(ParserVehicleTypeInterface.class, VehicleTypeParserFromDb.class);
        interfaceToImplementations.put(BreakingParserInterface.class, BreakingsParserFromDb.class);
    }

}
