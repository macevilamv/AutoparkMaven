package by.incubator.autopark.collections;

import by.incubator.autopark.parsers.ParserVehicleFromFile;
import by.incubator.autopark.rent.Rent;
import by.incubator.autopark.vehicle.Vehicle;
import by.incubator.autopark.vehicle.VehicleType;
import by.incubator.autopark.infrastructure.core.annotations.Autowired;
import by.incubator.autopark.infrastructure.core.annotations.InitMethod;
import lombok.SneakyThrows;

import java.util.*;

public class VehicleCollection {
    private List<VehicleType> types;
    private List<Rent> rents;
    private List<Vehicle> vehicles;
    @Autowired
    private ParserVehicleFromFile parser;

    public VehicleCollection() {
    }

    @SneakyThrows
    @InitMethod
    public void init() {
        this.types = parser.loadTypes();
        this.rents = parser.loadRents();
        this.vehicles = parser.loadVehicles();
    }

    public void insert(int index, Vehicle vehicle) {
        if (index < 0 || index >= this.vehicles.size()) {
            this.vehicles.add(vehicle);
        } else {
            this.vehicles.add(index, vehicle);
        }
    }

    public int delete(int index) {
        if (index < 0 || index >= this.vehicles.size()) {
            return -1;
        } else {
            this.vehicles.remove(index);
            return index;
        }
    }

    public double sumTotalProfit() {
        double sum = 0.0d;

        for (Vehicle vehicle : vehicles) {
            sum += vehicle.getTotalProfit();
        }
        return sum;
    }

    public List<VehicleType> getTypes() {
        return types;
    }

    public void setTypes(List<VehicleType> types) {
        this.types = types;
    }

    public List<Rent> getRents() {
        return rents;
    }

    public void setRents(List<Rent> rents) {
        this.rents = rents;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @SneakyThrows
    public void display() {
        VehicleTableRenderer.renderVehicleTable(this.vehicles);
    }

    public void sort() {
        Collections.sort(this.vehicles, new VehicleModelComparator());
    }
}
