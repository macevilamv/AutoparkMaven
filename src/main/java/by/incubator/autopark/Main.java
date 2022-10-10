package by.incubator.autopark;

import by.incubator.autopark.engine.DieselEngine;
import by.incubator.autopark.entity.service.*;
import by.incubator.autopark.infrastructure.core.impl.ApplicationContext;
import by.incubator.autopark.infrastructure.core.impl.ObjectFactoryImpl;
import by.incubator.autopark.infrastructure.orm.service.PostgreDataBase;
import by.incubator.autopark.service.MechanicService;
import by.incubator.autopark.vehicle.CarColor;
import by.incubator.autopark.vehicle.Vehicle;
import by.incubator.autopark.vehicle.VehicleType;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        Map<Class<?>, Class<?>> interfaceToImplementations = new HashMap<>();
        ApplicationContext context = new ApplicationContext("by", interfaceToImplementations);
        ObjectFactoryImpl objectFactory = new ObjectFactoryImpl(context);
        PostgreDataBase dataBase = objectFactory.createObject(PostgreDataBase.class);
        TypesService typesService = objectFactory.createObject(TypesService.class);
        RentsService rentsService = objectFactory.createObject(RentsService.class);
        EngineService engineService = objectFactory.createObject(EngineService.class);
        OrdersService ordersService = objectFactory.createObject(OrdersService.class);
        typesService.loadTypeEntitiesIntoDatabase();
        rentsService.loadRentEntitiesIntoDatabase();
        engineService.loadEngineEntitiesIntoDatabase();
        ordersService.loadOrderEntitiesIntoDatabase();
//        VehiclesService vehiclesService = objectFactory.createObject(VehiclesService.class);
//        vehiclesService.loadVehicleEntitiesIntoDatabase();
//            MechanicService service =  objectFactory.createObject(MechanicService.class);
//        service.detectBreaking(new Vehicle (4,
//                  new VehicleType(1, "Car", 1.3d),
//                  CarColor.BLACK, "Volvo XC70", "5427 AX-7", 2323d, 123132,
//                  2009,
//                  new DieselEngine(2.4d, 9, 100),
//                  new ArrayList<>()));
    }
}
