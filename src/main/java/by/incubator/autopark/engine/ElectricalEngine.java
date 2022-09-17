package by.incubator.autopark.engine;

import by.incubator.autopark.exceptions.NotVehicleException;
import by.incubator.autopark.vehicle.TechnicalSpecialist;

public class ElectricalEngine extends AbstractEngine {
    private double batterySize;
    private double electricityConsumption;

    public ElectricalEngine(double electricityConsumption, double batterySize) {
        super("Electrical", 0.1);
        setBatterySize(batterySize);
        setElectricityConsumption(electricityConsumption);
    }

    @Override
    public double getTaxPerMonth() {
        return this.engineTypeTaxCoefficient;
    }

    @Override
    public double getMaxKilometers() {
        return batterySize / electricityConsumption;
    }

    public double getBatterySize() {
        return batterySize;
    }

    public void setBatterySize(double batterySize) {
        try {
            if (TechnicalSpecialist.validateBatterySize(batterySize)) {
                this.batterySize = batterySize;
            } else {
                throw new NotVehicleException("Incorrect battery size: " + batterySize);
            }
        } catch (NotVehicleException e) {
            e.printStackTrace();
        }
    }

    public double getElectricityConsumption() {
        return electricityConsumption;
    }

    public void setElectricityConsumption(double electricityConsumption) {
        try {
            if (TechnicalSpecialist.validateElectricityConsumption(electricityConsumption)) {
                this.electricityConsumption = electricityConsumption;
            } else {
                throw new NotVehicleException("Incorrect electricity consumption: " + electricityConsumption);
            }
        } catch (NotVehicleException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", "
                + getElectricityConsumption() + ", "
                + getBatterySize();
    }
}
