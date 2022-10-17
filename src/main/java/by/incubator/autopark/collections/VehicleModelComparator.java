package by.incubator.autopark.collections;

import by.incubator.autopark.vehicle.Driveable;
import by.incubator.autopark.vehicle.Vehicle;

import java.util.Comparator;

public class VehicleModelComparator implements Comparator<Driveable> {

    @Override
    public int compare(Driveable o1, Driveable o2) {
        return o1.getModel().compareTo(o2.getModel());
    }
}
