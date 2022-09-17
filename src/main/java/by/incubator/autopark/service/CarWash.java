package by.incubator.autopark.service;

import by.incubator.autopark.collections.MyQueue;
import by.incubator.autopark.vehicle.Vehicle;

import java.util.Arrays;
import java.util.List;

public class CarWash {

    public static void launchCarWash(List<Vehicle> vehicles) {
        vehicles.stream().forEach(CarWash::washCar);
    }

    private static void washCar(Vehicle vehicle) {
        System.out.println("Car: " + vehicle.getModel() + " |"
                + vehicle.getRegistrationNumber() +  "| is washed!");
    }
}
