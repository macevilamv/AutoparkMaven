package by.incubator.autopark.engine;

public class DieselEngine extends CombustionEngine {
    public DieselEngine (double engineVolume, double fuelConsumptionPer100, double fuelTankCapacity) {
        super("Diesel", 1.2, engineVolume, fuelConsumptionPer100, fuelTankCapacity);
    }
}
