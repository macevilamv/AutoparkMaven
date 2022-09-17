package by.incubator.autopark.collections;

import by.incubator.autopark.engine.Startable;
import by.incubator.autopark.rent.Rent;
import by.incubator.autopark.vehicle.CarColor;
import by.incubator.autopark.vehicle.Vehicle;
import by.incubator.autopark.vehicle.VehicleType;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static by.incubator.autopark.ParsingUtils.EngineParser.parseEngine;
import static by.incubator.autopark.ParsingUtils.FileParser.readFromFile;
import static by.incubator.autopark.ParsingUtils.StringProcessor.proceedStrings;

public class VehicleCollection {
    private static final String CSV_CONFIG_PATH = "src/main/resources/csv/csv-config.properties";
    private static final Map<String, Integer> CONFIG = new HashMap<>();
    private List<VehicleType> types;
    private List<Rent> rents;
    private List<Vehicle> vehicles;

    static {
        try {
            initProperties(CSV_CONFIG_PATH);
        } catch (IOException e) {
            throw new RuntimeException("Error: \"csv-config.properties\" file reading has failed.", e);
        }
    }

    public VehicleCollection(String types, String vehicles, String rents) {
        try {
            this.types = loadTypes(types);
            this.rents = loadRents(rents);
            this.vehicles = loadVehicles(vehicles);
        } catch (IOException | ParseException e) {
            throw new IllegalArgumentException("Error: incorrect path to .csv file.", e);
        }
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

    public void display() {
        VehicleTableRenderer.renderVehicleTable(this.vehicles);
    }

    public void sort() {
        Collections.sort(this.vehicles, new VehicleModelComparator());
    }

    public static List<VehicleType> loadTypes(String inFile) throws IOException {
        List<VehicleType> typesList = new ArrayList<>();
        String path = "src/main/resources/csv/" + inFile;
        File csvFile = new File(path);
        List<String> typesBuffer = proceedStrings(readFromFile(csvFile));

        for (String csvString : typesBuffer) {
            typesList.add(createType(csvString));
        }
        return typesList;
    }

    public static List<Rent> loadRents(String inFile) throws IOException, ParseException {
        List<Rent> rentList = new ArrayList<>();
        String path = "src/main/resources/csv/" + inFile;
        File csvFile = new File(path);
        List<String> rentsBuffer = proceedStrings(readFromFile(csvFile));

        for (String csvString : rentsBuffer) {
            rentList.add(createRent(csvString));
        }
        return rentList;
    }

    public static List<Vehicle> loadVehicles(String inFile) throws IOException, ParseException {
        List<Vehicle> vehicleList = new ArrayList<>();
        String path = "src/main/resources/csv/" + inFile;
        File csvFile = new File(path);
        List<String> vehiclesBuffer = proceedStrings(readFromFile(csvFile));

        for (String csvString : vehiclesBuffer) {
            vehicleList.add(createVehicle(csvString));
        }
        return vehicleList;
    }

    private static VehicleType createType(String csvStringOfType) {
        int id;
        String typeName;
        double tax;
        String[] typeParametersBuffer = csvStringOfType.split(",");

        id = Integer.parseInt(typeParametersBuffer[CONFIG.get("TYPE-CSV_ID")]);
        typeName = typeParametersBuffer[CONFIG.get("TYPE-CSV_NAME")];
        tax = Double.parseDouble(typeParametersBuffer[CONFIG.get("TYPE-CSV_TAX")]);

        return new VehicleType(id, typeName, tax);
    }

    private static Rent createRent(String csvStringOfRent) throws ParseException {
        int vehicleId;
        String rentDate;
        double cost;
        DateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String[] rentParametersBuffer = csvStringOfRent.split(",");

        vehicleId = Integer.parseInt(rentParametersBuffer[CONFIG.get("RENT-CSV_CAR_ID")]);
        rentDate = simpleDateFormat.format(simpleDateFormat.
                parse(rentParametersBuffer[CONFIG.get("RENT-CSV_DATE")]));
        cost = Double.parseDouble(rentParametersBuffer[CONFIG.get("RENT-CSV_COST")]);

        return new Rent(vehicleId, rentDate, cost);
    }

    private static Vehicle createVehicle(String csvStringOfVehicle) throws IOException, ParseException {
        double mass;
        int mileage;
        int manufactureYear;
        int id;
        int typeId;
        VehicleType vehicleType;
        CarColor color;
        String model;
        String registrationNumber;
        String engineName;
        Startable engine;
        List<Rent> rents;
        String[] vehicleParametersBuffer = csvStringOfVehicle.split(",");

        id = Integer.parseInt(vehicleParametersBuffer[CONFIG.get("VEHICLE-CSV_ID")]);
        typeId = Integer.parseInt(vehicleParametersBuffer[CONFIG.get("VEHICLE-CSV_TYPE_ID")]);
        vehicleType = generateTypeById(typeId);
        model = vehicleParametersBuffer[CONFIG.get("VEHICLE-CSV_MODEL")];
        registrationNumber = vehicleParametersBuffer[CONFIG.get("VEHICLE-CSV_NUMBER")];
        mass = Double.parseDouble(vehicleParametersBuffer[CONFIG.get("VEHICLE-CSV_WEIGHT")]);
        manufactureYear = Integer.parseInt(vehicleParametersBuffer[CONFIG.get("VEHICLE-CSV_YEAR")]);
        mileage = Integer.parseInt(vehicleParametersBuffer[CONFIG.get("VEHICLE-CSV_MILEAGE")]);
        color = CarColor.valueOf(vehicleParametersBuffer[CONFIG.get("VEHICLE-CSV_COLOR")].toUpperCase(Locale.ROOT));
        engineName = vehicleParametersBuffer[CONFIG.get("VEHICLE-CSV_ENGINE")];
        engine = parseEngine(engineName, vehicleParametersBuffer, CONFIG);
        rents = generateRentListById(id);

        return new Vehicle(id, vehicleType, color, model, registrationNumber, mass,
                mileage, manufactureYear, engine, rents);
    }

    private static VehicleType generateTypeById(int typeId) throws IOException {
        for (VehicleType vehicleType : loadTypes("types.csv")) {
            if (vehicleType.getId() == typeId) {
                return vehicleType;
            }
        }
        throw new RuntimeException("Type with such ID doesn't exist");
    }

    private static List<Rent> generateRentListById(int id) throws IOException, ParseException {
        List<Rent> rents = new ArrayList<>();

        for (Rent rent : loadRents("rents.csv")) {
            if (rent.getVehicleId() == id) {
                rents.add(rent);
            }
        }
        return rents;
    }

    private static void initProperties(String configPropPath) throws IOException {
        try (FileReader fileReader =
                     new FileReader(configPropPath)
        ) {
            Properties properties = new Properties();
            properties.load(fileReader);

            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                CONFIG.put((String) entry.getKey(), Integer.parseInt((String) entry.getValue()));
            }
        }
    }
}
