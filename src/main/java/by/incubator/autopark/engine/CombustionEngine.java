package by.incubator.autopark.engine;

import by.incubator.autopark.exceptions.NotVehicleException;
import by.incubator.autopark.vehicle.TechnicalSpecialist;

public class CombustionEngine extends AbstractEngine {
    private double engineVolume;
    private double fuelTankCapacity;
    private double fuelConsumptionPer100;

    public CombustionEngine(String engineName, double engineTypeTaxCoefficient, double engineVolume,
                            double fuelConsumptionPer100, double fuelTankCapacity) {
        super(engineName, engineTypeTaxCoefficient);
        setEngineVolume(engineVolume);
        setFuelTankCapacity(fuelTankCapacity);
        setFuelConsumptionPer100(fuelConsumptionPer100);
    }

    @Override
    public double getTaxPerMonth() {
        return this.engineTypeTaxCoefficient;
    }

    @Override
    public double getMaxKilometers() {
        return ((fuelTankCapacity * 100) / fuelConsumptionPer100);
    }

    public double getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(double engineVolume) {
        try {
            if (TechnicalSpecialist.validateEngineVolume(engineVolume)) {
                this.engineVolume = engineVolume;
            } else {
                throw new NotVehicleException("Incorrect engine volume: " + engineVolume);
            }
        } catch (NotVehicleException e) {
            e.printStackTrace();
        }
    }

    public double getFuelTankCapacity() {
        return fuelTankCapacity;
    }

    public void setFuelTankCapacity(double fuelTankCapacity) {
        try {
            if (TechnicalSpecialist.validateFuelTankCapacity(fuelTankCapacity)) {
                this.fuelTankCapacity = fuelTankCapacity;
            } else {
                throw new NotVehicleException("Incorrect fuel tank capacity: " + fuelTankCapacity);
            }
        } catch (NotVehicleException e) {
            e.printStackTrace();
        }
    }

    public double getFuelConsumptionPer100() {
        return fuelConsumptionPer100;
    }

    public void setFuelConsumptionPer100(double fuelConsumptionPer100) {
        try {
            if (TechnicalSpecialist.validateFuelConsumption(fuelConsumptionPer100)) {
                this.fuelConsumptionPer100 = fuelConsumptionPer100;
            } else {
                throw new NotVehicleException("Incorrect fuel consumption: " + fuelConsumptionPer100);
            }
        } catch (NotVehicleException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", "
                + getEngineVolume() + ", "
                + getFuelTankCapacity() + ", "
                + getFuelConsumptionPer100();
    }
}
