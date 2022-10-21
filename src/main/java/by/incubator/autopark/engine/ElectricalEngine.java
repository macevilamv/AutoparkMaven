package by.incubator.autopark.engine;

import by.incubator.autopark.exceptions.NotVehicleException;
import by.incubator.autopark.service.TechnicalSpecialist;

public class ElectricalEngine extends AbstractEngine {
    private double batterySize;
    private double electricityConsumption;

    public ElectricalEngine(double electricityConsumption, double batterySize) {
        super("Electrical", 0.1);
        setBatterySize(batterySize);
        setElectricityConsumption(electricityConsumption);
    }

    @Override
    public String getName() {
        return "Electrical";
    }

    @Override
    public double getTaxPerMonth() {
        return this.engineTypeTaxCoefficient;
    }

    @Override
    public double getMaxKilometers() {
        return batterySize / electricityConsumption;
    }

    @Override
    public double getCapacity() {
        return batterySize;
    }

    @Override
    public double getConsumption() {
        return this.electricityConsumption;
    }

    @Override
    public double getVolume() {
        return 0;
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
