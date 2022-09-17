package by.incubator.autopark.service;

import by.incubator.autopark.vehicle.Vehicle;

import java.util.Map;

public interface Fixer {
    public Map<String, Integer> detectBreaking(Vehicle vehicle);

    public void repair(Vehicle vehicle);

    boolean isBroken(Vehicle vehicle);

    default boolean detectAndRepair(Vehicle vehicle) {
        detectBreaking(vehicle);
        
        if (isBroken(vehicle)) {
            repair(vehicle);
            return true;
        }
        return false;
    }
}
