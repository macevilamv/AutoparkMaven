package by.incubator.autopark.service;

import by.incubator.autopark.vehicle.Vehicle;

import java.util.Map;

public class BadMechanicService implements Fixer {
    @Override
    public Map<String, Integer> detectBreaking(Vehicle vehicle) {
        return null;
    }

    @Override
    public void repair(Vehicle vehicle) {

    }

    @Override
    public boolean isBroken(Vehicle vehicle) {
        return false;
    }
}
