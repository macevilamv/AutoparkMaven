package by.incubator.autopark.service;

import by.incubator.autopark.vehicle.Driveable;
import by.incubator.autopark.vehicle.Vehicle;

import java.util.Map;

public class BadMechanicService implements Fixer {
    @Override
    public Map<String, Integer> detectBreaking(Driveable vehicle) {
        return null;
    }

    @Override
    public void repair(Driveable vehicle) {

    }

    @Override
    public boolean isBroken(Driveable vehicle) {
        return false;
    }
}
