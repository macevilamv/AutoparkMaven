package by.incubator.autopark;

import by.incubator.autopark.collections.VehicleCollection;
import by.incubator.autopark.entity.service.*;
import by.incubator.autopark.infrastructure.core.FactoryService;
import by.incubator.autopark.infrastructure.core.ObjectFactory;
import by.incubator.autopark.service.MechanicService;
import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        FactoryService service = new FactoryService();
        ObjectFactory objectFactory = service.getObjectFactory();
        TypesService typesService = objectFactory.createObject(TypesService.class);
        RentsService rentsService = objectFactory.createObject(RentsService.class);
        EngineService engineService = objectFactory.createObject(EngineService.class);
        OrdersService ordersService = objectFactory.createObject(OrdersService.class);
        typesService.loadTypeEntitiesIntoDatabase();
        rentsService.loadRentEntitiesIntoDatabase();
        engineService.loadEngineEntitiesIntoDatabase();
        ordersService.loadOrderEntitiesIntoDatabase();
        VehiclesService vehiclesService = objectFactory.createObject(VehiclesService.class);
        vehiclesService.loadVehicleEntitiesIntoDatabase();
        VehicleCollection collection = objectFactory.createObject(VehicleCollection.class);
        MechanicService mechanicService =  objectFactory.createObject(MechanicService.class);
        collection.getVehicles().stream().forEach(e -> mechanicService.detectBreaking(e));
        collection.getVehicles().stream().forEach(e -> mechanicService.repair(e));
    }
}
