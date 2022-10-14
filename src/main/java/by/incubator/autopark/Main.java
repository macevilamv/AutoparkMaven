package by.incubator.autopark;

import by.incubator.autopark.collections.VehicleCollection;
import by.incubator.autopark.entity.service.*;
import by.incubator.autopark.infrastructure.core.FactoryService;
import by.incubator.autopark.infrastructure.core.ObjectFactory;
import by.incubator.autopark.service.MechanicService;
import by.incubator.autopark.service.ScheduledService;
import by.incubator.autopark.service.Workroom;
import lombok.SneakyThrows;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    @SneakyThrows
    public static void main(String[] args) throws IOException, ParseException {
        FactoryService service = new FactoryService();
        ObjectFactory objectFactory = service.getObjectFactory();
        VehicleCollection collection = objectFactory.createObject(VehicleCollection.class);
        ScheduledService scheduledService = objectFactory.createObject(ScheduledService.class);
        Workroom workroom =  objectFactory.createObject(Workroom.class);
        scheduledService.scheduledCheck(workroom, collection);
        Thread.sleep(50000);
    }
}
