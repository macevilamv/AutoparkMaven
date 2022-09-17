package by.incubator.autopark.engine;

public class GasolineEngine extends CombustionEngine {
    public GasolineEngine(double engineVolume, double fuelConsumptionPer100, double fuelTankCapacity) {
        super("Gasoline", 1.1, engineVolume, fuelConsumptionPer100, fuelTankCapacity);
    }
}
