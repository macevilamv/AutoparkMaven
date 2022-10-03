package by.incubator.autopark.parsers;

import by.incubator.autopark.engine.Startable;
import by.incubator.autopark.rent.Rent;
import by.incubator.autopark.service.TechnicalSpecialist;
import by.incubator.autopark.vehicle.CarColor;
import by.incubator.autopark.vehicle.Vehicle;
import by.incubator.autopark.vehicle.VehicleType;
import by.incubator.autopark.infrastructure.core.annotations.Autowired;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static by.incubator.autopark.parsers.EngineParser.parseEngine;
import static by.incubator.autopark.parsers.FileParser.readFromFile;
import static by.incubator.autopark.parsers.StringProcessor.proceedStrings;

public class ParserVehicleFromFile {
    private static final Map<String, Integer> CONFIG = new HashMap<>();
    private static final String CSV_CONFIG_PATH = "src/main/resources/csv/csv-config.properties";
    private static final String VEHICLE_CSV_PATH = "src/main/resources/csv/vehicles.csv";
    private static final String RENTS_CSV_PATH = "src/main/resources/csv/rents.csv";
    private static final String TYPES_CSV_PATH = "src/main/resources/csv/types.csv";
    @Autowired
    private TechnicalSpecialist technicalSpecialist;

    static {
        try {
            initProperties();
        } catch (IOException e) {
            throw new RuntimeException("Error: \"csv-config.properties\" file reading has failed.", e);
        }
    }

    public ParserVehicleFromFile() {

    }

    public List<VehicleType> loadTypes() throws IOException {
        List<VehicleType> typesList = new ArrayList<>();
        File csvFile = new File(TYPES_CSV_PATH);
        List<String> typesBuffer = proceedStrings(readFromFile(csvFile));

        for (String csvString : typesBuffer) {
            typesList.add(createType(csvString));
        }
        return typesList;
    }

    public List<Rent> loadRents() throws IOException, ParseException {
        List<Rent> rentList = new ArrayList<>();
        File csvFile = new File(RENTS_CSV_PATH);
        List<String> rentsBuffer = proceedStrings(readFromFile(csvFile));

        for (String csvString : rentsBuffer) {
            rentList.add(createRent(csvString));
        }
        return rentList;
    }

    public List<Vehicle> loadVehicles() throws IOException, ParseException {
        List<Vehicle> vehicleList = new ArrayList<>();
        File csvFile = new File(VEHICLE_CSV_PATH);
        List<String> vehiclesBuffer = proceedStrings(readFromFile(csvFile));

        for (String csvString : vehiclesBuffer) {
            vehicleList.add(createVehicle(csvString));
        }
        return vehicleList;
    }

    private static VehicleType createType(String csvStringOfType) {
        String[] typeParametersBuffer = csvStringOfType.split(",");
        int id = Integer.parseInt(typeParametersBuffer[CONFIG.get("TYPE-CSV_ID")]);;
        String typeName = typeParametersBuffer[CONFIG.get("TYPE-CSV_NAME")];;
        double tax = Double.parseDouble(typeParametersBuffer[CONFIG.get("TYPE-CSV_TAX")]);;

        return new VehicleType(id, typeName, tax);
    }

    private static Rent createRent(String csvStringOfRent) throws ParseException {
        String[] rentParametersBuffer = csvStringOfRent.split(",");
        int vehicleId = Integer.parseInt(rentParametersBuffer[CONFIG.get("RENT-CSV_CAR_ID")]);;
        DateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String rentDate = simpleDateFormat.format(simpleDateFormat.
                parse(rentParametersBuffer[CONFIG.get("RENT-CSV_DATE")]));;
        double cost = Double.parseDouble(rentParametersBuffer[CONFIG.get("RENT-CSV_COST")]);

        return new Rent(vehicleId, rentDate, cost);
    }

    private Vehicle createVehicle(String csvStringOfVehicle) throws IOException, ParseException {
        String[] vehicleParametersBuffer = csvStringOfVehicle.split(",");
        int id = Integer.parseInt(vehicleParametersBuffer[CONFIG.get("VEHICLE-CSV_ID")]);
        int typeId = Integer.parseInt(vehicleParametersBuffer[CONFIG.get("VEHICLE-CSV_TYPE_ID")]);
        VehicleType vehicleType = generateTypeById(typeId);
        String model = vehicleParametersBuffer[CONFIG.get("VEHICLE-CSV_MODEL")];
        String registrationNumber = vehicleParametersBuffer[CONFIG.get("VEHICLE-CSV_NUMBER")];
        double mass = Double.parseDouble(vehicleParametersBuffer[CONFIG.get("VEHICLE-CSV_WEIGHT")]);
        int manufactureYear = Integer.parseInt(vehicleParametersBuffer[CONFIG.get("VEHICLE-CSV_YEAR")]);
        int mileage = Integer.parseInt(vehicleParametersBuffer[CONFIG.get("VEHICLE-CSV_MILEAGE")]);
        CarColor color = CarColor.valueOf(vehicleParametersBuffer[CONFIG.get("VEHICLE-CSV_COLOR")].toUpperCase(Locale.ROOT));
        String engineName = vehicleParametersBuffer[CONFIG.get("VEHICLE-CSV_ENGINE")];
        Startable engine = parseEngine(engineName, vehicleParametersBuffer, CONFIG);
        List<Rent> rents = generateRentListById(id);

        return new Vehicle(id, vehicleType, color, model, registrationNumber, mass,
                mileage, manufactureYear, engine, rents);
    }

    private VehicleType generateTypeById(int typeId) throws IOException {
        for (VehicleType vehicleType : loadTypes()) {
            if (vehicleType.getId() == typeId) {
                return vehicleType;
            }
        }
        throw new RuntimeException("Type with such ID doesn't exist");
    }

    private List<Rent> generateRentListById(int id) throws IOException, ParseException {
        List<Rent> rents = new ArrayList<>();

        for (Rent rent : loadRents()) {
            if (rent.getVehicleId() == id) {
                rents.add(rent);
            }
        }
        return rents;
    }

    private static void initProperties() throws IOException {
        try (FileReader fileReader =
                     new FileReader(ParserVehicleFromFile.CSV_CONFIG_PATH)
        ) {
            Properties properties = new Properties();
            properties.load(fileReader);

            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                CONFIG.put((String) entry.getKey(), Integer.parseInt((String) entry.getValue()));
            }
        }
    }
}
