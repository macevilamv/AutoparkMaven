package by.incubator.autopark;

import by.incubator.autopark.collections.VehicleCollection;
import by.incubator.autopark.entity.service.*;
import by.incubator.autopark.infrastructure.core.ContextService;
import by.incubator.autopark.infrastructure.core.ObjectFactory;
import by.incubator.autopark.infrastructure.core.impl.ApplicationContext;
import by.incubator.autopark.service.ScheduledService;
import by.incubator.autopark.service.Workroom;
import by.incubator.autopark.servlets.ViewCarTypesServlet;
import lombok.SneakyThrows;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
//        ContextService service = new ContextService();
//        ApplicationContext context = service.getContext();
//        TypesService typesService = context.getObject(TypesService.class);
//        RentsService rentsService = context.getObject(RentsService.class);
//        EngineService engineService = context.getObject(EngineService.class);
//        OrdersService ordersService = context.getObject(OrdersService.class);
//        VehiclesService vehiclesService = context.getObject(VehiclesService.class);
//        engineService.loadEngineEntitiesIntoDatabase();
//        typesService.loadTypeEntitiesIntoDatabase();
//        rentsService.loadRentEntitiesIntoDatabase();
//        ordersService.loadOrderEntitiesIntoDatabase();
//        vehiclesService.loadVehicleEntitiesIntoDatabase();
//        VehicleCollection collection = context.getObject(VehicleCollection.class);
//        collection.display();
    }
}
