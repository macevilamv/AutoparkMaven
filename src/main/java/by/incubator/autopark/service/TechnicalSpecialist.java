package by.incubator.autopark.service;

import by.incubator.autopark.vehicle.CarColor;
import by.incubator.autopark.vehicle.VehicleType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TechnicalSpecialist {
    public static final int LOWER_LIMIT_MANUFACTURE_YEAR = 1886;

    public TechnicalSpecialist() {
        System.out.println("Hello from TechSpec");
    }

    public static boolean validateManufactureYear(int year) {
        return year >= LOWER_LIMIT_MANUFACTURE_YEAR;
    }

    public static boolean validateMileage(int mileage) {
        return mileage >= 0;
    }

    public static boolean validateWeight(double weight) {
        return weight >= 0;
    }

    public static boolean validateColor(CarColor color) {
        return color != null;
    }

    public static boolean validateVehicleType(VehicleType type) {
        if (type == null) {
            return false;
        }

        return type.getTypeName() != null && !(type.getTypeName().isEmpty()) &&
                type.getTaxCoefficient() >= 0;
    }

    public static boolean validateRegistrationNumber(String number) {
        if (number == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("\\d{4}\\s[A-Z]{2}-[0-7]");
        Matcher matcher = pattern.matcher(number);

        return  matcher.matches();
    }

    public static boolean validateModelName(String name) {
        return (name != null && !(name.isEmpty()));
    }

    public static boolean validateEngineVolume(double volume) {
        return volume >= 0;
    }
    public static boolean validateFuelTankCapacity(double capacity) {
        return capacity >= 0;
    }

    public static boolean validateFuelConsumption(double consumption) {
        return consumption >= 0;
    }

    public static boolean validateBatterySize(double size) {
        return size >= 0;
    }

    public static boolean validateElectricityConsumption(double consumption) {
        return consumption >= 0;
    }
}

