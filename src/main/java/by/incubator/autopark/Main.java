package by.incubator.autopark;

import by.incubator.autopark.collections.VehicleCollection;
import by.incubator.autopark.engine.DieselEngine;
import by.incubator.autopark.rent.Rent;
import by.incubator.autopark.service.CarWash;
import by.incubator.autopark.service.Garage;
import by.incubator.autopark.service.MechanicService;
import by.incubator.autopark.vehicle.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class Main {
    private static final String VEHICLES_CSV = "vehicles.csv";
    private static final String TYPES_CSV = "types.csv";
    private static final String RENTS_CSV = "rents.csv";

    public static void main(String[] args) throws IOException, ParseException {
        VehicleCollection vehicleCollection = new VehicleCollection(TYPES_CSV, VEHICLES_CSV, RENTS_CSV);

        List<VehicleType> vehicleTypes = vehicleCollection.loadTypes(TYPES_CSV);
        List<Vehicle> vehicles = vehicleCollection.loadVehicles(VEHICLES_CSV);
        List<Rent> rents = vehicleCollection.loadRents(RENTS_CSV);
        MechanicService mechanicService = new MechanicService();
        Garage garage = new Garage();

        detectBreakages(vehicles, mechanicService);
        repair(vehicles, mechanicService);
        CarWash.launchCarWash(vehicles);
        pullIntoGarage(vehicles, garage);
    }

    private static void pullIntoGarage(List<Vehicle> vehicles, Garage garage) {
        vehicles.stream().forEach(garage::pullInto);
    }

    private static void repair(List<Vehicle> vehicles, MechanicService mechanicService) {
        vehicles.stream().filter(car -> !mechanicService.isBroken(car)).forEach(mechanicService::repair);
    }

    private static void detectBreakages(List<Vehicle> vehicles, MechanicService mechanicService) {
        vehicles.stream().forEach(mechanicService::detectBreaking);
    }

    private static Vehicle[] initVehicleArray() {
        Vehicle [] vehicles = {
                new Vehicle (1,
                        new VehicleType(0, "Car", 1.3d),
                        CarColor.BLACK, "Volvo XC90", "5427 AX-7", 2323d, 123132,
                        1998,
                        new DieselEngine(2.4d, 9, 100),
                        new ArrayList<>()),
                new Vehicle (2,
                        new VehicleType(0, "Car", 1.3d),
                        CarColor.BLACK, "Audi A6 C5", "5347 AX-7", 2323d, 123132,
                        1998,
                        new DieselEngine(1.9d, 8, 100),
                        new ArrayList<>()),
                new Vehicle (3,
                        new VehicleType(0, "Car", 1.3d),
                        CarColor.BLACK, "Renault Megane III", "5127 AX-7", 2323d, 123132,
                        1998,
                        new DieselEngine(1.5d, 23, 100),
                        new ArrayList<>())
        };

        return vehicles;
    }

    private static class VehicleUtil {
        private static Vehicle findMaxKilometersCar(Vehicle[] vehicles) {
            Arrays.sort(vehicles, new Comparator<Vehicle>() {
                @Override
                public int compare(Vehicle o1, Vehicle o2) {
                    return Double.compare(o1.getEngine().getMaxKilometers(), o2.getEngine().getMaxKilometers());
                }
            });
            return vehicles[vehicles.length - 1];
        }

        private static void print(Vehicle... vehicles) {
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle);
            }
        }

        private static void findEqualCars(Vehicle[] vehicles) {
            for (int i = 0; i < vehicles.length; i++) {
                for (int j = i + 1; j < vehicles.length; j++) {
                    if (vehicles[i].equals(vehicles[j])) {
                        print(vehicles[i], vehicles[j]);
                    }
                }
            }
        }

        private static void sort(Vehicle[] vehicles) {
            boolean flag = true;
            Vehicle holder;

            while (flag) {
                flag = false;

                for (int i = 0; i < vehicles.length - 1; i++) {
                    if (vehicles[i].compareTo(vehicles[i + 1]) > 0) {
                        holder = vehicles[i + 1];
                        vehicles[i + 1] = vehicles[i];
                        vehicles[i] = holder;
                        flag = true;
                    }
                }
            }
        }

        private static void findMileageExtrema(Vehicle[] vehicles) {
            Vehicle max = vehicles[0];
            Vehicle min = vehicles[0];

            for (int i = 0; i < vehicles.length; i++) {
                if (max.getMileage() < vehicles[i].getMileage()) {
                    max = vehicles[i];
                }
                if (min.getMileage() > vehicles[i].getMileage()) {
                    min = vehicles[i];
                }
            }
            System.out.println("The car with the minimal mileage= " + min);
            System.out.println("The car with the maximal mileage= " + max);
        }
    }
}
