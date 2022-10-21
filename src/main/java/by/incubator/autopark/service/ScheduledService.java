package by.incubator.autopark.service;

import by.incubator.autopark.collections.VehicleCollection;
import by.incubator.autopark.threads.annotations.Schedule;
import by.incubator.autopark.vehicle.Driveable;

import java.util.ArrayList;
import java.util.List;

public class ScheduledService {
    List<Driveable> repairedVehicles = new ArrayList<>();

    @Schedule(timeout = 5000, delta = 5000)
    public void scheduledCheck(Workroom workroom, VehicleCollection vehicleCollection) {
        repairedVehicles = workroom.getRepairedVehicles(vehicleCollection.getVehicles());
    }
}
