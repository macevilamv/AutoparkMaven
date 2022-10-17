package by.incubator.autopark.service;

import by.incubator.autopark.vehicle.Driveable;
import by.incubator.autopark.vehicle.Vehicle;

import java.util.Map;

public interface Fixer {
    public Map<String, Integer> detectBreaking(Driveable vehicle);

    public void repair(Driveable vehicle);

    boolean isBroken(Driveable vehicle);

    default boolean detectAndRepair(Driveable vehicle) {
        detectBreaking(vehicle);
        
        if (isBroken(vehicle)) {
            repair(vehicle);
            return true;
        }
        return false;
    }
}
