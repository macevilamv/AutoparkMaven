package by.incubator.autopark.service;

import by.incubator.autopark.collections.MyStack;
import by.incubator.autopark.vehicle.Vehicle;

public class Garage {
    MyStack<Vehicle> garage;

    public Garage (int capacity) {
        this.garage = new MyStack<>(capacity);
    }

    public Garage() {
        this.garage = new MyStack<>();
    }

    public void pullInto(Vehicle vehicle) {
        garage.push(vehicle);
        System.out.println(vehicle.getModel() + " is pulled into garage");
    }

    public void pullOut() {
        Vehicle pulledOutVehicle = garage.pop();
        System.out.println(pulledOutVehicle.getModel() + " is pulled out of garage");
    }
}
