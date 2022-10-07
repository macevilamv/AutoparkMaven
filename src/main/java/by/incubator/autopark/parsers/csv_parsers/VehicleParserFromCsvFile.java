package by.incubator.autopark.parsers.csv_parsers;

import by.incubator.autopark.engine.Startable;
import by.incubator.autopark.rent.Rent;
import by.incubator.autopark.service.TechnicalSpecialist;
import by.incubator.autopark.vehicle.CarColor;
import by.incubator.autopark.vehicle.Vehicle;
import by.incubator.autopark.vehicle.VehicleType;
import by.incubator.autopark.infrastructure.core.annotations.Autowired;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import static by.incubator.autopark.parsers.csv_parsers.EngineParser.parseEngine;
import static by.incubator.autopark.parsers.csv_parsers.FromCsvFileParser.readFromFile;
import static by.incubator.autopark.utils.StringProcessor.processDataInCsvFormat;

public class VehicleParserFromCsvFile {
    private static final String VEHICLE_CSV_PATH = "src/main/resources/csv/vehicles.csv";
    @Autowired
    private TechnicalSpecialist technicalSpecialist;
    @Autowired
    RentsParserFromCsvFile rentsParserFromCsvFile;
    @Autowired
    VehicleTypeParserFromCsvFile vehicleTypeParserFromCsvFile;

    public VehicleParserFromCsvFile() {

    }

    public List<Vehicle> loadVehicles() throws IOException, ParseException {
        List<Vehicle> vehicleList = new ArrayList<>();
        File csvFile = new File(VEHICLE_CSV_PATH);
        List<String> vehiclesBuffer = processDataInCsvFormat(readFromFile(csvFile));

        for (String csvString : vehiclesBuffer) {
            vehicleList.add(createVehicle(csvString));
        }
        return vehicleList;
    }

    private Vehicle createVehicle(String csvStringOfVehicle) throws IOException, ParseException {
        String[] vehicleParametersBuffer = csvStringOfVehicle.split(",");
        int id = Integer.parseInt(vehicleParametersBuffer[CsvIndexingParser.CSV_INDEX.get("VEHICLE-CSV_ID")]);
        int typeId = Integer.parseInt(vehicleParametersBuffer[CsvIndexingParser.CSV_INDEX.get("VEHICLE-CSV_TYPE_ID")]);
        VehicleType vehicleType = generateTypeById(typeId);
        String model = vehicleParametersBuffer[CsvIndexingParser.CSV_INDEX.get("VEHICLE-CSV_MODEL")];
        String registrationNumber = vehicleParametersBuffer[CsvIndexingParser.CSV_INDEX.get("VEHICLE-CSV_NUMBER")];
        double mass = Double.parseDouble(vehicleParametersBuffer[CsvIndexingParser.CSV_INDEX.get("VEHICLE-CSV_WEIGHT")]);
        int manufactureYear = Integer.parseInt(vehicleParametersBuffer[CsvIndexingParser.CSV_INDEX.get("VEHICLE-CSV_YEAR")]);
        int mileage = Integer.parseInt(vehicleParametersBuffer[CsvIndexingParser.CSV_INDEX.get("VEHICLE-CSV_MILEAGE")]);
        CarColor color = CarColor.valueOf(vehicleParametersBuffer[CsvIndexingParser.CSV_INDEX.get("VEHICLE-CSV_COLOR")].toUpperCase(Locale.ROOT));
        String engineName = vehicleParametersBuffer[CsvIndexingParser.CSV_INDEX.get("VEHICLE-CSV_ENGINE")];
        Startable engine = parseEngine(engineName, vehicleParametersBuffer, CsvIndexingParser.CSV_INDEX);
        List<Rent> rents = generateRentListById(id);

        return new Vehicle(id, vehicleType, color, model, registrationNumber, mass,
                mileage, manufactureYear, engine, rents);
    }

    private VehicleType generateTypeById(int typeId) throws IOException {
        for (VehicleType vehicleType : vehicleTypeParserFromCsvFile.loadVehicleTypes()) {
            if (vehicleType.getId() == typeId) {
                return vehicleType;
            }
        }
        throw new RuntimeException("Type with such ID doesn't exist");
    }

    private List<Rent> generateRentListById(int id) throws IOException, ParseException {
        List<Rent> rents = new ArrayList<>();

        for (Rent rent : rentsParserFromCsvFile.loadRents()) {
            if (rent.getVehicleId() == id) {
                rents.add(rent);
            }
        }
        return rents;
    }
}
