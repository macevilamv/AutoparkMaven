package by.incubator.autopark.service;

import by.incubator.autopark.collections.VehicleCollection;
import by.incubator.autopark.threads.annotations.Schedule;
import by.incubator.autopark.vehicle.Driveable;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

@Getter
@Setter
public class ScheduledService {
    List<Driveable> repairedVehicles = new ArrayList<>();
    List<Long> repairedVehiclesId = new ArrayList<>();
    String lastCheckTime;

    @Schedule(timeout = 5000, delta = 5000)
    public void scheduledCheck(Workroom workroom, VehicleCollection vehicleCollection) {
        repairedVehicles = workroom.getRepairedVehicles(vehicleCollection.getVehicles());
        repairedVehiclesId = workroom.getRepairedVehiclesId(vehicleCollection.getVehicles());
    }

    @Schedule(timeout = 50000, delta = 50000)
    public String getLastCheckTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        return lastCheckTime = LocalTime.now().format(dtf);
    }
}
