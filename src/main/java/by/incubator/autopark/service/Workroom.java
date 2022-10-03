package by.incubator.autopark.service;

import by.incubator.autopark.vehicle.Vehicle;
import by.incubator.autopark.infrastructure.core.annotations.Autowired;

import java.util.List;

public class Workroom {
    @Autowired
    private Fixer mechanic;

    public Workroom() {
    }

    public void checkAllVehicle(List<Vehicle> vehicleList) {
        for (Vehicle vehicle : vehicleList) {
            mechanic.detectBreaking(vehicle);

            if (mechanic.isBroken(vehicle)) {
                System.out.println(vehicle + " is broken");
            }
        }

        for (Vehicle vehicle : vehicleList) {
            if (!mechanic.isBroken(vehicle)) {
                System.out.println(vehicle + "in great condition");
            }
        }
    }


    public Fixer getMechanic() {
        return mechanic;
    }

    public void setMechanic(Fixer mechanic) {
        this.mechanic = mechanic;
    }
}
