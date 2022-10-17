package by.incubator.autopark.collections;

import by.incubator.autopark.parsers.interfaces.ParserRentInterface;
import by.incubator.autopark.parsers.interfaces.ParserVehicleInterface;
import by.incubator.autopark.parsers.interfaces.ParserVehicleTypeInterface;
import by.incubator.autopark.rent.Rentable;
import by.incubator.autopark.vehicle.Driveable;
import by.incubator.autopark.vehicle.TypeInterface;
import by.incubator.autopark.vehicle.Vehicle;
import by.incubator.autopark.infrastructure.core.annotations.Autowired;
import by.incubator.autopark.infrastructure.core.annotations.InitMethod;
import lombok.SneakyThrows;

import java.util.*;

public class VehicleCollection {
    private List<TypeInterface> types;
    private List<Rentable> rents;
    private List<Driveable> vehicles;
    @Autowired
    private ParserVehicleInterface vehicleParser;
    @Autowired
    private ParserVehicleTypeInterface typeParser;
    @Autowired
    private ParserRentInterface rentsParser;

    public VehicleCollection() {
    }

    @SneakyThrows
    @InitMethod
    public void init() {
        this.types = typeParser.loadVehicleTypes();
        this.rents = rentsParser.loadRents();
        this.vehicles = vehicleParser.loadVehicles();
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

        for (Driveable vehicle : vehicles) {
            sum += vehicle.getTotalProfit();
        }
        return sum;
    }

    public List<TypeInterface> getTypes() {
        return types;
    }

    public void setTypes(List<TypeInterface> types) {
        this.types = types;
    }

    public List<Rentable> getRents() {
        return rents;
    }

    public void setRents(List<Rentable> rents) {
        this.rents = rents;
    }

    public List<Driveable> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Driveable> vehicles) {
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
