package by.incubator.autopark.service;

import by.incubator.autopark.vehicle.Driveable;
import by.incubator.autopark.vehicle.Vehicle;
import by.incubator.autopark.infrastructure.core.annotations.Autowired;

import java.util.ArrayList;
import java.util.List;

public class Workroom {
    @Autowired
    private Fixer mechanic;

    public Workroom() {
    }

    public List<Driveable> getRepairedVehicles(List<Driveable> vehicles) {
        List<Driveable> driveableList = new ArrayList<>();

        vehicles.stream()
                .forEach(vehicle -> {
                    showCurrentTechnicalStatus(vehicle);
                    if (!mechanic.isBroken(vehicle)) {
                       driveableList.add(vehicle);
                    }
                });

        return driveableList;
    }

    public void checkAllVehicle(List<Driveable> vehicleList) {
        for (Driveable vehicle : vehicleList) {
            mechanic.detectBreaking(vehicle);
            showCurrentTechnicalStatus(vehicle);
        }
    }

    private void showCurrentTechnicalStatus(Driveable vehicle) {
        if (!mechanic.isBroken(vehicle)) {
            System.out.println("Vehicle: " + vehicle.getModel() + " id: " + vehicle.getId() + " works properly");
        } else {
            System.out.println("Vehicle: " + vehicle.getModel() + " id: " + vehicle.getId()
                    + "doesn't work properly. Is being repaired...");
            mechanic.repair(vehicle);
        }
    }

    public Fixer getMechanic() {
        return mechanic;
    }

    public void setMechanic(Fixer mechanic) {
        this.mechanic = mechanic;
    }
}
