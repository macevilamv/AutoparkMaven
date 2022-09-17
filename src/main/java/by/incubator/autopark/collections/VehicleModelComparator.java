package by.incubator.autopark.collections;

import by.incubator.autopark.vehicle.Vehicle;

import java.util.Comparator;

public class VehicleModelComparator implements Comparator<Vehicle> {
    public int compare(Vehicle o1, Vehicle o2) {
        return o2.compareTo(o1);
    }
}
